package com.ymkj.app.utils;


import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Administrator
 */
public class FileUtil {

    private static final String START_PATH = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/temp/";
    private static final String END_PATH = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/images/";

    /**
     * 静态方法：三个参数：文件的二进制，文件路径，文件名
     *
     * @param file
     * @param filePath
     * @param fileName
     * @throws IOException
     */
    public static void fileupload(byte[] file, String filePath, String fileName) throws IOException {
        //目标目录
        File targetfile = new File(filePath);
        if (targetfile.exists()) {
            targetfile.mkdirs();
        }
        //二进制流写入
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }


    /**
     * 将图片移动至另一个文件夹下
     * @param imageName 图片名字
     */
    public static void moveTotherFolders(String imageName) {

        try {
            File startFile = new File(START_PATH + imageName);
            //获取文件夹路径
            File tmpFile = new File(END_PATH);
            //判断文件夹是否创建，没有创建则创建新文件夹
            if (!tmpFile.exists()) {
                tmpFile.mkdirs();
            }
            if (startFile.renameTo(new File(END_PATH + startFile.getName()))) {
                System.out.println("File is moved successful!");

            } else {
                System.out.println("File is failed to move!");

            }
        } catch (Exception e) {
            System.out.println("error");

        }
    }

}
