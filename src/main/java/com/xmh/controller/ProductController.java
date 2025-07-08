package com.xmh.controller;


import com.xmh.model.Product;
import com.xmh.model.ProductDetails;
import com.xmh.service.IProductDetailsService;
import com.xmh.service.IProductService;
import com.xmh.util.CategoryNode;
import com.xmh.util.JsonData;
import com.xmh.util.PageResult;
import com.xmh.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 向敏豪
 * @since 2023-02-05
 */
@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductDetailsService productDetailsService;

    //1、查找全部产品+分页+条件
    @GetMapping("/list")
    public JsonData findAll( @RequestParam(required = false,defaultValue = "1") int page,
                             @RequestParam(required = false,defaultValue = "50") int rows,
                             PageUtil pageUtil,
                             @ModelAttribute Product product,
                             @RequestParam(required = false) String ProductStatusA){
        System.out.println("page："+page+"，rows：" + rows + "，产品状态条件：" + ProductStatusA );
        pageUtil.setCurrent(page);
        pageUtil.setSize(rows);
        JsonData jsonData = new JsonData();
        if (ProductStatusA!=null){
            jsonData.setMessage("查询成功");
            //条件查询
            product.setListingPlatform(ProductStatusA);
        }

        PageResult<Product> list = productService.listProductAll(pageUtil, product);
        List<Product> studentList = list.getRows();
        System.out.println(studentList);
        pageUtil.setTotal(list.getTotal());
        System.out.println("页码："+pageUtil);
        jsonData.setPage(pageUtil.getCurrent());
        jsonData.setRows(pageUtil.getSize());
        jsonData.setTotal(pageUtil.getTotal());
        jsonData.setResult(studentList);
        jsonData.setCode(20000);
        return  jsonData;

    }

    //2、产品上架状态
    @GetMapping("/ProductStatus")
    public JsonData ProductStatus(){
        List<Map<String, String>> reviewProgressStatuses = productService.getReviewProgressStatuses();
        System.out.println(reviewProgressStatuses);
        JsonData jsonData = new JsonData();
        jsonData.setCode(20000);
        jsonData.setResult(reviewProgressStatuses);
        return jsonData;

    }
    //3、根据前端传来的/product/getProductById/${id}写一个方法
    @GetMapping("/getProductById/{id}")
    public JsonData getProductById(@PathVariable("id") String id){
        System.out.println("id："+id);
        Product product = productService.getProductById(id);
        System.out.println(product);
        JsonData jsonData = new JsonData();
        jsonData.setCode(20000);
        jsonData.setResult(product);
        return jsonData;
    }

    //4、查找全部产品详情+分页+条件
    @GetMapping("/productDetailsList")
    public JsonData findProductDetailsAll( @RequestParam(required = false,defaultValue = "1") int page,
                             @RequestParam(required = false,defaultValue = "50") int rows,
                             PageUtil pageUtil,
                             @ModelAttribute ProductDetails productDetails,
                             @RequestParam(required = false) String productLabelList,
                             @RequestParam(required = false) String CategorySelect,
                             @RequestParam(required = false) String GrowthData,
                             @RequestParam(required = false) String productGoodsId){
        System.out.println("page：" + page + "，rows：" + rows + "，产品状态条件：" +
                productLabelList + "，类目条件：" + CategorySelect +
                "，增长次数条件：" + GrowthData+ "，产品ID：" +productGoodsId);

        pageUtil.setCurrent(page);
        pageUtil.setSize(rows);
        JsonData jsonData = new JsonData();
        if (productLabelList!=null){
            //条件查询（产品机会选择）
            productDetails.setProductDefinitionType(productLabelList);
        }
        if (productGoodsId!=""){
            //条件查询（产品机会选择）
            productDetails.setProductGoodsId(productGoodsId);
        }
        if (GrowthData!=""){
            //条件查询（产品增长次数）
            productDetails.setDataGrowth(GrowthData);
        }
        // 设置 categorySelect
        if (CategorySelect != "" ) {
            System.out.println("类目条件：" + CategorySelect);
            productDetails.setCategorySelect(CategorySelect);
        }

        PageResult<ProductDetails> list = productDetailsService.listProductDetailsAll(pageUtil, productDetails);
        List<ProductDetails> studentList = list.getRows();
//        System.out.println(studentList);
        pageUtil.setTotal(list.getTotal());
        System.out.println("页码："+pageUtil);
        jsonData.setPage(pageUtil.getCurrent());
        jsonData.setRows(pageUtil.getSize());
        jsonData.setTotal(pageUtil.getTotal());
        jsonData.setResult(studentList);
        jsonData.setCode(20000);
        return  jsonData;

    }

    //5、获取产品机会标签
    @GetMapping("/productLabelList")
    public JsonData ProductLabelList(){
        List<Map<String, String>> productLabelList = productDetailsService.productLabelList();
        System.out.println(productLabelList);
        JsonData jsonData = new JsonData();
        jsonData.setCode(20000);
        jsonData.setResult(productLabelList);
        return jsonData;

    }
    //6、获取类目标签信息
    // 获取类目树
    @GetMapping("/categoryTree")
    public JsonData getCategoryTree() {
        List<CategoryNode> options = productDetailsService.buildCategoryTree();
        JsonData jsonData = new JsonData();
        jsonData.setCode(20000);
        jsonData.setResult(options);
        return jsonData;
    }

    //7、获取产品增长数据
    @GetMapping("/productGrowthData")
    public JsonData productGrowthData(){
        List<Map<String, String>> productGrowthData = productDetailsService.productGrowthData();
        System.out.println(productGrowthData);
        JsonData jsonData = new JsonData();
        jsonData.setCode(20000);
        jsonData.setResult(productGrowthData);
        return jsonData;
    }

    //8、获取产品增长销量数据
    @GetMapping("/productGrowthSalesData")
    public JsonData productGrowthSalesData(){
        List<Map<String, String>> productGrowthData = productDetailsService.productGrowthSalesData();
        System.out.println(productGrowthData);
        JsonData jsonData = new JsonData();
        jsonData.setCode(20000);
        jsonData.setResult(productGrowthData);
        return jsonData;
    }

    //9、每日数据更新视图
    @GetMapping("/dataUpdatesToday")
    public JsonData DataUpdatesToday(){
        List<Map<String, Object>> productGrowthData = productDetailsService.dataUpdatesToday();
        System.out.println(productGrowthData);
        JsonData jsonData = new JsonData();
        jsonData.setCode(20000);
        jsonData.setResult(productGrowthData);
        return jsonData;
    }

    //10、获取数据机会视图
    @GetMapping("/opportunityShare")
    public JsonData OpportunityShare(){
        List<Map<String, Object>> productGrowthData = productDetailsService.opportunityShare();
        System.out.println(productGrowthData);
        JsonData jsonData = new JsonData();
        jsonData.setCode(20000);
        jsonData.setResult(productGrowthData);
        return jsonData;
    }


}
