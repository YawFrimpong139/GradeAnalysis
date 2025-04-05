import java.util.Arrays;
//import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] scores;
        int n;

        // Input and control for the number of students and their scores
        while (true) {
            System.out.print("Enter the number of students (N): ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 0) {
                    scores = new int[n];
                    scanner.nextLine(); // Consume the newline character

                    System.out.println("Enter the grades of the " + n + " students separated by spaces:");
                    String line = scanner.nextLine();
                    String[] scoreStrings = line.split("\\s+");

                    if (scoreStrings.length == n) {
                        boolean validScores = true;
                        for (int i = 0; i < n; i++) {
                            try {
                                int score = Integer.parseInt(scoreStrings[i]);
                                if (score >= 0 && score <= 100) {
                                    scores[i] = score;
                                } else {
                                    System.out.println("Invalid grade entered: " + score + ". Grades must be between 0 and 100.");
                                    validScores = false;
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input: '" + scoreStrings[i] + "' is not a valid integer grade.");
                                validScores = false;
                                break;
                            }
                        }
                        if (validScores) {
                            break; // Exit the input loop if everything is valid
                        }
                    } else {
                        System.out.println("Error: Please enter exactly " + n + " grades.");
                    }
                } else {
                    System.out.println("Number of students must be a positive integer.");
                }
            } else {
                System.out.println("Invalid input for the number of students.");
                scanner.next(); // Consume the invalid input
            }
        }

        // Search for and display statistics
        int maximumGrade = scores[0];
        int minimumGrade = scores[0];
        int totalGrade = 0;

        for (int score : scores) {
            if (score > maximumGrade) {
                maximumGrade = score;
            }
            if (score < minimumGrade) {
                minimumGrade = score;
            }
            totalGrade += score;
        }
        double averageGrade = (double) totalGrade / n;

        System.out.println("\n--- Grade Statistics ---");
        System.out.println("Maximum Grade: " + maximumGrade);
        System.out.println("Minimum Grade: " + minimumGrade);
        System.out.println("Average Grade: " + String.format("%.2f", averageGrade));

        //Defining the stats array
        int[] stats = new int[5];
        for (int score : scores) {
            if (score > 80) {
                stats[4]++;
            } else if (score > 60) {
                stats[3]++;
            } else if (score > 40) {
                stats[2]++;
            } else if (score > 20) {
                stats[1]++;
            } else {
                stats[0]++;
            }
        }

        System.out.println("\n--- Grade Distribution ---");
        System.out.println("Grades above 80: " + stats[4]);
        System.out.println("Grades from 61 to 80: " + stats[3]);
        System.out.println("Grades from 41 to 60: " + stats[2]);
        System.out.println("Grades from 21 to 40: " + stats[1]);
        System.out.println("Grades from 0 to 20: " + stats[0]);

        System.out.println("Stats Frequency: " + Arrays.toString(stats));



        // Create a dynamic bar graph
        System.out.println("\n--- Bar Graph of Scores ---");
        int maxCount = 0;
        for (int count : stats) {
            if (count > maxCount) {
                maxCount = count;
            }
        }
        System.out.println(maxCount);

        if (maxCount > 0) {
            for (int i = maxCount; i > 0; i--) {
                System.out.printf("%2d >", i);
                for (int count : stats) {

                    if (count >= i) {
                        System.out.print(" ####### ");
                    } else {
                        System.out.print("         "); // Spaces for alignment
                    }
                }
                System.out.println();
            }
            System.out.println("     -------+--------+--------+---------+---------+");
            System.out.println("     I 0-20 I 21-40  I 41-60  I  61-80  I 81-100  I");
//            System.out.println("");
//            System.out.println("0-20    21-40   41-60   61-80   81-100");
        } else {
            System.out.println("No scores to display in the graph.");
        }

        scanner.close();
    }
}
