package com.xxx.starter.basic.autoconfigure;

import com.kaseihaku.boot.starter.spring.bean.YmlDefaultPropertySourceFactory;
import com.xxx.starter.basic.metadata.XxxStarterBasicProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@AutoConfiguration
@PropertySource(value={"classpath:/com/xxx/starter/basic/config/application-xxx-basic.yml"},
    factory = YmlDefaultPropertySourceFactory.class)
@EnableConfigurationProperties({XxxStarterBasicProperties.class})
public class XxxBasicAutoConfiguration {
}
