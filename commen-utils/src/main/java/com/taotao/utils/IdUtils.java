package com.taotao.utils;

import java.util.Random;

public class IdUtils {

    /*
    * 获取商品id
    * */
    public static Long getItemId(){
        long time = System.currentTimeMillis();
        int random  = new Random().nextInt(99);
        String str = time+String.format("%02d",random);
        Long id = new Long(str);
        return id;
    }
}
