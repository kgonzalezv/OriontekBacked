package com.example.oriontek.config;

import com.example.oriontek.controller.AddressResource;
import com.example.oriontek.model.Address;
import com.example.oriontek.controller.CustomerResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseryConfig  extends ResourceConfig {

    public  JerseryConfig(){
        packages("com.example.oriontek.controller");
    }
}
