package bankingApp;
import java.util.Scanner;

public class bankingApp {
    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        float balance = 0f;
        while(true) {
            System.out.println("Please enter what you would like to do (1 - 3): \n");
            System.out.println("1. Check Balance\n2. Deposit\n3. Withdraw\n4. Quit");
            int userChoice = scannerObj.nextInt();
            switch(userChoice) {
                case 1: {
                    System.out.println(balance);
                    break;
                }
                case 2: {
                    System.out.println("How much would you like to deposit?: ");
                    float moneyToDeposit = scannerObj.nextFloat();
                    balance = deposit(moneyToDeposit, balance);
                    break;
                }
                case 3: {
                    float moneyToWithdraw = scannerObj.nextFloat();
                    balance = withdraw(moneyToWithdraw, balance);
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
    public static float deposit(float depositMoney, float currentBalance) {
        return currentBalance += depositMoney;
    }
    public static float withdraw(float withdrawMoney, float currentBalance) {
        return currentBalance -= withdrawMoney;
    }
}
