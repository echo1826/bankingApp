package bankingApp;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class bankingApp {

    private static String readFile(String filename) {
        String data = "";
        try {
            File myFile = new File(filename);
            Scanner myReader = new Scanner(myFile);
            data = myReader.nextLine();
            // System.out.println(data);
            myReader.close();
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static float deposit(float depositMoney, float currentBalance) {
        float newBalance = currentBalance + depositMoney;
        try {
            FileWriter myWriter = new FileWriter("balance.txt");
            String balanceToBeWritten = Float.toString(newBalance);
            myWriter.write(balanceToBeWritten);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newBalance;
    }

    private static float withdraw(float withdrawMoney, float currentBalance) {
        float newBalance = currentBalance - withdrawMoney;
        try{
            FileWriter myWriter = new FileWriter("balance.txt");
            String balanceToBeWritten = Float.toString(newBalance);
            myWriter.write(balanceToBeWritten);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newBalance;
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
                balance = Float.parseFloat(readFile("balance.txt"));
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
                    balance = Float.parseFloat(readFile("balance.txt"));
                    balance = deposit(moneyToDeposit, balance);
                    System.out.println(String.format("%.2f", balance));
                    break;
                }
                case 3: {
                    System.out.println("How much would you like to withdraw?: ");
                    float moneyToWithdraw = scannerObj.nextFloat();
                    balance = Float.parseFloat(readFile("balance.txt"));
                    balance = withdraw(moneyToWithdraw, balance);
                    System.out.println(String.format("%.2f", balance));
                    break;
                }
                case 4: {
                    scannerObj.close();
                    System.exit(1);
                }
                default: {
                    System.out.println("Not a valid option, please choose between 1, 2, 3, 4.");
                    break;
                }
            }
        }
    }
}
