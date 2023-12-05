import java.util.*;

public class Main {

    public static Long computePartOne() {
        List<String> inputArr = new ArrayList<>(List.of(Input.INPUT.split("\\n")));

        List<String> seeds = new ArrayList<>(List.of(inputArr.get(0).substring(inputArr.get(0).indexOf(":")+1).split(" ")));
        seeds.removeAll(Collections.singleton(""));

        List<List<String>> subMaps = new ArrayList<>();
        int prev = 0;
        for (int cur = 0; cur < inputArr.size(); cur++) {
            if (inputArr.get(cur).equals("")) {
                subMaps.add(inputArr.subList(prev+1, cur));
                prev = cur + 1;
            }
        }
        subMaps.add(inputArr.subList(prev+1, inputArr.size()));

        List<String> seedToSoil = subMaps.get(1);
        List<String> soilToFertilizer = subMaps.get(2);
        List<String> fertilizerToWater = subMaps.get(3);
        List<String> waterToLight = subMaps.get(4);
        List<String> lightToTemp = subMaps.get(5);
        List<String> temptoHumidity = subMaps.get(6);
        List<String> humidityToLocation = subMaps.get(7);

        List<Long> locVals = new ArrayList<>();

        for(String seed : seeds) {
            var soilVal = mapValue(Long.valueOf(seed), seedToSoil);
            var fertVal = mapValue(soilVal, soilToFertilizer);
            var waterVal = mapValue(fertVal, fertilizerToWater);
            var lightVal = mapValue(waterVal, waterToLight);
            var tempVal = mapValue(lightVal, lightToTemp);
            var humidVal = mapValue(tempVal, temptoHumidity);
            var locVal = mapValue(humidVal, humidityToLocation);
            locVals.add(locVal);
        }

        var lowest = Collections.min(locVals);
        return lowest;
    }

    public static Long mapValue(Long startVal, List<String> mapList) {
        for(String val : mapList) {
            var nums = val.split(" ");
            var source = Long.valueOf(nums[1]);
            var dest = Long.valueOf(nums[0]);
            var range = Long.valueOf(nums[2]);

            if(startVal >= source && startVal <= source+range) {
                var diff = startVal-source;
                return dest+diff;
            }
        }
        return startVal;
    }

    public static Long reverseMapValue(Long startVal, List<String> mapList) {
        for(String val : mapList) {
            var nums = val.split(" ");
            var source = Long.valueOf(nums[0]);
            var dest = Long.valueOf(nums[1]);
            var range = Long.valueOf(nums[2]);

            if(startVal >= source && startVal <= source+range) {
                var diff = startVal-source;
                return dest+diff;
            }
        }
        return startVal;
    }

    public static Map<Long,Long> getAsReversedMap(List<String> mapList) {
        Map<Long,Long> map = new HashMap<>();
        for(String val : mapList) {
            var nums = val.split(" ");
            var source = Long.valueOf(nums[0]);
            var dest = Long.valueOf(nums[1]);
            map.put(source, dest);
        }
        return map;
    }

    public static Long computePartTwo() {
        List<String> inputArr = new ArrayList<>(List.of(Input.INPUT.split("\\n")));

        var seedArr = inputArr.get(0).substring(inputArr.get(0).indexOf(":") + 1).split(" ");

        List<List<String>> subMaps = new ArrayList<>();
        int prev = 0;
        for (int cur = 0; cur < inputArr.size(); cur++) {
            if (inputArr.get(cur).equals("")) {
                subMaps.add(inputArr.subList(prev + 1, cur));
                prev = cur + 1;
            }
        }
        subMaps.add(inputArr.subList(prev + 1, inputArr.size()));

        Map<Long, Long> soilToSeed = getAsReversedMap(subMaps.get(1));
        Map<Long, Long> fertToSoil = getAsReversedMap(subMaps.get(2));
        Map<Long, Long> waterToFert = getAsReversedMap(subMaps.get(3));
        Map<Long, Long> lightToWater = getAsReversedMap(subMaps.get(4));
        Map<Long, Long> tempToLight = getAsReversedMap(subMaps.get(5));
        Map<Long, Long> humidToTemp = getAsReversedMap(subMaps.get(6));
        Map<Long, Long> locToHumid = getAsReversedMap(subMaps.get(7));


        List<Long> seeds = new ArrayList<>();
        Set<Long> soilVals = new HashSet<>(soilToSeed.keySet());
        Set<Long> fertVals = new HashSet<>(fertToSoil.keySet());
        Set<Long> waterVals = new HashSet<>(waterToFert.keySet());
        Set<Long> lightVals = new HashSet<>(lightToWater.keySet());
        Set<Long> tempVals = new HashSet<>(tempToLight.keySet());
        Set<Long> humidVals = new HashSet<>(humidToTemp.keySet());

        for (Long val : locToHumid.keySet()) {
            humidVals.add(locToHumid.get(val));
        }

        for (Long val : humidVals) {
            if (humidToTemp.get(val) != null) {
                tempVals.add(humidToTemp.get(val));
            } else {
                tempVals.add(reverseMapValue(val, subMaps.get(6)));
            }
        }

        for (Long val : tempVals) {
            if (tempToLight.get(val) != null) {
                lightVals.add(tempToLight.get(val));
            } else {
                lightVals.add(reverseMapValue(val, subMaps.get(5)));
            }
        }

        for (Long val : lightVals) {
            if (lightToWater.get(val) != null) {
                waterVals.add(lightToWater.get(val));
            } else {
                waterVals.add(reverseMapValue(val, subMaps.get(4)));
            }
        }

        for (Long val : waterVals) {
            if (waterToFert.get(val) != null) {
                fertVals.add(waterToFert.get(val));
            } else {
                fertVals.add(reverseMapValue(val, subMaps.get(3)));
            }
        }

        for (Long val : fertVals) {
            if (fertToSoil.get(val) != null) {
                soilVals.add(fertToSoil.get(val));
            } else {
                soilVals.add(reverseMapValue(val, subMaps.get(2)));
            }
        }

        for (Long val : soilVals) {
            if (soilToSeed.get(val) != null) {
                seeds.add(soilToSeed.get(val));
            } else {
                seeds.add(reverseMapValue(val, subMaps.get(1)));
            }
        }

        for (Long val : soilToSeed.keySet()) {
            seeds.add(soilToSeed.get(val));
        }

        List<Long> locVals = new ArrayList<>();
        for (int i = 1; i < seedArr.length; i += 2) {
            var startVal = Long.valueOf(seedArr[i]);
            var range = Long.valueOf(seedArr[i + 1]);

            for (Long seed : seeds) {
                if (seed >= startVal && seed <= startVal + range) {
                    var soilVal = mapValue(Long.valueOf(seed), subMaps.get(1));
                    var fertVal = mapValue(soilVal, subMaps.get(2));
                    var waterVal = mapValue(fertVal, subMaps.get(3));
                    var lightVal = mapValue(waterVal, subMaps.get(4));
                    var tempVal = mapValue(lightVal, subMaps.get(5));
                    var humidVal = mapValue(tempVal, subMaps.get(6));
                    var locVal = mapValue(humidVal, subMaps.get(7));
                    locVals.add(locVal);
                }
            }
        }

        return Collections.min(locVals);
    }


    public static void main(String[] args) {
        System.out.println("Part one = " + computePartOne());
        System.out.println("Part two = " + computePartTwo());
    }
}