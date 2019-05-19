package com.ray.controller;

import com.ray.Util.ResultVOUtil;
import com.ray.controller.VO.ProductInfoVO;
import com.ray.controller.VO.ProductVO;
import com.ray.controller.VO.ResultVO;
import com.ray.dataobject.ProductCategory;
import com.ray.dataobject.ProductInfo;
import com.ray.service.ProductCategoryService;
import com.ray.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品controller
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product",key="123")//第一次执行后将返回对象设置进入redis，第二次访问则直接查redis
    public ResultVO list(){
        //1.查询所有的上架商品
        List<ProductInfo> list = productInfoService.findUpAll();

        //2.查询类目（一次性查询）
        //List<Integer> categoryTypeList = new ArrayList<>();
        List<Integer> categoryTypeList = list.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCateType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO>  productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo:list){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO =new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
