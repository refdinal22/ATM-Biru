import java.util.Scanner;
/**
 *
 * @author Luthfi M Agung
 */
public class Payment extends Transaction {
    Scanner scan = new Scanner(System.in);
    private Keypad keypad; // reference to keypad
    private final static int CANCELED = 0;
    
    public Payment(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
    }
    public void otherPayment(){
        System.out.println("Other Payment : ");
        System.out.println("1. Pulsa");
        System.out.println("2. Electricity Token");
        System.out.println("3. Zakat");
        System.out.println("Enter a choice : ");
        int enter = keypad.getInput();
        String number = null;
        double nominal = 0;
        nominal = nominal/15000;
        String eBills = null;
        switch(enter){
            case 1:
                System.out.println("Select the Provider : ");
                System.out.println("1. Telkomsel");
                System.out.println("2. Indosat");
                System.out.println("3. 3(Tri)");
                System.out.println("4. XL");
                System.out.println("5. Axis");
                System.out.println("6. Smartfren");
                System.out.println("Enter a choice : ");
                int tipe = keypad.getInput();
                switch(tipe){
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    System.out.println("Enter your phone number : ");
                    number = scan.next();
                    System.out.println("Please Enter the nominal in dollar ($1 = 10.000) : ");
                    nominal = keypad.getInput();
                    default: System.out.println("Undefined provider");
                }
                System.out.println("The Payment for Number " + number + ", with nominal " + nominal + 
                        " succeed.");
                super.getBankDatabase().payment(super.getAccountNumber(), nominal);
                break;
            case 2:
                System.out.println("Insert your Electricity Bills Number : ");
                eBills = scan.next();
                System.out.println("Please Enter the nominal in dollar ($1 = 10.000) : ");
                nominal = keypad.getInput();
                System.out.println("The Payment for Number " + eBills + ", with nominal " + nominal + 
                        " was succeed.");
                super.getBankDatabase().payment(super.getAccountNumber(), nominal);
                break;
            case 3:
               Transaction temp = new Zakat(getAccountNumber(), getScreen(), getBankDatabase(), keypad);
               temp.execute();
               break;
               
            default: // 
                  getScreen().displayMessageLine(
                     "\nYou did not enter a valid selection. Try again.");
                  break;
        }
    }

    @Override
    public void execute() {
        otherPayment();
    }
}