/* M Fajar*/
public class Saving extends Transaction{
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad
   private DepositSlot depositSlot; // reference to deposit slot
   private final static int CANCELED = 0; // constant for cancel option

   // Deposit constructor
   public Saving(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      DepositSlot atmDepositSlot) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad= atmKeypad;
      depositSlot=atmDepositSlot;
   } 

   @Override
   public void execute() {
       amount= promptForSavingAmount();
       if(amount==0)
       {
           System.out.println("Canceling transaction...");
       }
       else {
           super.getBankDatabase().save(super.getAccountNumber(), amount);
           System.out.print("You Save ");
           super.getScreen().displayDollarAmount(amount);
           
       }
   }
   private double promptForSavingAmount() {
      Screen screen = getScreen(); // get reference to screen

      // display the prompt
      screen.displayMessage("\nPlease enter a deposit amount in " + 
         "CENTS (or 0 to cancel): ");
      int input = keypad.getInput(); // receive input of deposit amount
      
      // check whether the user canceled or entered a valid amount
      if (input == CANCELED) {
         return CANCELED;
      }
      else {
         return (double) input / 100; // return dollar amount
      }
   }
}
