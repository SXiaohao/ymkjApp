package com.ymkj.app.service.common;

import java.util.Map;

public interface VerificationCode {
    /**
     * 发送验证码
     *
     * @param phone 手机号
     * @return 发送状态
     */
    Map sendStatus(String phone);
}
