package com.xmh.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("temu_data_table_hm")
public class Product extends Model<Product> {
    //serialVersionUID含义：序列化版本控制，保证序列化版本一致性
    private static final long serialVersionUID = 1L;

    @TableId(value = "product_number", type = IdType.ASSIGN_ID)
    private Integer productNumber;
    @TableField("sku")
    private String sku;
    @TableField("link_alive")
    private String linkAlive;
    @TableField("shelf_time")
    private String shelfTime;
    @TableField("review_progress")
    private String reviewProgress;
    @TableField("data_update_time")
    private String dataUpdateTime;
    @TableField("listing_platform")
    private String listingPlatform;
    @TableField("img_url")
    private String imgUrl;
    @TableField("product_title")
    private String productTitle;
    @TableField("product_url")
    private String productUrl;
    @TableField("min_price")
    private String minPrice;
    @TableField("max_price")
    private String maxPrice;
    @TableField("product_goods_id")
    private String productGoodsId;
    @TableField("estimated_supply_price")
    private String estimatedSupplyPrice;
    @TableField("purchase_price")
    private String purchasePrice;
    @TableField("product_profit")
    private String productProfit;
    @TableField("product_profit_margin")
    private String productProfitMargin;



}

