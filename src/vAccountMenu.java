/**
 *
 * @author Refdinal
 */
public class vAccountMenu extends Transaction{
    BankDatabase bDB;
    Keypad keypad;
    
    public vAccountMenu(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad keypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        bDB = atmBankDatabase;
        this.keypad = keypad;
    }

    @Override
    public void execute() {
        int code;
        getScreen().displayMessage("Enter No Virtual Account : ");
        code = keypad.getInput();
        if(bDB.availableVaccount(code) && !bDB.getStat()){
            System.out.println("Amount             : "+bDB.getAmount());
            System.out.println("No Virtual Account : "+bDB.getNoVacc());
            getScreen().displayMessage("Pay ? (1,0)");
            if(keypad.getInput()==1 && bDB.getAvailableBalance(getAccountNumber()) >= bDB.getAmount() ){
                getBankDatabase().debit(getAccountNumber(), bDB.getAmount());
                getBankDatabase().credit(bDB.getVccount(), bDB.getAmount());
                System.out.println("Success");
                bDB.setStat();
            }
            else System.out.println("Cancel Transaction");
        }
        else System.out.println("No Virtual Account Not Found");
    }
}
