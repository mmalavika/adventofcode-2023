import java.util.*;

public class PartTwo {
    public static int computeTotalScratchcards() {
        List<String> inputList = new ArrayList<>(Arrays.asList(Input.INPUT.split("\\n")));

        Map<Integer, Integer> countPerCard = new HashMap<>();
        Map<Integer, Integer> copiesWonPerCard = new HashMap<>();

        for(int i=0; i < inputList.size(); i++) {
            var input = inputList.get(i);
            var cardNumber = i+1;
            var numString = input.substring(input.indexOf(":")+1);
            var winningNumbers = new ArrayList<>(List.of(numString.substring(0, numString.indexOf("|") - 1).split(" ", -1)));
            var numToCheck = new ArrayList<>(List.of(input.substring(input.indexOf("|") + 1).split(" ", -1)));

            winningNumbers.removeAll(Collections.singleton(""));
            numToCheck.removeAll(Collections.singleton(""));
            var copiesWon = 0;

            for(String num : numToCheck) {
                if(winningNumbers.contains(num)) {
                    copiesWon++;
                }
            }

            countPerCard.put(cardNumber, 1);
            copiesWonPerCard.put(cardNumber, copiesWon);
        }

        for (int i=0; i < inputList.size(); i++) {
            var cardNumber = i+1;
            var copiesWon = copiesWonPerCard.get(cardNumber);
            var currentCount = countPerCard.get(cardNumber);
            for(int j = cardNumber+1; j <= cardNumber + copiesWon; j++) {
                countPerCard.put(j, countPerCard.get(j)+currentCount);
            }
        }


        int total = 0;
        for (Integer key : countPerCard.keySet()) {
            total = total + countPerCard.get(key);
        }
        return total;
    }
}
