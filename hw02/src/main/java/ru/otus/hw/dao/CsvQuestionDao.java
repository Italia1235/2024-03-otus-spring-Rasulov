package ru.otus.hw.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.util.List;


@RequiredArgsConstructor
@Component
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        var testFileName = fileNameProvider.getTestFileName();
        var inputStream = this.getClass().getClassLoader().getResourceAsStream(testFileName);

        if (inputStream == null) {
            throw new QuestionReadException("Resource with tests not found!");
        }

        try (inputStream) {
            var bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            return new CsvToBeanBuilder<QuestionDto>(bufferedReader)
                    .withType(QuestionDto.class)
                    .withSeparator(':')
                    .withSkipLines(1)
                    .build()
                    .parse()
                    .stream()
                    .map(QuestionDto::toDomainObject)
                    .toList();
        } catch (Exception e) {
            throw new QuestionReadException("CSV parse1 failed", e);
        }
    }
}