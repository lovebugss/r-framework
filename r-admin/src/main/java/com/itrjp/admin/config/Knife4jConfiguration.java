package com.itrjp.admin.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;

/**
 * Knife4jConfiguration
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/1 14:25
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "dockerBean")
    public Docket dockerBean() {

        Parameter token = new ParameterBuilder()
                .name("Authorization").description("登录凭证")
                .defaultValue("Bearer ")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build();

        //指定使用Swagger2规范
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(Collections.singletonList(token))
                .apiInfo(new ApiInfoBuilder()
                        //描述字段支持Markdown语法
                        .description("#后台管理")
                        .termsOfServiceUrl("https://admin.itrjp.com/")
                        .contact(new Contact("任大忽悠", "https://itrjp.com", "r979668507@gmail.com"))
                        .version("1.0")
                        .title("橙小康运营后台API文档")
                        .build())
                //分组名称
                .groupName("运营后台")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }
}
