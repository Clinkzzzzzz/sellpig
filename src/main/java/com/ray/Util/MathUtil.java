package com.ray.Util;

public class MathUtil {
    private static final  double MONEY_RANGE=0.01;//精度
    /**
     * 比较两个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static  boolean equals(double d1,double d2){
        double result = Math.abs(d1-d2);
        if(result < MONEY_RANGE){
            return true;
        }else return false;
    }
}
