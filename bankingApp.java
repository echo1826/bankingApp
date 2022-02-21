package bankingApp;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class bankingApp {

    public static float deposit(float depositMoney, float currentBalance) {
        return currentBalance += depositMoney;
    }

    public static float withdraw(float withdrawMoney, float currentBalance) {
        return currentBalance -= withdrawMoney;
    }
    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        float balance = 0f;
        try {
            File myFileObject = new File("balance.txt");
            if (myFileObject.createNewFile()) {
                System.out.println("File created: " + myFileObject.getName());
                FileWriter myWriter = new FileWriter("balance.txt");
                String balanceToBeWritten = Float.toString(balance);
                myWriter.write(balanceToBeWritten);
                myWriter.close();
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error occured");
            e.printStackTrace();
        }
        while (true) {
            System.out.println("Please enter what you would like to do (1 - 3): \n");
            System.out.println("1. Check Balance\n2. Deposit\n3. Withdraw\n4. Quit");
            int userChoice = scannerObj.nextInt();
            switch (userChoice) {
                case 1: {
                    System.out.println(String.format("%.2f", balance));
                    break;
                }
                case 2: {
                    System.out.println("How much would you like to deposit?: ");
                    float moneyToDeposit = scannerObj.nextFloat();
                    balance = deposit(moneyToDeposit, balance);
                    System.out.println(String.format("%.2f", balance));
                    break;
                }
                case 3: {
                    float moneyToWithdraw = scannerObj.nextFloat();
                    balance = withdraw(moneyToWithdraw, balance);
                    System.out.println(String.format("%.2f", balance));
                    break;
                }
                case 4: {
                    scannerObj.close();
                    System.exit(1);
                }
                default: {
                    System.out.println("Not a valid option, please choose between 1, 2, 3.");
                    break;
                }
            }
        }
    }
}
