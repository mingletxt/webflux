package com.mingle.webflux.perf;

import lombok.Data;


/**
 * Created by mingle. Time 2019-05-04 23:04 Desc 文件描述
 */
@Data
public class AuthTokenResp {
    
    private String code;
    
    private String accessToken;
}
