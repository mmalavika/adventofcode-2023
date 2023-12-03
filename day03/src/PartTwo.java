import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PartTwo {
    public static int computeGearRatiosTotal() {
        String[] inputArr = Input.INPUT.split("\\n");
        int total = 0;

        for(int i = 0; i < inputArr.length; i++) {
            String prevLine = null;
            String currentLine = inputArr[i];
            String nextLine = null;
            if (i >= 1) {
                prevLine = inputArr[i - 1];

            }
            if (i < inputArr.length - 1) {
                nextLine = inputArr[i + 1];
            }

            Pattern p = Pattern.compile("\\*");
            Matcher matcher = p.matcher(currentLine);

            while(matcher.find()) {
                var pos = matcher.start();
                var posBefore = pos-1;
                var posAfter = pos+1;
                List<Integer> gearNums = new ArrayList<>();

                Stream.of(prevLine, currentLine, nextLine).filter(Objects::nonNull).forEach(line -> {
                    Pattern pNum = Pattern.compile("\\d{1,}");
                    Matcher numMatcher = pNum.matcher(line);

                    while(numMatcher.find()) {
                        var numberStartPoint = numMatcher.start();
                        var numberEndPoint = numberStartPoint + numMatcher.group().length()-1;
                        List<Integer> gearPositions = List.of(posBefore, pos, posAfter);

                        if(gearPositions.contains(numberStartPoint) || gearPositions.contains(numberEndPoint)) {
                            gearNums.add(Integer.parseInt(numMatcher.group()));
                        }
                    }
                });

                if(gearNums.size() == 2) {
                    var gearNumber = gearNums.get(0) * gearNums.get(1);
                    total = total + gearNumber;
                }
            }
        }

        return total;
    }
}
