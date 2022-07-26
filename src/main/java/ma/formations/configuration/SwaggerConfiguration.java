package ma.formations.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 7/5/2022 9:48 PM
 */

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket productApi(){

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ma.formations"))
                .build();
    }
}
