import models.Operation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static Queue<Operation> history = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean on = true;

        printWelcomeBanner();

        while (on) {
            printMainMenu();

            String option = sc.nextLine();
            clearConsole();

            switch (option) {
                case "1":
                    printOperationsMenu();
                    doCalculation(sc, getOpertor(sc));
                    break;

                case "2":
                    printHistory();
                    break;

                case "3":
                    on = false;
                    break;
            }
        }
    }

    private static void printWelcomeBanner() {
        final String MESSAGE = "Welcome to The Calculator";
        String border = "*".repeat(MESSAGE.length() + 4);

        System.out.println(border);
        System.out.println("* " + MESSAGE + " *");
        System.out.println(border);
    }

    private static void printMainMenu() {
        System.out.println("Enter the number of the option");
        System.out.println(" 1. Operations.");
        System.out.println(" 2. History.");
        System.out.println(" 3. Exit.");
    }

    private static void printHistory() {

        if (history.isEmpty()) {
            System.out.println("There are no operations");
        } else {
            StringBuilder sb = new StringBuilder();
            int i = 1;

            for (Operation operation : history) {
                sb.append("Operation ").append(i).append(":\n");
                sb.append("  operator: ").append(operation.getOperator()).append("\n");
                sb.append("  a: ").append(operation.getA()).append("\n");
                sb.append("  b: ").append(operation.getB()).append("\n");
                sb.append("  resultado: ").append(operation.getResult()).append("\n\n");
                i++;
            }

            System.out.println(sb.toString());
        }
    }

    private static void printOperationsMenu() {
        System.out.println("Enter the symbol of the operation you want to perform:");
        System.out.println(" (+) Add");
        System.out.println(" (-) Subtract");
        System.out.println(" (*) Multiply");
        System.out.println(" (/) Divide");
    }

    private static String getOpertor(Scanner sc) {
        String REGEX = "^[+\\-*/]$";
        String operator = sc.nextLine();

        if(Pattern.matches(REGEX, operator)) {
            return operator;
        }
        else {
            return "";
        }
    }

    private static void doCalculation(Scanner sc, String operator) {
        if(operator.isEmpty()) {
            System.out.println("The value entered is not correct");
            return;
        }

        System.out.println("Enter the first number:");
        double a = sc.nextDouble();
        sc.nextLine();

        System.out.println("Enter the second number:");
        double b = sc.nextDouble();
        sc.nextLine();

        if(operator.equals("/") && b == 0) {
            System.out.println("Divisions by 0 are not possible");
            return;
        }

        double result = switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };

        System.out.println("Result: " + result);
        clearConsole();

        Operation operation = new Operation();
        operation.setA(a);
        operation.setB(b);
        operation.setOperator(operator);
        operation.setResult(result);

        history.add(operation);
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}