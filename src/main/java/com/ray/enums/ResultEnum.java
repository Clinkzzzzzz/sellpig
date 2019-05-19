package com.ray.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    ORDER_NO_EXIST(12,"订单不存在"),
    ORDER_DETAIL_NO_EXIST(13,"购物车不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单状态修改失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17,"订单订单支付状态不正确"),
    PARAM_ERROR(18,"参数不正确"),
    CART_EMPTY(19,"购物车为空"),
    ORDER_OWNER_ERROR(20,"该订单不属于当前用户"),
    WECHAT_MP_ERROR(21,"微信公众账号错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(22,"微信支付异步通知金额校验不通过"),
    ORDER_CANCEL_SUCCESS(23,"订单取消成功"),
    ORDER_FINISH_SUCCESS(24,"订单完成成功"),
    PRODUCT_STATUS_ERROR(25,"商品状态不正确"),
    LOGIN_FAIL(26,"登录失败，登录信息不正确"),
    LOGOUT_SUCCESS(27,"登出成功"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer i, String msg) {
        this.code=i;
        this.message=msg;
    }
}
