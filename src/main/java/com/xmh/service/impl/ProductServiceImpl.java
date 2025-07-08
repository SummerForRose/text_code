package com.xmh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmh.mapper.ProductMapper;
import com.xmh.model.Product;
import com.xmh.model.ProductDetails;
import com.xmh.service.IProductService;
import com.xmh.util.PageResult;
import com.xmh.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageResult<Product> listProductAll(PageUtil pageUtil, Product product) {
        Page<Product> page = new Page<>(pageUtil.getCurrent(), pageUtil.getSize());
        // 查询条件
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (product != null) {
            queryWrapper.like(StringUtils.isNotBlank(product.getListingPlatform()), "review_progress", product.getListingPlatform());
            // 其他查询条件可以在这里添加
        }
        // 分页查询
        this.page(page, queryWrapper);
        List<Product> records = page.getRecords();
//        System.out.println(records);
//        for (Product p : records) {
////            System.out.println(p);
//        }
        PageResult<Product> result = new PageResult<>();
        result.setTotal(page.getTotal());
        result.setRows(records);
        return result;
    }

    @Override
    public List<Map<String, String>> getReviewProgressStatuses() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT review_progress");
        List<Object> reviewProgressObjects = productMapper.selectObjs(queryWrapper);
        // 将 List<Object> 转换为 List<String>
        List<Map<String, String>> reviewProgressStatuses = reviewProgressObjects.stream()
                .map(obj -> {
                    Map<String, String> map = new HashMap<>();
                    String value = (String) obj;
                    map.put("value", value);
                    map.put("label", value);
                    return map;
                })
                .collect(Collectors.toList());
        return reviewProgressStatuses;
    }

    @Override
    public Product getProductById(String id) {
        //更加id去查数据库字段为product_goods_id数据
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_goods_id", id);
        Product product = productMapper.selectOne(queryWrapper);
        if (product != null) {
            return product;
        }

        return null;
    }




}
