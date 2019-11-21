package grow.demo.config;

import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

//restDoccs 문서에서 response 응답들을 이쁘게 나오게 하기위해서 설정값을 변경하는 과정
@TestConfiguration
public class RestDocsConfiguration {

    @Bean
    RestDocsMockMvcConfigurationCustomizer restDocsMockMvcConfigurationCustomizer(){
        return new RestDocsMockMvcConfigurationCustomizer() {
            @Override
            public void customize(org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer configurer) {
                configurer.operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint());
            }
        };
    }
}
