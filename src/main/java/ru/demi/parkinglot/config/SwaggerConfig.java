package ru.demi.parkinglot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.demi.parkinglot.swagger.AuthOperations;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiModelReader;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.demi.parkinglot.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Primary
    @Bean
    public ApiListingScanner addExtraOperations(
            ApiDescriptionReader apiDescriptionReader,
            ApiModelReader apiModelReader,
            DocumentationPluginsManager pluginsManager
    ) {
        return new AuthOperations(apiDescriptionReader, apiModelReader, pluginsManager);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("Spring Boot REST API for Parking lot app.")
                .version("1.0.0")
                .license("MIT License")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .contact(new Contact("Dmitry Izmerov", "https://www.github.com/dmitry-izmerov", ""))
                .build();
    }
}
