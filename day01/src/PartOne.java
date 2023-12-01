public class PartOne {
    public static int computeTotalCalibrationDigits() {
        String[] inputArr = Input.INPUT.split("\\n");

        int total = 0;

        for(String input : inputArr) {
            String numbers = input.replaceAll("[^0-9]", "");
            int len = numbers.length();
            var finalNumString = numbers.substring(0,1) + numbers.substring(len-1,len);
            int finalNum = Integer.parseInt(finalNumString);
            total = total + finalNum;
        }
        return total;
    }
}
