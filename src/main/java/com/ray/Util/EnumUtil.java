package com.ray.Util;

import com.ray.enums.CodeEnum;


//java.lang.Class.getEnumConstants() 返回枚举类的元素，或null如果此Class对象不表示枚举类型。

/**
 * 根据code的返回枚举的元素
 * 工具类
 */
public class EnumUtil {
    public  static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for(T each:enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
