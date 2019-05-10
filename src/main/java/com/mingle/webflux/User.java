package com.mingle.webflux;

/**
 * Created by mingle. Time 2019-05-04 01:32 Desc 文件描述
 */
public class User {
    private String id;
    private String name;
    private String email;
    
    public String getId() {
        return id;
    }
    
    
    public void setId(String id) {
        this.id = id;
    }
    
    
    public String getName() {
        return name;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    public String getEmail() {
        return email;
    }
    
    
    public void setEmail(String email) {
        this.email = email;
    }
}
