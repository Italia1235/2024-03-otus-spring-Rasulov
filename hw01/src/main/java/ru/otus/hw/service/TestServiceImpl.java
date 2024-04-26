package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;
    private final QuestionDao csvQuestionDao;
    @Override
    public void executeTest() {

        // Получить вопросы из дао и вывести их с вариантами ответов

        for (Question question : csvQuestionDao.findAll()) {
            ioService.printLine(question.text());
        }

    }
}
