// Withdrawal.java

import java.util.Scanner;

// Represents a withdrawal ATM transaction

public class Withdrawal extends Transaction {
   private int amount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser

   // constant corresponding to menu option to cancel
   private final static int CANCELED = 6;

   // Withdrawal constructor
   public Withdrawal(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
      cashDispenser = atmCashDispenser;
   }

    // perform transaction
    @Override
    public void execute() {
        amount = displayMenuOfAmounts();
        if (amount == CANCELED) 
            System.out.println("Canceling transaction..");
        else {
            if(cashDispenser.isSufficientCashAvailable(amount)){
                if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){
                    cashDispenser.dispenseCash(amount);
                    super.getBankDatabase().debit(super.getAccountNumber(), amount);
                    getBankDatabase().addHistory(getAccountNumber(), "Last Withdrawal :" + amount);
                    System.out.println("Your cash has been dispensed. Please take your cash now.");
                }
                else {
                    System.out.println("Inufficient Balance");
                }
            }
            else {
                System.out.println("Inufficient Cash In Dispenser");
            }
        }
    } 

   // display a menu of withdrawal amounts and the option to cancel;
   // return the chosen amount or 0 if the user chooses to cancel
   private int displayMenuOfAmounts() {
      int userChoice = 0; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int[] amounts = {0, SHARDS,
                          SHARDS*2, 
                          SHARDS*3,
                          SHARDS*5,
                          SHARDS*10};

      // loop while no valid choice has been made
      while (userChoice == 0) {
         // display the withdrawal menu
         screen.displayMessageLine("\nWithdrawal Menu:");
         /*************************** UPDATE ***************************/
         screen.displayMessageLine("(Withdraw in $"+SHARDS+" denomination)");
         screen.displayMessageLine("0 - Custom Nominal");
         /************************* END UPDATE *************************/
         screen.displayMessageLine("1 - $"+amounts[1]);
         screen.displayMessageLine("2 - $"+amounts[2]);
         screen.displayMessageLine("3 - $"+amounts[3]);
         screen.displayMessageLine("4 - $"+amounts[4]);
         screen.displayMessageLine("5 - $"+amounts[5]);
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nChoose a withdrawal amount: ");

         int input = keypad.getInput(); // get user input through keypad

         // determine how to proceed based on the input value
         switch (input) {
            case 1: // if the user chose a withdrawal amount 
            case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
            case 3: // corresponding amount from amounts array
            case 4:
            case 5:
                userChoice = amounts[input]; // save user's choice
                break;
            /*************************** UPDATE ***************************/
            case 0 :
                userChoice = customNominal();
                break;
            /************************* END UPDATE *************************/
            case CANCELED: // the user chose to cancel
                userChoice = CANCELED; // save user's choice
                break;
            default: // the user did not enter a value from 1-6
                screen.displayMessageLine("\nInvalid selection. Try again.");
            } 
        }
        return userChoice; // return withdrawal amount or CANCELED
    }
   
    /*************************** UPDATE ***************************/
    private int customNominal(){
        Screen screen = getScreen();
        Scanner sc = new Scanner(System.in);
        for(;;){
            int nominal = sc.nextInt();
            if (nominal%SHARDS==0) return nominal;
            else screen.displayMessageLine("\nInvalid nominal. Try Again.");
        }
    }
    /************************* END UPDATE *************************/
} 