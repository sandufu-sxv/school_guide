package com.jiang.school_guide.untils;

import org.springframework.util.DigestUtils;

public class EncryptionService {

    public static String encryption(String password){
        String encryptionPassword;
        encryptionPassword = DigestUtils.md5DigestAsHex((password + "5434").getBytes());
        encryptionPassword = DigestUtils.md5DigestAsHex((encryptionPassword + "Head").getBytes());
        return encryptionPassword;
    }
}
