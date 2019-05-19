package com.ray.controller.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品（包含类目）
 */
@Data
public class ProductVO implements Serializable {


    private static final long serialVersionUID = 8003509124539716034L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer cateType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
