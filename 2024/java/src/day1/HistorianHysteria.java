package day1;

import setup.Problem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class HistorianHysteria extends Problem<Integer> {

    private static final String WHITESPACE = "\\s+";
    private static final String INPUT_FILE_PATH = "src\\day1\\input.txt";

    public HistorianHysteria(String inputFilePath) {
        super(inputFilePath);
    }

    public static void main(String[] args) {
        var day1 = new HistorianHysteria(INPUT_FILE_PATH);
        day1.executeWithRuntime();
    }

    @Override
    public Integer partOne() {
        var left = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();
        this.inputLines.forEach(readInputIntoCollections(left, right));
        left.sort(Comparator.naturalOrder());
        right.sort(Comparator.naturalOrder());

        int size = Math.min(left.size(), right.size());
        int totalDistance = 0;

        for (int i = 0; i < size; i++) {
            int leftElement = left.get(i);
            int rightElement = right.get(i);
            totalDistance += Math.abs(leftElement - rightElement);
        }
        return totalDistance;
    }

    @Override
    public Integer partTwo() {
        var left = new ArrayList<Integer>();
        var occurrences = new HashMap<Integer, Integer>();
        this.inputLines.forEach(readInputIntoCollections(left, occurrences));

        int distance = 0;
        for (var elem : left) {
            distance += elem * occurrences.getOrDefault(elem, 0);
        }
        return distance;
    }

    private static Consumer<String> readInputIntoCollections(Collection<Integer> left, Collection<Integer> right) {
        return line -> {
            var parts = line.split(WHITESPACE);
            left.add(Integer.parseInt(parts[0]));
            right.add(Integer.parseInt(parts[1]));
        };
    }

    private static Consumer<String> readInputIntoCollections(Collection<Integer> left, Map<Integer, Integer> occurrences) {
        return line -> {
            var parts = line.split(WHITESPACE);
            left.add(Integer.parseInt(parts[0]));
            occurrences.compute(Integer.parseInt(parts[1]), (unused, v) -> v == null ? 1 : v + 1);
        };
    }
}
