package ru.otus.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Question;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.TestServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Test: TestServiceImplTest")
@ExtendWith(MockitoExtension.class)
public class TestServiceImplTest {

    @Mock
    private IOService ioService;

    @Mock
    private QuestionDao csvQuestionDao;

    @InjectMocks
    private TestServiceImpl testService;


    @Test
    public void testExecuteTest() {

        List<Question> questions = Arrays.asList(new Question("Question 1", null), new Question("Question 2", null));

        when(csvQuestionDao.findAll()).thenReturn(questions);

        testService.executeTest();

        verify(ioService).printLine("Question 1");
        verify(ioService).printLine("Question 2");
    }
}