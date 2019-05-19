package com.ray.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ray.Util.EnumUtil;
import com.ray.Util.serializer.Date2LongSerializer;
import com.ray.dataobject.OrderDetail;
import com.ray.enums.OrderStatusEnum;
import com.ray.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
public class OrderDTO {
    private String orderId;//订单id

    private String buyerName;//买家名字

    private String buyerPhone;//买家手机号

    private String buyerAddress;//买家地址

    private String buyerOpenid;//买家微信openid

    private BigDecimal orderAmount;//订单总金额

    private Integer orderStatus;//订单状态,默认为0新订单

    private Integer payStatus;//支付状态，默认为0未支付

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private  Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
