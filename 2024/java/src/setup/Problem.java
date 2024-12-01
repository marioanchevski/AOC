package setup;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Problem<T> {

    protected List<String> inputLines;

    public Problem(String inputFilePath) {
        try(var reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath)))) {
            inputLines = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("File does not exist: " + inputFilePath);
        }
    }

    public abstract T partOne();

    public abstract T partTwo();

    protected void executeWithRuntime() {
        var currentTime = System.currentTimeMillis();
        System.out.printf("Solution part one: %s, runtime: %dms\n", partOne(), System.currentTimeMillis() - currentTime);

        currentTime = System.currentTimeMillis();
        System.out.printf("Solution part two: %s, runtime: %dms\n", partTwo(), System.currentTimeMillis() - currentTime);
    }

}
