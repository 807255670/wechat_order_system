package com.hodor.demo.utils;

import java.util.Random;

/**
 * Created By Fan Huiliang
 * 2019-07-08 21:19
 */
public class KeyUtil {

    /*
    * 生成唯一主键
    * 格式：时间加随机数
    * */
    public static synchronized String genUniqueKey(){
        Random random = new Random();

        Long currentTime = System.currentTimeMillis();

        Integer num =random.nextInt(900000)+100000;

        return currentTime.toString()+num;

    }

}
