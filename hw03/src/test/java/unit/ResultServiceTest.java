package unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.config.TestConfig;

import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;
import ru.otus.hw.service.*;


@DisplayName("Test: ResultServiceTest")
@ExtendWith(MockitoExtension.class)
public class ResultServiceTest {

    private IOService ioService;
    private ResultService resultService;

    @BeforeEach
    void setUp() {
        TestConfig testConfig = Mockito.mock(AppProperties.class);
        ioService = Mockito.mock(StreamsIOService.class);
        resultService = new ResultServiceImpl(testConfig, ioService);
    }

    @Test
    void showResult_verifyIoServiceIsCalling() {
        Student student = new Student("Ras", "Ras");
        TestResult testResult = new TestResult(student);
        testResult.setRightAnswersCount(4);

        resultService.showResult(testResult);

        Mockito.verify(ioService, Mockito.times(3)).printFormattedLine(Mockito.anyString(), Mockito.any());
        Mockito.verify(ioService, Mockito.times(3)).printLine(Mockito.anyString());
    }
}