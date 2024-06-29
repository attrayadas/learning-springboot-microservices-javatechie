package com.attraya.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    /**
     *
     * If Spring Boot finds prod.datasource.enabled=true, then enable ProdDatasource
     * @return
     */
    @Bean
    @Primary
    @ConditionalOnProperty(name="prod.datasource.enabled", havingValue = "true")
    public DataSourceConfig enableProdDatabase(){
        return new EnableProdDataSource();
    }

    /**
     * If the EnableProdDataSource bean is missing, then only create the DevDataSource
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(EnableProdDataSource.class)
    public EnableDevDataSource enableDevDatabase(){
        return new EnableDevDataSource();
    }
}
