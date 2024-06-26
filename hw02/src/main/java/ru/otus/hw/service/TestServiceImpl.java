package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var question: questions) {
            printQuestion(question);
            var actualAnswer = actualAnswer(question);
            testResult.applyAnswer(question, actualAnswer.isCorrect());
        }
        return testResult;
    }

    private void printQuestion(Question question) {
        ioService.printLine(question.text());
        for (int i = 0; i < question.answers().size(); i++) {
            ioService.printFormattedLine("\t[%d]: %s", i, question.answers().get(i).text());
        }
    }

    private Answer actualAnswer(Question question) {
        var actualAnswerId = actualAnswerId(question);
        return question.answers().get(actualAnswerId);
    }

    private int actualAnswerId(Question question) {
        var min = 0;
        var max = question.answers().size() - 1;
        return ioService.readIntForRange(min, max, String.format("Invalid answer. Use [%d;%d]", min, max));
    }
}
