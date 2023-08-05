import java.util.*;

public class Main {

    public static String generateSecretNumber(int length, int symbolChoice) {

        Random rand = new Random();
        Set<Integer> seen = new HashSet<>();

        StringBuilder res = new StringBuilder();

        for (int i = 1; i <= length; i++) {
            int nextDigit = rand.nextInt(0, symbolChoice);
            while (seen.contains(nextDigit)) {
                nextDigit = rand.nextInt(0, symbolChoice);
            }

            // > 9 -> a-z -> -10 +97 to change to alphabet
            int digitToChar = nextDigit;
            if (digitToChar > 9) {
                digitToChar = digitToChar - 10 + 97;
            }

            String nextChar = nextDigit > 9 ? String.valueOf((char) digitToChar) : String.valueOf(nextDigit);

            seen.add(nextDigit);
            res.append(nextChar);
        }

        // code preparation message
        StringBuilder starMessage = new StringBuilder();
        for (int i = 0; i < length; i++) {
            starMessage.append('*');
        }

        StringBuilder displayRange = new StringBuilder();
        if (symbolChoice == 1) {
            displayRange.append("(0)");
        } else if (symbolChoice < 10) {
            displayRange.append("(0-")
                    .append(String.valueOf(symbolChoice))
                    .append(')');
        } else if (symbolChoice == 10) {
            displayRange.append("(0-9, a)");
        } else { // > 10
            char maxChar = (char) (symbolChoice - 10 + 97 - 1); // -1 because count back "0"
            displayRange.append("(0-9, a-")
                    .append(maxChar)
                    .append(')');
        }
        System.out.printf("The secret is prepared: %s %s.", starMessage, displayRange);
        System.out.println("");

        return res.toString();

    }

    //state check
    public static int[] stateCheck(String secretNumber) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();


        if (secretNumber.length() != input.length()) {
            return new int[] {-1, -1};
        }

        for (int i = 0; i < input.length(); i++) {

            if (! "abcdefghijklmnopqrstuvwxyz1234567890".contains(String.valueOf(input.charAt(i)))){
                return new int[] {-1, -1};
            }
        }


        char[] inputArray = input.toCharArray();
        char[] ansArray = secretNumber.toCharArray();

        int bull = 0, cow = 0;

        // check bull
        for (int i = 0; i < secretNumber.length(); i++) {
            if (inputArray[i] == ansArray[i]) {
                bull++;
            }
        }

        // check cow
        for (int i = 0; i < secretNumber.length(); i++) { // i -> input
            for (int j = 0; j < secretNumber.length(); j++) { // j -> ans
                if (i != j && inputArray[i] == ansArray[j]) {
                    cow++;
                }
            }
        }

        return new int[] {bull, cow};
    }

    // print result
    public static void printResult(int bull, int cow) {
        if (bull != 0 && cow != 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s)%n", bull, cow);
        } else if (bull != 0) {
            System.out.printf("Grade: %d bull(s)%n", bull);
        } else if (cow != 0) {
            System.out.printf("Grade: %d cow(s)%n", cow);
        } else {
            System.out.printf("Grade: None%n");
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Input the length of the secret code:");
            int numberLength = scanner.nextInt();

            System.out.println("Input the number of possible symbols in the code:");
            int symbolChoice = scanner.nextInt();

            if (symbolChoice < numberLength) {
                System.out.printf("Error: it's not possible to generate a code with a length of " +
                        "%d with %d unique symbols.%n", numberLength, symbolChoice);
                return;
            }

            if (symbolChoice > 36 || numberLength <= 0 || numberLength > 36) {
                System.out.println("error");
                return;
            }

            String secretNumber = null;

            if (numberLength > 36) {
                System.out.printf("Error: can't generate a secret number with a " +
                        "length of %d because there aren't enough unique digits.", numberLength);
            } else {
                secretNumber = generateSecretNumber(numberLength, symbolChoice);
            }

            System.out.println("Okay, let's start a game!");
            int turnNumber = 1;
            while (true) {
                System.out.printf("Turn %d:%n", turnNumber);
                int[] res = stateCheck(secretNumber); // bull, cow

                printResult(res[0], res[1]);
                if (res[0] == numberLength) {
                    break;
                }
                if (res[0] == -1) {
                    System.out.println("error");
                    return;
                }
                turnNumber++;
            }

            System.out.println("Congratulations! You guessed the secret code.");
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
