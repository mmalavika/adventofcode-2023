import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartTwo {
    public static int computeTotalCalibrationSpelled() {
        String[] inputArr = Input.INPUT.split("\\n");

        int total = 0;

        for(String input : inputArr) {
            Map<String, String> numMap = new HashMap<>();
            numMap.put("one", "1");
            numMap.put("two", "2");
            numMap.put("three", "3");
            numMap.put("four", "4");
            numMap.put("five", "5");
            numMap.put("six", "6");
            numMap.put("seven", "7");
            numMap.put("eight", "8");
            numMap.put("nine", "9");

            Map<String, String> numMapReverse = new HashMap<>();
            numMapReverse.put("eno", "1");
            numMapReverse.put("owt", "2");
            numMapReverse.put("eerht", "3");
            numMapReverse.put("ruof", "4");
            numMapReverse.put("evif", "5");
            numMapReverse.put("xis", "6");
            numMapReverse.put("neves", "7");
            numMapReverse.put("thgie", "8");
            numMapReverse.put("enin", "9");

            StringBuilder finalWordString = new StringBuilder();
            Pattern p = Pattern.compile("[0-9]|one|two|three|four|five|six|seven|eight|nine");
            Matcher matcher = p.matcher(input);
            if(matcher.find()) {
                if(matcher.group().length() > 1) {
                    finalWordString.append(numMap.get(matcher.group()));
                } else {
                    finalWordString.append(matcher.group());
                }
            }

            var reversed = new StringBuilder(input).reverse().toString();
            Pattern pReverse = Pattern.compile("[0-9]|eno|owt|eerht|ruof|evif|xis|neves|thgie|enin");
            Matcher matcherReverse = pReverse.matcher(reversed);
            if(matcherReverse.find()) {
                if(matcherReverse.group().length() > 1) {
                    finalWordString.append(numMapReverse.get(matcherReverse.group()));
                } else {
                    finalWordString.append(matcherReverse.group());
                }
            }
            var finalNumString = finalWordString.toString();

            int finalNum = Integer.parseInt(finalNumString);
            total = total + finalNum;
        }

        return total;
    }
}
