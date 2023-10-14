package com.mh.util;


import com.mh.sys.entity.Emp;

import javax.servlet.http.HttpSession;

/**
* @author xukh
* @date 2019/12/3 0003 10:37
*/
public class SessionUtil {
    public static Emp getUser(HttpSession session) {
        return (Emp) session.getAttribute(ConstUtil.USER);
    }

    public static Integer getUserId(HttpSession session) {
        return getUser(session).getId();
    }

    public static void addUser(HttpSession session, Emp user) {
        session.setAttribute(ConstUtil.USER, user);
    }

}
