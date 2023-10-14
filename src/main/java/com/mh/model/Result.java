package com.mh.model;

import lombok.Data;

/**
* @author xukh
* @date 2019/12/2 0002 18:00
*/
@Data
public class Result {
    private Boolean success;
    private String info;
    private Object data;
    public Result(){}
    public Result(Boolean success, String info, Object data) {
        this.success = success;
        this.info = info;
        this.data = data;
    }

    public static Result ok(){
        return new Result (true,null,null);
    }

    public static Result ok(String info){
        return new Result (true,info,null);
    }

    public static Result ok(String info, Object data){
        return new Result (true,info,data);
    }

    public static Result error(String info){
        return new Result (false,info,null);
    }

    public static Result error(String info, Object data){
        return new Result (false,info,data);
    }
}
