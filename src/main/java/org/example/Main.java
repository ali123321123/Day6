package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static List<Integer> parseInts(String s) {
        List<Integer> ints = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\d+").matcher(s);
        while (matcher.find()) {
            ints.add(Integer.parseInt(matcher.group()));
        }
        return ints;
    }

    public static int calcDist(int holdTime, int maxTime) {
        return (maxTime - holdTime) * holdTime;
    }

    public static int part1(List<Integer> times, List<Integer> distances) {
        int result = 1;
        for (int i = 0; i < times.size(); i++) {
            int maxTime = times.get(i);
            int recordDist = distances.get(i);
            int count = 0;
            for (int holdTime = 0; holdTime < maxTime; holdTime++) {
                if (calcDist(holdTime, maxTime) > recordDist) {
                    count++;
                }
            }
            result *= count;
        }
        return result;
    }

    public static int solve(int maxTime, int recordDist) {
        int x1 = 0;
        int x2 = maxTime;

        while (x1 < x2 - 1) {
            int mid = (x2 + x1) / 2;
            if (calcDist(mid, maxTime) > recordDist) {
                x2 = mid;
            } else {
                x1 = mid;
            }
        }

        return maxTime - (x1 * 2) - 1;
    }

    public static void main(String[] args) {
        // Input directly in the code
        Integer[] timesArray = {49, 97, 94, 94};
        Integer[] distancesArray = {263, 1532, 1378, 1851};

        List<Integer> times = Arrays.asList(timesArray);
        List<Integer> distances = Arrays.asList(distancesArray);

        // PART 1
        int part1Result = part1(times, distances);
        System.out.println("1: " + part1Result);

        // PART 2
        int time = Integer.parseInt(String.join("", times.stream().map(Object::toString).toArray(String[]::new)));
        int dist = Integer.parseInt(String.join("", distances.stream().map(Object::toString).toArray(String[]::new)));
        int part2Result = solve(time, dist);
        System.out.println("2: " + part2Result);
    }
}
