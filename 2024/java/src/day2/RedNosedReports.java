package day2;

import setup.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RedNosedReports extends Problem<Integer> {

    private static final String INPUT_FILE_PATH = "src\\day2\\input.txt";
    private static final String WHITESPACE = "\\s+";

    public RedNosedReports(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public Integer partOne() {
        return ((int) inputLines.stream()
                .map(line -> isSafe(Arrays.stream(line.split(WHITESPACE)).map(Integer::valueOf).toList()))
                .filter(i -> i == -1)
                .count());
    }

    @Override
    public Integer partTwo() {
        return ((int) inputLines.stream()
                .map(line -> Arrays.stream(line.split(WHITESPACE)).map(Integer::valueOf).toList())
                .map(this::isSafeWithRemoval)
                .filter(b -> b)
                .count());
    }

    private boolean isSafeWithRemoval(List<Integer> array) {
        int faultyIndex = isSafe(array);
        if (faultyIndex == -1) {
            return true;
        }

        if (faultyIndex == 1) {
            if (isSafe(getCopyWithElementAtIndexRemoved(array, 0)) == -1) {
                return true;
            }
        }

        if (isSafe(getCopyWithElementAtIndexRemoved(array, faultyIndex)) == -1) {
            return true;
        }

        return isSafe(getCopyWithElementAtIndexRemoved(array, faultyIndex + 1)) == -1;
    }

    private List<Integer> getCopyWithElementAtIndexRemoved(List<Integer> array, int index) {
        var copy = new ArrayList<>(array);
        copy.remove(index);
        return copy;
    }

    /**
     * @return index of the element for potential removal, -1 if isSafe
     */
    private int isSafe(List<Integer> array) {
        int i = 0, j = i + 1;
        int current, next;
        boolean increasingSeries = isIncreasingSeries(array);
        while (j < array.size()) {
            current = array.get(i);
            next = array.get(j++);
            if (!acceptableLevelDistance(current - next) || !isMonotonicSeries(increasingSeries, current, next)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private boolean isIncreasingSeries(List<Integer> array) {
        return array.get(0) < array.get(1);
    }

    private boolean acceptableLevelDistance(Integer num) {
        return Math.abs(num) > 0 && Math.abs(num) <= 3;
    }

    private boolean isMonotonicSeries(boolean increasing, Integer num1, Integer num2) {
        return increasing ? num1 < num2 : num1 > num2;
    }

    public static void main(String[] args) {
        var day2 = new RedNosedReports(INPUT_FILE_PATH);
        day2.executeWithRuntime();
    }

}
