package com.xmh.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 类名：
 * 作者：Summer
 * 功能：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
	private long total;         // 总记录数
	private List<T> rows;       // 每页记录集合
}
