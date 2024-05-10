package integration.dao;

import integration.config.IntegrationConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {IntegrationConfig.class})
public class CsvQuestionDaoTest {

    @Autowired
    private QuestionDao questionDao;


    @Test
    void finAllQuestionTest() {
        List<Question> questions = questionDao.findAll();
        System.out.println(questions);
        Assertions.assertEquals(4, questions.size());
    }
}
