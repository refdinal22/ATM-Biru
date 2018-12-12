/**
 *
 * @author Refdinal
 */
public class Zakat extends Transaction{
    Keypad keypad;
    public Zakat(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
    }

    @Override
    public void execute() {
        int members=0;
        double amount=0;
                
        getScreen().displayMessage("      Zakat Fitrah\n\n");
        getScreen().displayMessage("The cost of zakat for one person is $2.5\n");
        getScreen().displayMessage("Enter Number Of Family Members : ");
        members = keypad.getInput();
        
        if(members>0){
            amount = zakat(members);
            if(super.getBankDatabase().getAvailableBalance(getAccountNumber()) >= amount){
                getScreen().displayMessage("Money Paid Is : $ " + amount);
                
                getBankDatabase().debit(getAccountNumber(), amount);
                getBankDatabase().save(4321, amount);
                
                System.out.println("\nSuccess"); 
                System.out.println("Thank You"); 
            }
            else
                System.out.println("Insufficient Balance");
            
        }
        else System.out.println("Cancelling"); 
    }
    
    public double zakat(int members){
        return 2.5 * members;
    }
    
    
}
