package io.basis.postservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.basis.postservice"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(generateApiInfo()).directModelSubstitute(Timestamp.class, Long.class);
    }
    private ApiInfo generateApiInfo() {
        return new ApiInfo("BASIS", "Post", "Version 1.0", "urn:tos", "test", "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0");
    }
}
