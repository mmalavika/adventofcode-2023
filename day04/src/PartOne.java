import java.util.Collections;
import java.util.List;

public class PartOne {
    public static int computePointsTotal() {
        String[] inputArr = Input.INPUT.split("\\n");
        int total = 0;

        for(String input : inputArr) {
            var numString = input.substring(input.indexOf(":")+1);
            var winningNumbers = new java.util.ArrayList<>(List.of(numString.substring(0, numString.indexOf("|") - 1).split(" ", -1)));
            var numToCheck = new java.util.ArrayList<>(List.of(input.substring(input.indexOf("|") + 1).split(" ", -1)));
            winningNumbers.removeAll(Collections.singleton(""));
            numToCheck.removeAll(Collections.singleton(""));
            var score = 0;
            for(String num : numToCheck) {
                if(winningNumbers.contains(num)) {
                    if(score == 0) {
                        score = 1;
                    } else {
                        score = score * 2;
                    }
                }
            }
            total = total + score;
        }

        return total;
    }
}
