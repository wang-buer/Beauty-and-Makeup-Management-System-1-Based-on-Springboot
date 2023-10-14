package com.mh.sys.controller;

import com.mh.sys.entity.Emp;
import com.mh.util.ServletUtil;
import com.mh.util.SessionUtil;
import lombok.Data;

/**
* @author xukh
* @date 2019/12/3 0003 10:41
*/
@Data
public class BaseController {
    //可以直接取session中的用户
    public Emp getUser() {
        return SessionUtil.getUser (ServletUtil.getSession ());
    }
    //可以直接取session中的用户ID
    public Integer getUserid(){
        return SessionUtil.getUserId (ServletUtil.getSession ());
    }
}
