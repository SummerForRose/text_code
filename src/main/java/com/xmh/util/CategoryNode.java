package com.xmh.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryNode {
    private String value;
    private String label;
    private List<CategoryNode> children;

    public CategoryNode(String value, String label) {
        this.value = value;
        this.label = label;
        this.children = new ArrayList<>();
    }
}