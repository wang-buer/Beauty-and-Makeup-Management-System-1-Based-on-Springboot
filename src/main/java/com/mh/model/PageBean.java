package com.mh.model;

import lombok.Data;

/**
* @author xukh
* @date 2019/12/3 0003 09:12
*/
@Data
public class PageBean {
    private Integer page=1;
    private Integer limit=5;
}
