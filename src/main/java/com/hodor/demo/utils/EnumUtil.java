package com.hodor.demo.utils;

import com.hodor.demo.enums.CodeEnum;

/**
 * Created By Fan Huiliang
 * 2019-07-16 23:11
 */
public class EnumUtil {

    public static<T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
        for(T each:enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
