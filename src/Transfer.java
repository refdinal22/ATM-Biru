import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Queue;
import java.util.LinkedList;

public class Transfer extends Transaction{
     private int amount; // amount to withdraw
     private Keypad keypad; // reference to keypad
    public Transfer(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, int amount, Keypad keypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        this.amount = amount;
        this.keypad = keypad;
    }
    @Override
    
    
    public void execute() {      
      int tamp=0;
      Queue curr;
      boolean isRight=true;
      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      amount=displayMenuOfAmounts();
      
       if (amount != 6){

           if (!getBankDatabase().getTrfH(getAccountNumber()).isEmpty()){
               System.out.println("\n\t\tMENU");
               System.out.println("1. Enter Your Own Account Number");
               System.out.println("2. Select from transfer History");
               System.out.println("\n\nEnter the choice : \n");
               
//               System.out.println("\nSelect Account : ");
               
               int pil=keypad.getInput();
               if(pil!=1&&pil==2){
                   getBankDatabase().showAcc(getAccountNumber());
                   pil=keypad.getInput();
                   
               if(pil<=getBankDatabase().currsize(getAccountNumber())&& pil!=0){
                   tamp = getBankDatabase().search(getAccountNumber(), pil);
               }
               }

           }
           if (tamp ==0) tamp = transferAccount();
           if(tamp == getAccountNumber()){
               System.out.println("You can't transfer to your own account!");
               isRight=false;
           }else isRight=true;
           if(super.getBankDatabase().getAvailableBalance(getAccountNumber())>=amount&&isRight==true){
               if(tamp!=0){
                    getBankDatabase().debit(getAccountNumber(), amount);
                    getBankDatabase().save(tamp, amount);
                    //menulis di File
                    maketxt(tamp, amount);
                    
                    if(getBankDatabase().currsize(getAccountNumber())<5){
                        curr = getBankDatabase().getTrfH(getAccountNumber());
                        if(curr.size() < 5){
                            curr.add(new TrfHistory(amount, tamp));
                            getBankDatabase().setTrfH(getAccountNumber(), curr);
                            System.out.println(curr.size());
                        }
                    }
                    else{
                        curr = getBankDatabase().getTrfH(getAccountNumber());
                        curr.remove();
                        curr.add(new TrfHistory(amount, tamp));         
                        getBankDatabase().setTrfH(getAccountNumber(), curr);
                        }

                    System.out.println("Your cash has been transferred. Thank you for using this ATM.\n" +"");
               }
               else System.out.println("\nAccount Not Found!\n");
            }
           else System.out.println("Transaction invalid!\nCanceling Transaction. . .\n");
        }
    }
    
    private int transferAccount() {
      Screen screen = getScreen(); // get reference to screen

      // display the prompt
      screen.displayMessage("\nEnter Account Number : ");
      int input = keypad.getInput(); // receive input of deposit amount
      if(getBankDatabase().availableAccount(input)) return input;
      else return 0;   
   }
    
   /******* Rico ***********/
    private int displayMenuOfAmounts() {
      int userChoice = 0; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int[] amounts = {0, 20, 40, 60, 100, 200};

      // loop while no valid choice has been made
      while (userChoice == 0) {
         // display the withdrawal menu
         screen.displayMessageLine("\nTransfer Amount Menu:");
         screen.displayMessageLine("0 - Custom Nominal");
         screen.displayMessageLine("1 - $20");
         screen.displayMessageLine("2 - $40");
         screen.displayMessageLine("3 - $60");
         screen.displayMessageLine("4 - $100");
         screen.displayMessageLine("5 - $200");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nChoose a transfer amount: ");

         int input = keypad.getInput(); // get user input through keypad

         // determine how to proceed based on the input value
         switch (input) {
            case 0:
                getScreen().displayMessage("\nEnter Amount Transfer : ");
                userChoice = keypad.getInput();
                break;
            case 1: // if the user chose a withdrawal amount 
            case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
            case 3: // corresponding amount from amounts array
            case 4:
            case 5:
               userChoice = amounts[input]; // save user's choice
               break;       
            case 6: // the user chose to cancel
               userChoice = 6; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
         } 
      } 

      return userChoice; // return withdrawal amount or CANCELED
   }
    
   /*Refdinal*/
   private void maketxt(int account, double amount){
        String filename= "struk.txt";
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat curHour = new SimpleDateFormat("HH:mm:ss");
        DateFormat curDate = new SimpleDateFormat("dd/MM/yyyy");
        
        String formattedH = curHour.format(date);
        String formattedDate = curDate.format(date);
        
         try {
             FileWriter fw = new FileWriter(filename,false);
             fw.write("     *** ATM ***\n\n");
             fw.write(formattedDate+"     "+formattedH+"\n\n");
             fw.write("TRANSFER\n");
             fw.write("To    : "+ account+"\n");
             fw.write("Total : $ "+amount+"\n");
             fw.write("\n\n     THANK YOU\n");
             fw.close();
             
         } catch (IOException ex) {
             System.out.println("Failed");
         }
   }
}
