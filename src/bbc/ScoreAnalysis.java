package bbc;

import java.util.Arrays;
import java.util.Scanner;

public class ScoreAnalysis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] scores = new int[n];
        for (int i = 0; i < n; i++)
            scores[i] = scanner.nextInt();
        System.out.println(Arrays.stream(scores).max().getAsInt());
        System.out.println(Arrays.stream(scores).min().getAsInt());
        System.out.printf("%.2f%n", Arrays.stream(scores).average().getAsDouble());
        scanner.close();
    }
}
