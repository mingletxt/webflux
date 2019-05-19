package com.mingle.webflux.perf;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;


/**
 * Created by mingle. Time 2019-04-24 15:43 Desc 文件描述
 */
@Data
@ToString(callSuper = true)
public class AuthValidateResp implements Serializable {
    
    private static final long serialVersionUID = -1725414280826561654L;
    
    private String code;
    
    private String msg;
    
    private String csrfToken;
    
    private Long transactionTime;
    
    private boolean result;
    
}
