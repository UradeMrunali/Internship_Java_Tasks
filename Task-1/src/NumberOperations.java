import java.util.Scanner;

public class NumberOperations {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("--- Number Operations ---");

        while (keepRunning) {
            System.out.println("Select an operation:");
            System.out.println("1. Check Prime");
            System.out.println("2. Check Palindrome");
            System.out.println("3. Calculate Factorial");
            System.out.println("4. Fibonacci Series");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        checkPrime(sc);
                        break;
                    case 2:
                        checkPalindrome(sc);
                        break;
                    case 3:
                        calculateFactorial(sc);
                        break;
                    case 4:
                        printFibonacci(sc);
                        break;
                    case 5:
                        keepRunning = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please pick 1-5.");
                }
            } else {
                System.out.println("Please enter a number only.");
                sc.next();
            }
        }
    }

    public static void checkPrime(Scanner sc) {
        System.out.print("Enter number to check prime: ");
        int num = sc.nextInt();
        boolean isPrime = true;

        if (num <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= num / 2; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }

        if (isPrime) {
            System.out.println(num + " is a Prime number.");
        } else {
            System.out.println(num + " is NOT Prime.");
        }
    }

    public static void checkPalindrome(Scanner sc) {
        System.out.print("Enter number to check palindrome: ");
        int num = sc.nextInt();
        int original = num;
        int reversed = 0;

        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num = num / 10;
        }

        if (original == reversed) {
            System.out.println(original + " is a Palindrome.");
        } else {
            System.out.println(original + " is not a Palindrome.");
        }
    }

    public static void calculateFactorial(Scanner sc) {
        System.out.print("Enter a number for factorial: ");
        int num = sc.nextInt();
        long fact = 1;

        for (int i = 1; i <= num; i++) {
            fact = fact * i;
        }
        System.out.println("Factorial of " + num + " is " + fact);
    }

    public static void printFibonacci(Scanner sc) {
        System.out.print("How many terms to print? ");
        int count = sc.nextInt();
        int n1 = 0, n2 = 1;

        System.out.print("Fibonacci: " + n1 + " " + n2);

        for (int i = 2; i < count; i++) {
            int n3 = n1 + n2;
            System.out.print(" " + n3);
            n1 = n2;
            n2 = n3;
        }
        System.out.println();
    }
}

