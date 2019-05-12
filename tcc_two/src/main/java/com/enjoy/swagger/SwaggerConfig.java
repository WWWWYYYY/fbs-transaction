package com.enjoy.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //让Spring来加载该类配置
@EnableWebMvc
@EnableSwagger2 //启用Swagger2
@ComponentScan(basePackages = { "com.enjoy" }) //指定Swagger扫面的包
public class SwaggerConfig {

  @Bean
  public Docket customDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
           .select()
           .apis(RequestHandlerSelectors.any())
           .paths(PathSelectors.any())
           .build()
           .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Peter的程序").build();
  }

}