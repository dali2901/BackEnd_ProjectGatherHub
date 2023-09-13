package com.gatherhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    Boolean swaggerEnabled = true;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .enable(swaggerEnabled)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.connectu.connectuapi.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .ignoredParameterTypes(HttpSession.class);
                //--------------------------------------------
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gatherhub.controller")) // 指定API控制器所在的包
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("gatherhub")
                //创建人
                //.contact(new Contact("clc", "http://www.baidu.com", "clc@x.com"))
                .version("1.0")
                // .description("不需要描述")
                .build();
    }

}
