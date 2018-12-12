/**
 *
 * @author Gita
 */
public class AdminMenu extends Transaction{
    Keypad keypad;
    public AdminMenu(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad keypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        this.keypad = keypad;
    }

    @Override
    public void execute() {
        System.out.println("Admin Menu ");
        System.out.println("1. Change PIN");
        System.out.println("2. Set Available Balance");
        System.out.println("Enter a choice : ");
        int enter = keypad.getInput();
      
        switch(enter){
            case 1:
               Transaction temp = new ChangePIN(getAccountNumber(), getScreen(), getBankDatabase(), keypad);
               temp.execute();
               break;
            case 2:
               Transaction setAv = new SettingAv(getAccountNumber(), getScreen(), getBankDatabase(), keypad);
               setAv.execute();
               break;
            default: // 
                  getScreen().displayMessageLine(
                     "\nYou did not enter a valid selection. Try again.");
                  break;
        }
    }
}
