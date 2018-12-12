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
        int members;
        getScreen().displayMessage("      Zakat\n\n");
        getScreen().displayMessage("Enter Number Of Family Members : ");
        members = keypad.getInput();
        if(members>0){
            double amount = zakat(members);
            if(super.getBankDatabase().getAvailableBalance(getAccountNumber()) >= amount){
                getBankDatabase().debit(getAccountNumber(), amount);
                getBankDatabase().credit(4321, amount);
                
                System.out.println("Success"); 
                System.out.println("Thank You"); 
            }
        }
        else System.out.println("Cancelling"); 
    }
    
    public double zakat(int members){
        return 2.5 * members;
    }
    
    
}
