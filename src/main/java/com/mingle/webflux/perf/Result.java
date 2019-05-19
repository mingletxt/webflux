package com.mingle.webflux.perf;

import java.io.Serializable;

import lombok.Data;


/**
 * Created by mingle. Time 2019-04-25 14:38 Desc 文件描述
 */
@Data
public class Result implements Serializable {
    
    private static final long serialVersionUID = 3285590480513275854L;
    
    private int code;
    
    private String msg;
    
}
