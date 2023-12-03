public class PartTwo {
    public static int computeSumOfPowers() {
        String[] inputArr = Input.INPUT.split("\\n");
        int total = 0;

        for(String input : inputArr) {
            var setStart = input.indexOf(":");
            var set = input.substring(setStart).split(";");

            var maxBlue = 0;
            var maxGreen = 0;
            var maxRed = 0;

            for(String s : set) {
                var blueIndex = s.indexOf("blue") - 2;
                var redIndex = s.indexOf("red") - 2;
                var greenIndex = s.indexOf("green") - 2;

                if(blueIndex > 0) {
                    int blueNum;
                    if(Character.isDigit(s.charAt(blueIndex-1))) {
                        blueNum = Integer.parseInt(s.substring(blueIndex-1,blueIndex+1));
                    } else {
                        blueNum = Integer.parseInt(String.valueOf(s.charAt(blueIndex)));
                    }
                    if (maxBlue <= blueNum) {
                        maxBlue = blueNum;
                    }
                }

                if(greenIndex > 0) {
                    int greenNum;
                    if(Character.isDigit(s.charAt(greenIndex-1))) {
                        greenNum = Integer.parseInt(s.substring(greenIndex-1,greenIndex+1));
                    } else {
                        greenNum = Integer.parseInt(String.valueOf(s.charAt(greenIndex)));
                    }
                    if (maxGreen <= greenNum) {
                        maxGreen = greenNum;
                    }
                }

                if(redIndex > 0) {
                    int redNum;
                    if(Character.isDigit(s.charAt(redIndex-1))) {
                        redNum = Integer.parseInt(s.substring(redIndex-1,redIndex+1));
                    } else {
                        redNum = Integer.parseInt(String.valueOf(s.charAt(redIndex)));
                    }
                    if (maxRed <= redNum) {
                        maxRed = redNum;
                    }
                }
            }
            var power = maxBlue * maxRed * maxGreen;
            total = total + power;
        }

        return total;
    }
}
