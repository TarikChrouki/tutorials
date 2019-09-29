package com.chrouki;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;

@Configuration
@ConfigurationProperties("app")
@Data
public class AppProperties {

    private String email;
    private String appName;

    private List<String> contacts;

    private Author author;

    private HashMap<String, String> site;

    private HashMap<String, Site> sites;

}


