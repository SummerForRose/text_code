package com.xmh.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 向敏豪
 * @since 2023-02-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_class")
public class Class extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    /**
     * 班级
     */
    private String cname;


}
