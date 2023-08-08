package com.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2配置类  接口文档
 * @author ldw
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 规定扫描哪些包下面要生成接口文档
     * @return
     */
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.server.controller"))
                .paths(PathSelectors.any())
                .build()
                //配置全局的登录授权，authorization,这样在调试的时候就不用每次都携带jwt了
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("云上OA接口文档")
                .description("云上OA接口文档")
                .contact(new Contact("ldw", "http:localhost:8081/doc.html", "2632711107@qq.com"))
                .version("1.0")
                .build();
    }

    private List<SecurityContext>securityContexts(){
        //设置需要认证的路径，必需登录之后才能访问的接口路径
        List<SecurityContext> result=new ArrayList<>();
        // /hello/.*表示hello下的所有接口都要
        result.add(getContextByPath("/hello/.*"));
        return result;
    }
    private List<ApiKey> securitySchemes(){
        //设置请求头信息
        List<ApiKey> result=new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization","Authorization", "Header");
        result.add(apiKey);
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result=new ArrayList<>();
        //授权范围
        AuthorizationScope authorizationScope=new AuthorizationScope(
                "global",
                "accessEverything");
        AuthorizationScope[] authorizationScopes=new AuthorizationScope[1];
        authorizationScopes[0]=authorizationScope;
        //因为SecurityReference参数需要的是数组，所以需要封装
        result.add(new SecurityReference("Authorization",authorizationScopes));
        return result;
    }

}
