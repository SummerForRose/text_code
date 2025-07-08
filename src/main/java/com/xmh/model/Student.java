package com.xmh.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 向敏豪
 * @since 2023-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)// 链式编程
@TableName("t_student")
public class Student extends Model {

    //serialVersionUID含义
    private static final long serialVersionUID = 1L;

    @TableId(value = "sid", type = IdType.ASSIGN_ID)
    private String sid;

    private String sname;

    private String sex;

    private Integer age;

    private Integer cid;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date great_time;

    @TableField(fill = FieldFill.UPDATE)
    private Date update_time;

    @TableLogic(value = "0",delval = "1")
    private Integer deleted;

    @TableField(value = "未入学",exist = false)
    private String cname;


}
