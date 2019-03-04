package com.ymkj.app.utils;

import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author Xiaohao
 * @date 2019/03/03
 */
public class PasswordHash
{
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int SALT_BYTE_SIZE = 24;
    private static final int HASH_BYTE_SIZE = 24;
    private static final int PBKDF2_ITERATIONS = 1000;

    private static final int ITERATION_INDEX = 0;
    private static final int SALT_INDEX = 1;
    private static final int PBKDF2_INDEX = 2;

    /**
     * 将字符串用户密码转化为字符数组
     * @param   password  用户密码
     * @return         返回字符数组。
     */
    public static String createHash(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        return createHash(password.toCharArray());
    }

    /**
     *转化为
     * @param   password    用户密码字符数组
     * @return              返回字符串“迭代1000：16进制盐值：16进制加密后密码”。
     */
    private static String createHash(char[] password)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        // 随机生成盐
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        // Hash 密码
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // 格式化为16进制的字符串 “迭代1000：16进制盐值：16进制加密后密码”
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" +  toHex(hash);
    }

    /**
     * 使用哈希验证密码
     *
     * @param   password        输入的密码
     * @param   correctHash     有效密码 =》格式化为16进制的字符串
     * @return                  如果密码正确则为真，如果错误则为假
     */
    public static boolean validatePassword(String password, String correctHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        return validatePassword(password.toCharArray(), correctHash);
    }

    /**
     * 使用哈希验证密码
     *
     * @param   password        输入的密码
     * @param   correctHash     有效密码 =》格式化为16进制的字符串
     * @return                  如果密码正确则为真，如果错误则为假
     */
    private static boolean validatePassword(char[] password, String correctHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        // 将格式化为16进制的字符串解码为其参数
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[ITERATION_INDEX]);
        byte[] salt = fromHex(params[SALT_INDEX]);
        byte[] hash = fromHex(params[PBKDF2_INDEX]);
        // 使用相同的盐值、迭代计数和哈希长度计算提供的密码的哈希
        byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
        // 比较常量时间内的哈希值。如果两个哈希匹配，则密码正确。
        return slowEquals(hash, testHash);
    }

    /**
     * 比较两个字节数组的长度常数时间。这种比较方法的使用，使密码哈希不能提取从一个在线系统使用定时攻击，然后离线攻击。
     * @param   a       第一个字节数组
     * @param   b       第二个字节数组
     * @return          如果两个字节数组相同，则为真;如果不相同，则为假
     */
    private static boolean slowEquals(byte[] a, byte[] b)
    {
        int diff = a.length ^ b.length;
        for(int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    /**
     *  计算密码的PBKDF2 hash。
     *
     * @param   password    the password to hash.
     * @param   salt        the salt
     * @param   iterations  the iteration count (slowness factor)
     * @param   bytes       the length of the hash to compute in bytes
     * @return              the PBDKF2 hash of the password
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    /**
     * 将十六进制字符字符串转换为字节数组。
     *
     * @param   hex         the hex string
     * @return              十六进制字符串解码成字节数组
     */
    private static byte[] fromHex(String hex)
    {
        byte[] binary = new byte[hex.length() / 2];
        for(int i = 0; i < binary.length; i++)
        {
            binary[i] = (byte)Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
        }
        return binary;
    }

    /**
     * 将字节数组转换为十六进制字符串。
     *
     * @param   array       要转换的字节数组
     * @return              对字节数组进行编码长度*2的字符串
     */
    private static String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }



}