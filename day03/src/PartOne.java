import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {
    public static int computeSchematicTotal() {
        String[] inputArr = Input.INPUT.split("\\n");
        int total = 0;

        for(int i = 0; i < inputArr.length; i++) {
            String prevLine = null;
            String currentLine = inputArr[i];
            String nextLine = null;
            if(i >= 1) {
                prevLine = inputArr[i-1];

            }
            if(i < inputArr.length-1) {
                nextLine = inputArr[i+1];
            }

            Pattern p = Pattern.compile("\\d{1,}");
            Matcher matcher = p.matcher(currentLine);

            while(matcher.find()) {
                var number = matcher.group();

                var numberStartPoint = matcher.start();
                var numberEndPoint = numberStartPoint + matcher.group().length();

                var charBefore = numberStartPoint-1 > 0 ? currentLine.charAt(numberStartPoint-1) : '.';
                var charAfter = numberEndPoint < currentLine.length() ? currentLine.charAt(numberEndPoint) : '.';

                String substringLineBefore = "";
                String substringLineAfter = "";

                if(prevLine != null) {
                    substringLineBefore = prevLine.substring(Math.max(numberStartPoint-1, 0), Math.min(numberEndPoint+1, prevLine.length()));
                }

                if(nextLine != null) {
                    substringLineAfter = nextLine.substring(Math.max(numberStartPoint-1, 0), Math.min(numberEndPoint+1, nextLine.length()));
                }

                if (!substringLineBefore.isEmpty()) {
                    substringLineBefore = substringLineBefore.replace(".","").replaceAll("[0-9]", "");
                }

                if (!substringLineAfter.isEmpty()) {
                    substringLineAfter = substringLineAfter.replace(".","").replaceAll("[0-9]", "");
                }

                if(charBefore != '.' || charAfter != '.' || !substringLineBefore.isEmpty() || !substringLineAfter.isEmpty()) {
                    total = total + Integer.parseInt(number);

                }

            }
        }

        return total;
    }
}
