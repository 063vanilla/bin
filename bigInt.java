import java.util.*;

class bigInt {
    public static void main(String[] args) {
        String number1 = "";
        String number2 = "";
        int choice;
        int continueFlag = 0;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("===========================================================");
            System.out.println("Select From Menu: ");
            System.out.println("1. Addition \n2. Subtraction \n3. Multiplication \n4. Square \n5. Exit");
            System.out.println("===========================================================");
            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline after nextInt()

            switch (choice) {
                case 1:
                    System.out.print("Enter First Number: ");
                    number1 = sc.nextLine();
                    System.out.print("Enter 2nd Number: ");
                    number2 = sc.nextLine();
                    System.out.println("Addition of " + number1 + " and " + number2 + " is: " + findSum(number1, number2));
                    break;

                case 2:
                    System.out.print("Enter First Number: ");
                    number1 = sc.nextLine();
                    System.out.print("Enter 2nd Number: ");
                    number2 = sc.nextLine();
                    System.out.println("Subtraction of " + number1 + " and " + number2 + " is: " + findDifference(number1, number2));
                    break;

                case 3:
                    System.out.print("Enter First Number: ");
                    number1 = sc.nextLine();
                    System.out.print("Enter 2nd Number: ");
                    number2 = sc.nextLine();
                    System.out.println("Multiplication of " + number1 + " and " + number2 + " is: " + findProduct(number1, number2));
                    break;

                case 4:
                    System.out.print("Enter Number: ");
                    number1 = sc.nextLine();
                    System.out.println("Square of " + number1 + " is: " + findProduct(number1, number1));
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println("===========================================================");
            if (choice != 5) { // Only ask to continue if choice is not Exit
                System.out.print("Do you want to continue? (1 for Yes / 0 for No): ");
                continueFlag = sc.nextInt();
                sc.nextLine(); // Consume newline after nextInt()
            } else {
                continueFlag = 0;
            }
        } while (continueFlag == 1 && choice != 5);
    }

    private static String findProduct(String num1, String num2) {
        if (num1.isEmpty() || num2.isEmpty()) {
            return "0";
        }
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int length = num1.length();
        while (num2.length() < length) {
            num2 = "0" + num2;
        }
        if (length == 1) {
            int result = Integer.parseInt(num1) * Integer.parseInt(num2);
            return Integer.toString(result);
        }
        if (length % 2 == 1) {
            num1 = "0" + num1;
            num2 = "0" + num2;
            length++;
        }
        String num1Left = num1.substring(0, length / 2);
        String num1Right = num1.substring(length / 2, length);
        String num2Left = num2.substring(0, length / 2);
        String num2Right = num2.substring(length / 2, length);

        String p = findProduct(num1Left, num2Left);
        String q = findProduct(num1Right, num2Right);
        String r = findProduct(findSum(num1Left, num1Right), findSum(num2Left, num2Right));

        String subResult = findDifference(r, findSum(p, q));
        p = p + "0".repeat(length); // Fix for appending zeros
        subResult += "0".repeat(length / 2); // Corrected padding
        String result = findSum(findSum(p, subResult), q);
        return result.replaceFirst("^0+(?!$)", "");
    }

    private static String findDifference(String num1, String num2) {
        if (num1.isEmpty()) return num2;
        if (num2.isEmpty()) return num1;

        if (num1.length() < num2.length() || (num1.length() == num2.length() && num1.compareTo(num2) < 0)) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        int len1 = num1.length();
        int len2 = num2.length();
        StringBuilder result = new StringBuilder();

        String str1 = new StringBuilder(num1).reverse().toString();
        String str2 = new StringBuilder(num2).reverse().toString();
        int borrow = 0;
        for (int i = 0; i < len1; i++) {
            int digit1 = str1.charAt(i) - '0';
            int digit2 = (i < len2) ? str2.charAt(i) - '0' : 0;
            int diff = digit1 - digit2 - borrow;

            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.append(diff);
        }

        return result.reverse().toString().replaceFirst("^0+(?!$)", "");
    }

    private static String findSum(String num1, String num2) {
        if (num1.isEmpty()) {
            return num2;
        }
        if (num2.isEmpty()) {
            return num1;
        }
        if (num1.length() > num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int len1 = num1.length();
        int len2 = num2.length();
        StringBuilder result = new StringBuilder();

        String str1 = new StringBuilder(num1).reverse().toString();
        String str2 = new StringBuilder(num2).reverse().toString();
        int carry = 0;
        for (int i = 0; i < len1; i++) {
            int sum = ((str1.charAt(i) - '0') + (str2.charAt(i) - '0') + carry);
            result.append((sum % 10));
            carry = sum / 10;
        }
        for (int i = len1; i < len2; i++) {
            int sum = ((str2.charAt(i) - '0') + carry);
            result.append((sum % 10));
            carry = sum / 10;
        }
        if (carry != 0) {
            result.append(carry);
        }
        return result.reverse().toString().replaceFirst("^0+(?!$)", "");
    }
}
