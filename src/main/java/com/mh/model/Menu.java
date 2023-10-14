package com.mh.model;

import lombok.Data;

import java.util.List;

/***
* @author xukh
* @date 2019/12/2 0002 22:46
*/
@Data
public class Menu {

    private String title;
    private String href;
    private String icon;
    private String target;
    private Integer id;
    private Integer pid;
    private List<Menu> child;
}
