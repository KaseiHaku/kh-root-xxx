package com.xxx.starter.basic.metadata;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties("com.xxx.starter.basic")
public class XxxStarterBasicProperties {

    private String key;
}
