package com.xmh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmh.mapper.ProductDetailsMapper;
import com.xmh.model.ProductDetails;
import com.xmh.service.IProductDetailsService;
import com.xmh.util.CategoryNode;
import com.xmh.util.PageResult;
import com.xmh.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductDetailsServiceImpl extends ServiceImpl<ProductDetailsMapper, ProductDetails> implements IProductDetailsService {

    @Autowired
    private ProductDetailsMapper productDetailsMapper;
    @Override
    public PageResult<ProductDetails> listProductDetailsAll(PageUtil pageUtil, ProductDetails productDetails) {
        Page<ProductDetails> page = new Page<>(pageUtil.getCurrent(), pageUtil.getSize());
        // 查询条件
        QueryWrapper<ProductDetails> queryWrapper = new QueryWrapper<>();
        if (productDetails != null) {
            //前端返回来的商品机会查询条件，这一步是为了查询指定机会条件
            queryWrapper.like(StringUtils.isNotBlank(productDetails.getProductDefinitionType()), "product_definition_type", productDetails.getProductDefinitionType());

            // 添加 data_growth 条件
            if (productDetails.getDataGrowth() != null) {
//                System.out.println("data_growth："+productDetails.getDataGrowth());
                queryWrapper.eq("data_growth", productDetails.getDataGrowth());
            }
            // 新增：添加 productGoodsId 条件
            if (StringUtils.isNotBlank(productDetails.getProductGoodsId())) {
                queryWrapper.eq("product_goods_id", productDetails.getProductGoodsId());
            }

            // 其他查询条件可以在这里添加
            // 处理 categorySelect
            System.out.println("判断："+StringUtils.isNotBlank(productDetails.getCategorySelect()));
            if (StringUtils.isNotBlank(productDetails.getCategorySelect())) {
                String[] categories = productDetails.getCategorySelect().split(",");
                if (categories.length > 0) {
                    String lastCategory = categories[categories.length - 1].trim().replaceAll("\"", "");
                    if (categories.length == 1) {
                        queryWrapper.eq("cat_one", lastCategory);
                    } else if (categories.length == 2) {
                        queryWrapper.eq("cat_two", lastCategory);
                    } else if (categories.length == 3) {
                        queryWrapper.eq("cat_three", lastCategory);
                    } else if (categories.length == 4) {
                        queryWrapper.eq("cat_four", lastCategory);
                    } else if (categories.length == 5) {
                        queryWrapper.eq("cat_five", lastCategory);
                    }
                }
            }
        }
        // 添加 soldtotal_growth 排序条件
        queryWrapper.orderByDesc("soldtotal_growth");
        // 分页查询
        this.page(page, queryWrapper);
        List<ProductDetails> records = page.getRecords();
//        System.out.println(records);
        PageResult<ProductDetails> result = new PageResult<>();
        result.setTotal(page.getTotal());
        result.setRows(records);
        return result;
    }

    @Override
    public List<Map<String, String>> productLabelList() {
        QueryWrapper<ProductDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT product_definition_type");
        List<Map<String, String>> objectList = productDetailsMapper.selectObjs(queryWrapper).stream()
                .map(obj -> {
                    Map<String, String> map = new HashMap<>();
                    String value = (String) obj;
                    map.put("value", value);
                    map.put("label", value);
                    return map;
                })
                .collect(Collectors.toList());
//        System.out.println(objectList);

        return objectList;
    }

    @Override
    public List<ProductDetails> getAllCategoryData() {
        QueryWrapper<ProductDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("cat_one", "cat_two", "cat_three", "cat_four", "cat_five");
        return list(queryWrapper);
    }
    @Override
    public List<CategoryNode> buildCategoryTree() {
        List<ProductDetails> allCategoryData = getAllCategoryData();

        Map<String, CategoryNode> categoryMap = new HashMap<>();

        for (ProductDetails productDetails : allCategoryData) {
            String catOne = productDetails.getCatOne();
            String catTwo = productDetails.getCatTwo();
            String catThree = productDetails.getCatThree();
            String catFour = productDetails.getCatFour();
            String catFive = productDetails.getCatFive();

            if (catOne != null && !catOne.isEmpty()) {
                CategoryNode levelOne = categoryMap.computeIfAbsent(catOne, k -> new CategoryNode(catOne, catOne));
                if (catTwo != null && !catTwo.isEmpty()) {
                    CategoryNode levelTwo = levelOne.getChildren().stream()
                            .filter(node -> catTwo.equals(node.getValue()))
                            .findFirst()
                            .orElseGet(() -> {
                                CategoryNode newNode = new CategoryNode(catTwo, catTwo);
                                levelOne.getChildren().add(newNode);
                                return newNode;
                            });
                    if (catThree != null && !catThree.isEmpty()) {
                        CategoryNode levelThree = levelTwo.getChildren().stream()
                                .filter(node -> catThree.equals(node.getValue()))
                                .findFirst()
                                .orElseGet(() -> {
                                    CategoryNode newNode = new CategoryNode(catThree, catThree);
                                    levelTwo.getChildren().add(newNode);
                                    return newNode;
                                });
                        if (catFour != null && !catFour.isEmpty()) {
                            CategoryNode levelFour = levelThree.getChildren().stream()
                                    .filter(node -> catFour.equals(node.getValue()))
                                    .findFirst()
                                    .orElseGet(() -> {
                                        CategoryNode newNode = new CategoryNode(catFour, catFour);
                                        levelThree.getChildren().add(newNode);
                                        return newNode;
                                    });
                            if (catFive != null && !catFive.isEmpty()) {
                                CategoryNode levelFive = levelFour.getChildren().stream()
                                        .filter(node -> catFive.equals(node.getValue()))
                                        .findFirst()
                                        .orElseGet(() -> {
                                            CategoryNode newNode = new CategoryNode(catFive, catFive);
                                            levelFour.getChildren().add(newNode);
                                            return newNode;
                                        });
                            }
                        }
                    }
                }
            }
        }

        return new ArrayList<>(categoryMap.values());
    }


    @Override
    public List<Map<String, String>> productGrowthData() {
        QueryWrapper<ProductDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT data_growth");
        List<Integer> collect = productDetailsMapper.selectObjs(queryWrapper).stream()
                .map(obj -> (Integer) obj)
                .collect(Collectors.toList());

        // 对 collect 列表进行排序
        Collections.sort(collect);

        // 将 List<Integer> 转换为 List<Map<String, String>>
        List<Map<String, String>> objectList = collect.stream()
                .map(value -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("value", value.toString());
                    map.put("label", value.toString());
                    return map;
                })
                .collect(Collectors.toList());

//        System.out.println(objectList);
        return objectList;
    }

    @Override
    public List<Map<String, String>> productGrowthSalesData() {
        QueryWrapper<ProductDetails> queryWrapper_Sales = new QueryWrapper<>();
        queryWrapper_Sales.select("DISTINCT soldtotal_growth");
        List<Integer> collect = productDetailsMapper.selectObjs(queryWrapper_Sales).stream()
                .map(obj -> (Integer) obj)
                .collect(Collectors.toList());

        // 对 collect 列表进行排序
        Collections.sort(collect);

        // 将 List<Integer> 转换为 List<Map<String, String>>
        List<Map<String, String>> objectList = collect.stream()
                .map(value -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("value", value.toString());
                    map.put("label", value.toString());
                    return map;
                })
                .collect(Collectors.toList());

//        System.out.println(objectList);
        return objectList;
    }

    @Override
    public List<Map<String, Object>> dataUpdatesToday() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        // 设置查询条件
        QueryWrapper<ProductDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("data_update", formattedDate)
                .eq("data_growth", 0);

        // 执行查询
        List<ProductDetails> productDetailsList = list(queryWrapper);

        // 用于存储累加结果的Map
        Map<String, Integer> categoryCountMap = new HashMap<>();

        // 遍历查询结果
        for (ProductDetails productDetails : productDetailsList) {
            String catOne = productDetails.getCatOne();
            if (catOne != null && !catOne.isEmpty()) {
                categoryCountMap.put(catOne, categoryCountMap.getOrDefault(catOne, 0) + 1);
            }
        }

        // 构建最终结果
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : categoryCountMap.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", entry.getKey());
            map.put("value", entry.getValue()); // 保持为 int 类型
            result.add(map);
        }

        return result;
    }

    @Override
    public List<Map<String, Object>> opportunityShare() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        // 设置查询条件
        // 构建查询条件
        QueryWrapper<ProductDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_definition_type", "新品增长款")
                     .eq("data_update", formattedDate)
                     .eq("data_growth", 0);

        // 执行查询
        List<ProductDetails> productDetailsList = list(queryWrapper);

        // 用于存储累加结果的Map
        Map<String, Integer> categoryCountMap = new HashMap<>();

        // 遍历查询结果
        for (ProductDetails productDetails : productDetailsList) {
            String catOne = productDetails.getCatOne();
            if (catOne != null && !catOne.isEmpty()) {
                categoryCountMap.put(catOne, categoryCountMap.getOrDefault(catOne, 0) + 1);
            } else {
                // 如果 cat_one 为空，则将其归类为 "暂无一级类目"
                categoryCountMap.put("暂无一级类目", categoryCountMap.getOrDefault("暂无一级类目", 0) + 1);
            }
        }

        // 构建最终结果
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : categoryCountMap.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", entry.getKey());
            map.put("value", entry.getValue()); // 保持为 int 类型
            result.add(map);
        }

        return result;
    }



}
