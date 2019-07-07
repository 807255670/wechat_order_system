package com.hodor.demo.VO;

import lombok.Data;

import java.util.List;

/**
 * http请求返回的最外层对象
 * Created By Fan Huiliang
 * 2019-07-07 11:54
 */
@Data
public class ResultVO<T> {

    /*错误码*/
    private Integer code;

    /*提示信息*/
    private String message;

    /*具体内容*/
    private T data;

}
