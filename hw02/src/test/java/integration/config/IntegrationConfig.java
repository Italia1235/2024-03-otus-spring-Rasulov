package integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.dao.QuestionDao;


@Configuration
@PropertySource("classpath:application-test.properties")
public class IntegrationConfig {

    @Bean
    public TestFileNameProvider testFileNameProvider() {
        return new AppProperties();
    }

    @Bean
    public QuestionDao questionDao(TestFileNameProvider testFileNameProvider) {
        return new CsvQuestionDao(testFileNameProvider);
    }
}