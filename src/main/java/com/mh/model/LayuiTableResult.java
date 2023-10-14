package com.mh.model;

import lombok.Data;

/**
 * @author xukh
 * @date 2019/12/2 0002 22:46
 */
@Data
public class LayuiTableResult {
    private int code;  //code 成功0

    private String msg;  //msg 信息

    private long count;  // 总记录数

    private Object data; // 数据

    public LayuiTableResult() {
    }

    public LayuiTableResult(int code, String msg, long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public static LayuiTableResult ok(long count, Object data) {
        return new LayuiTableResult(0, "", count, data);
    }


}
