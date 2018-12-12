/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luthfi M Agung
 */
public class Info extends Transaction {
   public Info(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase) {

      super(userAccountNumber, atmScreen, atmBankDatabase);
   } 

   // performs the transaction
   @Override
   public void execute() {
      // get references to bank database and screen
      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();

      double availableBalance = 
         bankDatabase.getAvailableBalance(getAccountNumber());

      double totalBalance = 
         bankDatabase.getTotalBalance(getAccountNumber());
      
      int accountNumber = 
         bankDatabase.getAcc(getAccountNumber());
      
      // display the balance information on the screen
      screen.displayMessageLine("\nUser Information:");
      screen.displayMessage(" - User Name     : "); 
      screen.displayMessageLine(bankDatabase.getAccName(getAccountNumber()));
      screen.displayMessage(" - Bank Name     : ");
      screen.displayMessageLine(bankDatabase.getBName(getAccountNumber()));
      screen.displayMessage(" - Account Number: ");
      System.out.println(accountNumber);
      screen.displayMessage(" - Available balance: "); 
      screen.displayDollarAmount(availableBalance);
      screen.displayMessage("\n - Total balance    : ");
      screen.displayDollarAmount(totalBalance);
      screen.displayMessageLine("");
   }
}