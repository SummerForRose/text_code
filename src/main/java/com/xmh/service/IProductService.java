package com.xmh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmh.model.Product;
import com.xmh.model.ProductDetails;
import com.xmh.util.PageResult;
import com.xmh.util.PageUtil;

import java.util.List;
import java.util.Map;

public interface IProductService extends IService<Product> {

    //产品列表
    PageResult<Product> listProductAll(PageUtil pageUtil, Product product);

    //产品上架状态
    List<Map<String, String>> getReviewProgressStatuses();

    //根据产品id查询产品信息
    Product getProductById(String id);


}
