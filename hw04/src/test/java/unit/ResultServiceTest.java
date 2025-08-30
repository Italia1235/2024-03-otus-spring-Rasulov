package unit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.hw.config.TestConfig;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;
import ru.otus.hw.service.LocalizedIOService;
import ru.otus.hw.service.ResultServiceImpl;

import static org.mockito.Mockito.*;


@SpringBootTest(classes = ResultServiceImpl.class)
public class ResultServiceTest {

    @Autowired
    private ResultServiceImpl resultService;

    @MockBean
    private LocalizedIOService ioService;

    @MockBean
    private TestConfig testConfig;

    @Test
    void showResult_verifyIoServiceIsCalling() {
        when(testConfig.getRightAnswersCountToPass()).thenReturn(3);
        var student = new Student("RAS", "ras");
        var testResults = new TestResult(student);

        testResults.setRightAnswersCount(3);

        resultService.showResult(testResults);

        verify(ioService, times(2)).printLineLocalized(anyString());
        verify(ioService, times(1))
                .printFormattedLineLocalized("ResultService.student", testResults.getStudent().getFullName());
        verify(ioService, times(1))
                .printFormattedLineLocalized("ResultService.answered.questions.count", testResults.getAnsweredQuestions().size());
        verify(ioService, times(1))
                .printFormattedLineLocalized("ResultService.right.answers.count", testResults.getRightAnswersCount());
        verify(testConfig, times(1)).getRightAnswersCountToPass();
    }
}