package com.tmasolutions.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.tmasolutions.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(metaData());
//    }
//
//    private ApiInfo metaData(){
//        return new ApiInfoBuilder()
//                .title("TMA Spring Boot demo Swagger")
//                .description("\"Java-sample Online store\"")
//                .version("1.0.1")
//                .license("Apache License Version 2.0")
//                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
//                .contact(new Contact("Thanh Cong", "https://www.tmasolutions.com", "ntcong2@tma.com.vn"))
//                .build();
//    }
}
