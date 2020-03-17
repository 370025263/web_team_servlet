package com.major.model;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@org.springframework.context.annotation.Configuration
@ComponentScan({"com.major.model","com.google.gson","com.major.web"} )


public class Configuration {
    public Configuration(){System.out.println("TestConfiguration容器启动初始化。。。");
    }

@Bean(name = "gson")
    public Gson createGson(){
        return new Gson();
}

}
