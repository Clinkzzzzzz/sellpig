package com.ray.dataobject;

import com.ray.enums.OrderStatusEnum;
import com.ray.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;//订单id

    private String buyerName;//买家名字

    private String buyerPhone;//买家手机号

    private String buyerAddress;//买家地址

    private String buyerOpenid;//买家微信openid

    private BigDecimal orderAmount;//订单总金额

    private Integer orderStatus= OrderStatusEnum.NEW.getCode();//订单状态,默认为0新订单

    private Integer payStatus= PayStatusEnum.WAIT.getCode();//支付状态，默认为0未支付

    private Date createTime;

    private  Date updateTime;

}
