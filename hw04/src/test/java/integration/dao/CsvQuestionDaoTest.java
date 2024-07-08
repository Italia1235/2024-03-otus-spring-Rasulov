package integration.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Question;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CsvQuestionDao.class})
public class CsvQuestionDaoTest {
    @Autowired
    private QuestionDao questionDao;
    @MockBean
    private TestFileNameProvider fileNameProviderMock;

    @Test
    void finAllQuestionTest() {
        when(fileNameProviderMock.getTestFileName()).thenReturn("questions-test.csv");
        List<Question> questions = questionDao.findAll();
        Assertions.assertEquals(4, questions.size());
        Assertions.assertEquals("Bro, 2+2?", questions.get(0).text());
        Assertions.assertEquals("Bro,3*5?", questions.get(1).text());
    }

}
