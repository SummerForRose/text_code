package com.xmh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmh.model.ProductDetails;
import com.xmh.util.CategoryNode;
import com.xmh.util.PageResult;
import com.xmh.util.PageUtil;

import java.util.List;
import java.util.Map;

public interface IProductDetailsService extends IService<ProductDetails> {

    //获取所有产品描述
    PageResult<ProductDetails> listProductDetailsAll(PageUtil pageUtil, ProductDetails productDetails);

    //获取产品机会标签
    List<Map<String, String>> productLabelList();

    //遍历类目
    List<ProductDetails> getAllCategoryData();

    List<CategoryNode> buildCategoryTree();

    //获取数据增长次数
    List<Map<String, String>> productGrowthData();

    //获取数据增长销量
    List<Map<String, String>> productGrowthSalesData();

    //获取今日数据更新数据
    List<Map<String, Object>> dataUpdatesToday();

    //获取今日数据更新情况
    List<Map<String, Object>> opportunityShare();





}
