/**
 *
 * @author Daffa
 */
public class SettingAv extends Transaction{
     private Keypad keypad; // reference to keypad
     private BankDatabase bankData;
    public SettingAv(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase,Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        bankData = atmBankDatabase;
    }

    @Override
    public void execute() {
       double setting;
       double AV;
       System.out.println("Setting Minimal balance :  ");
       setting = keypad.getInput();
       if(getBankDatabase().getTotalBalance(getAccountNumber()) >= setting){
           AV = getBankDatabase().getTotalBalance(getAccountNumber()) - setting;
           bankData.setting(getAccountNumber(), AV);

       }
       else System.out.println("Insufficient");
        
       
    }
    
}
