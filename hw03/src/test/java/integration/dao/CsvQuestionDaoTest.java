package integration.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Question;

import java.util.List;

public class CsvQuestionDaoTest {

    private QuestionDao questionDao;

    @BeforeEach
    void setUp() {

        AppProperties testFileNameProvider = Mockito.mock(AppProperties.class);
        Mockito.when(testFileNameProvider.getTestFileName()).thenReturn("questions-test.csv");
        Mockito.when(testFileNameProvider.getRightAnswersCountToPass()).thenReturn(3);
        questionDao = new CsvQuestionDao(testFileNameProvider);
    }

    @Test
    void finAllQuestionTest() {
        List<Question> questions = questionDao.findAll();
        Assertions.assertEquals(4, questions.size());
        Assertions.assertEquals("Bro, 2+2?", questions.get(0).text());
        Assertions.assertEquals("Bro,3*5?", questions.get(1).text());
    }

}
