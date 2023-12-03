public class PartOne {
    public static int computePossibleGameTotal() {
        String[] inputArr = Input.INPUT.split("\\n");
        int redMax = 12;
        int greenMax = 13;
        int blueMax = 14;

        int total = 0;

        for(int i=0; i < inputArr.length; i++) {
            var input = inputArr[i];
            var setStart = input.indexOf(":");
            var set = input.substring(setStart).split(";");

            boolean isSetGood = true;

            for(String s : set) {
                var blue = 0;
                var green = 0;
                var red = 0;

                var blueIndex = s.indexOf("blue")-2;
                var redIndex = s.indexOf("red")-2;
                var greenIndex = s.indexOf("green")-2;

                if(blueIndex > 0) {
                    if(Character.isDigit(s.charAt(blueIndex-1))) {
                        var blueNum = s.substring(blueIndex-1,blueIndex+1);
                        blue = Integer.parseInt(blueNum);
                    }
                }

                if(greenIndex > 0) {
                    if(Character.isDigit(s.charAt(greenIndex-1))) {
                        var greenNum = s.substring(greenIndex-1,greenIndex+1);
                        green = Integer.parseInt(greenNum);
                    }
                }

                if(redIndex > 0) {
                    if(Character.isDigit(s.charAt(redIndex-1))) {
                        var redNum = s.substring(redIndex-1,redIndex+1);
                        red = Integer.parseInt(redNum);
                    }
                }

                if(red > redMax || green > greenMax || blue > blueMax) {
                    isSetGood = false;
                    break;
                }
            }

            if(isSetGood) {
                total = total + (i+1);
            }
        }

        return total;
    }
}
