
/**
 *
 * @author Daffa
 */
import java.util.Scanner;
public class History extends Transaction  {
 private int his;
 private Keypad keypad;
    Transaction temp = null; 
    
  
    
    public History(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad) {
        
        super(userAccountNumber, atmScreen, atmBankDatabase);
         keypad = atmKeypad;
    }



    @Override
    public void execute() {
        System.out.println("Other Payment : ");
        System.out.println("1. History Deposit and Withdrawal");
        System.out.println("2. History Transfer");
        System.out.println("3. Highest Transfer");
        System.out.println("4. Lowest Transfer");
        System.out.println("5. Average Transfer");
        System.out.println("Enter a choice : ");
        int enter = keypad.getInput();
        switch(enter){
            case 1 : getBankDatabase().showHistory(getAccountNumber());break;
            case 2 : 
                if(!getBankDatabase().getTrfH(getAccountNumber()).isEmpty()){
                getBankDatabase().show(getAccountNumber());
            }else System.out.println("Belum ada Transfer yang tercipta");break;
            case 3 : 
                if(!getBankDatabase().getTrfH(getAccountNumber()).isEmpty()){
                    
                    int id = getBankDatabase().getmax(getAccountNumber());
                    
                    TrfHistory max = getBankDatabase().searchH(getAccountNumber(), id);
                    System.out.println("Amount      To ");
                    System.out.println(max.getAmount() +"    "+max.getTrfAccount() );
                }
                break;
            case 4 : 
                    if(!getBankDatabase().getTrfH(getAccountNumber()).isEmpty()){
                    int id = getBankDatabase().getmin(getAccountNumber());
                    
                    TrfHistory min = getBankDatabase().searchH(getAccountNumber(), id);
                    System.out.println("Amount      To ");
                    System.out.println(min.getAmount() +"    "+min.getTrfAccount() );
                }
                break;
            case 5 : 
                if(!getBankDatabase().getTrfH(getAccountNumber()).isEmpty()){
                    System.out.println("Average Transfer : $"+getBankDatabase().getAvg(getAccountNumber()));
                }
                break;
            
            default : System.out.println("You did not enter a valid selection");
        }
        
        
    }
}