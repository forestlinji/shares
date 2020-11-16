package com.icecream.shares.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class MD5Utils {
    public static String cptry(String password){
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(password);
        return digestHex;
    }
}
