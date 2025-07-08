package com.xmh.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("temu_product_library")
public class ProductDetails  extends Model<ProductDetails> {
    //serialVersionUID含义：序列化版本控制，保证序列化版本一致性
    private static final long serialVersionUID = 1L;

    @TableId(value = "product_id", type = IdType.ASSIGN_ID)
    private Integer productId;
    @TableField("product_title")
    private String productTitle;
    @TableField("img_url")
    private String imgUrl;
    @TableField("product_url")
    private String productUrl;
    @TableField("min_price")
    private String minPrice;
    @TableField("max_price")
    private String maxPrice;
    @TableField("stock")
    private String stock;
    @TableField("total_sales")
    private String totalSales;
    @TableField("daily_sales")
    private String dailySales;
    @TableField("weekly_sales")
    private String weeklySales;
    @TableField("monthly_sales")
    private String monthlySales;
    @TableField("total_salesamount")
    private String totalSalesAmount;
    @TableField("daily_exposure")
    private String dailyExposure;
    @TableField("weekly_exposure")
    private String weeklyExposure;
    @TableField("monthly_exposure")
    private String monthlyExposure;
    @TableField("shop_link")
    private String shopLink;
    @TableField("first_time_jjy")
    private String firstTimeJjy;
    @TableField("update_time_jjy")
    private String updateTimeJjy;
    @TableField("product_goods_id")
    private String productGoodsId;
    @TableField("product_definition_type")
    private String productDefinitionType;
    @TableField("cat_one")
    private String catOne;
    @TableField("cat_two")
    private String catTwo;
    @TableField("cat_three")
    private String catThree;
    @TableField("cat_four")
    private String catFour;
    @TableField("cat_five")
    private String catFive;
    @TableField("data_update")
    private String dataUpdate;
    @TableField("data_growth")
    private String dataGrowth;
    @TableField("soldtotal_growth")
    private String soldtotalGrowth;
    @TableField("user_id")
    private String userId;
    //categoryId不是数据库字段
    @TableField(exist = false) // 表示该字段不是数据库字段，不需要进行数据库操作
    private String categorySelect;
    @TableField(exist = false) // 表示该字段不是数据库字段，不需要进行数据库操作
    private String username;
    @TableField(exist = false) // 表示该字段不是数据库字段，不需要进行数据库操作
    private int a;

    // Getter 和 Setter 方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
