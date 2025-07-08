package com.xmh.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageUtil {
    private int current = 1;// 页码

    private int size = 50;// 页大小，row=0即不分页

    private int total = 0;// 总记录数

    /**
     *
     * @param total
     * intValue 这个方法即可以整型，也可以长整型
     */
    public void setTotal(Long total) {
        this.total = total.intValue();
    }
}
