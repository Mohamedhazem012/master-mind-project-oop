import java.util.Scanner;
import java.util.Random;

class Mastermind {

    // الألوان الممكنة في اللعبة
    private static final char[] COLORS = {'R', 'G', 'B'}; // أحمر، أخضر، أزرق
    private static final int NUM_COLORS = 3;
    private static final int CODE_LENGTH = 4;

    // المولد العشوائي
    private static Random rand = new Random();

    // الدالة لتوليد رمز عشوائي من الألوان
    private static char[] generateCode() {
        char[] code = new char[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            code[i] = COLORS[rand.nextInt(NUM_COLORS)];
        }
        return code;
    }

    // الدالة لمقارنة التخمين مع الرمز السري
    private static int[] compareCodes(char[] guess, char[] secretCode) {
        int correctPosition = 0;
        int correctColor = 0;

        boolean[] secretChecked = new boolean[CODE_LENGTH];
        boolean[] guessChecked = new boolean[CODE_LENGTH];

        // تحقق من الألوان في الأماكن الصحيحة
        for (int i = 0; i < CODE_LENGTH; i++) {
            if (guess[i] == secretCode[i]) {
                correctPosition++;
                secretChecked[i] = true;
                guessChecked[i] = true;
            }
        }

        // تحقق من الألوان الصحيحة في الأماكن الخاطئة
        for (int i = 0; i < CODE_LENGTH; i++) {
            if (!guessChecked[i]) {
                for (int j = 0; j < CODE_LENGTH; j++) {
                    if (!secretChecked[j] && guess[i] == secretCode[j]) {
                        correctColor++;
                        secretChecked[j] = true;
                        break;
                    }
                }
            }
        }

        return new int[]{correctPosition, correctColor};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // توليد الرمز السري
        char[] secretCode = generateCode();

        System.out.println("Welcome to the game Mastermind!");
        System.out.println("Try guessing the 4-place code using the colors: R (red), G (green), B (blue).");
        System.out.println("You will be told the number of correct colors in the right places and the right colors in the wrong places.");

        int attempts = 0;
        boolean guessedCorrectly = false;

        // السماح للاعب بتخمين الرمز
        while (!guessedCorrectly) {
            System.out.print("Enter your guess (4 characters, such as: RGBG): ");
            String input = scanner.nextLine().toUpperCase();

            // التحقق من صحة الإدخال
            if (input.length() != CODE_LENGTH || !input.matches("[RGB]+")) {
                System.out.println("Invalid entry. Make sure you use the correct colors.");
                continue;
            }

            // تحويل الإدخال إلى مصفوفة من الحروف
            char[] guess = input.toCharArray();

            // مقارنة التخمين مع الرمز السري
            int[] result = compareCodes(guess, secretCode);

            int correctPosition = result[0];
            int correctColor = result[1];

            attempts++;

            System.out.println("اThe right colors in the right places: " + correctPosition);
            System.out.println("The right colors in the wrong places: " + correctColor);

            // التحقق إذا كان التخمين صحيحًا
            if (correctPosition == CODE_LENGTH) {
                guessedCorrectly = true;
                System.out.println("Congratulations! You guessed the code correctly.");
                System.out.println("Number of attempts: " + attempts);
            }
        }

        scanner.close();
    }
}






