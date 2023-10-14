package com.mh.model;

import lombok.Data;

import java.util.List;

/**
* @author xukh
* @date 2019/12/5 0005 14:40
*/
@Data
public class TreeNode {
    private Integer id;
    private String title;
    private String state ;
    private Boolean checked;
    private List<TreeNode> children;
}
