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

    private static float deposit(float depositMoney, float currentBalance, String account) {
        float newBalance = currentBalance + depositMoney;
        try {
            FileWriter myWriter = new FileWriter(account);
            String balanceToBeWritten = Float.toString(newBalance);
            myWriter.write(balanceToBeWritten);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newBalance;
    }

    private static float withdraw(float withdrawMoney, float currentBalance, String account) {
        float newBalance = currentBalance - withdrawMoney;
        try{
            FileWriter myWriter = new FileWriter(account);
            String balanceToBeWritten = Float.toString(newBalance);
            myWriter.write(balanceToBeWritten);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newBalance;
    }

    private static String accountChoice() {
        Scanner scannerObj = new Scanner(System.in);
        while(true) {
            System.out.println("Please enter what account you would like to make a transaction in: ");
            System.out.println("1. Checkings\n2. Savings\n3. Quit");
            int accountChoice = scannerObj.nextInt();
            switch(accountChoice) {
                case 1: {
                    try{
                        File fileObject = new File("checkings.txt");
                        if(fileObject.createNewFile()) {
                            FileWriter writer = new FileWriter("checkings.txt");
                            writer.write("0.00");
                            writer.close();
                            return "checkings.txt";
                        }else {
                            return "checkings.txt";
                        }
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                    
                }
                case 2: {
                    try {
                        File fileObject = new File("savings.txt");
                        if(fileObject.createNewFile()) {
                            FileWriter writer = new FileWriter("savings.txt");
                            writer.write("0.00");
                            writer.close();
                            return "savings.txt";
                        }else {
                            return "savings.txt";
                        }
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
                case 3: {
                    scannerObj.close();
                    System.exit(1);
                }
                default: {
                    System.out.println("Not a valid choice, please try again.");
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        String account = accountChoice();
        float balance = Float.parseFloat(readFile(account));
        while (true) {
            System.out.println("Please enter what you would like to do (1 - 3): \n");
            System.out.println("1. Check Balance\n2. Deposit\n3. Withdraw\n4. Go Back");
            int userChoice = scannerObj.nextInt();
            switch (userChoice) {
                case 1: {
                    System.out.println(String.format("%.2f", balance));
                    break;
                }
                case 2: {
                    System.out.println("How much would you like to deposit?: ");
                    float moneyToDeposit = scannerObj.nextFloat();
                    balance = Float.parseFloat(readFile(account));
                    balance = deposit(moneyToDeposit, balance, account);
                    System.out.println(String.format("%.2f", balance));
                    break;
                }
                case 3: {
                    System.out.println("How much would you like to withdraw?: ");
                    float moneyToWithdraw = scannerObj.nextFloat();
                    balance = Float.parseFloat(readFile(account));
                    balance = withdraw(moneyToWithdraw, balance, account);
                    System.out.println(String.format("%.2f", balance));
                    break;
                }
                case 4: {
                    // scannerObj.close();
                    account = accountChoice();
                    balance = Float.parseFloat(readFile(account));
                }
                default: {
                    System.out.println("Not a valid option, please choose between 1, 2, 3, 4.");
                    break;
                }
            }
        }
    }
}
