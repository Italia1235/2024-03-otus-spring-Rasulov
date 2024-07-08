package ru.otus.hw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import ru.otus.hw.service.TestRunnerService;

@ShellComponent
@Service
@RequiredArgsConstructor
public class ShellRunner {

    private final TestRunnerService testRunnerService;
    @ShellMethod(value = "start",key ={"s","start","run","go","g"})
    public void startTest() {
        testRunnerService.run();
    }

}
