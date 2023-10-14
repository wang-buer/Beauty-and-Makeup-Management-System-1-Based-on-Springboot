package com.mh.sys.service;


import com.mh.model.Menu;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface SysResourcesService {
    List<Menu> getMenu(HttpSession session);
}
