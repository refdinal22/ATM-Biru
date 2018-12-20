import java.util.Scanner;

public class ATM {
    private boolean userAuthenticated; // whether user is authenticated
    private int currentAccountNumber; // current user's account number
    private Screen screen; // ATM's screen
    private Keypad keypad; // ATM's keypad
    private CashDispenser cashDispenser; // ATM's cash dispenser
    private DepositSlot depositSlot; //
    private BankDatabase bankDatabase; // account information database

    // constants corresponding to main menu options
    private static final int INFO = 1;
    private static final int BALANCE_INQUIRY = 2;
    private static final int ADM_MENU = 3;
    private static final int WITHDRAWAL = 4;
    private static final int DEPOSIT = 5;
    private static final int SAVING = 6;
    private static final int TRANSFER = 7;
    private static final int VACCOUNT = 8;
    private static final int PAYMENT = 9;
    private static final int HISTORY = 10;
    private static final int EXIT = 99;
    private int iBlokir = 0; // Initial Number For Blokir 

    // no-argument ATM constructor initializes instance variables
    public ATM() {
        userAuthenticated = false; // user is not authenticated to start
        currentAccountNumber = 0; // no current account number to start
        screen = new Screen(); // create screen
        keypad = new Keypad(); // create keypad 
        cashDispenser = new CashDispenser(); // create cash dispenser
        bankDatabase = new BankDatabase(); // create acct info database
        depositSlot = new DepositSlot();
    }

    public void run() {
        // welcome and authenticate user; perform transactions
        while (true) {
            // loop while user is not yet authenticated
            while ( !userAuthenticated) {
                screen.displayMessageLine("\nWelcome!");
                authenticateUser(); // authenticate user
                if (iBlokir >= 2) {
                    System.out.println("Your card has been blocked.");
                    if(bankDatabase.availableAccount(currentAccountNumber)) {
                        bankDatabase.setBlocked(currentAccountNumber);
                    }
                    iBlokir = 0;
                }
            }
            performTransactions(); // user is now authenticated
            userAuthenticated = false; // reset before next ATM session
            currentAccountNumber = 0; // reset before next ATM session
            screen.displayMessageLine("\nThank you! Goodbye!");
        }
    }

    // attempts to authenticate user against database
    private void authenticateUser() {
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput(); // input account number
        
        screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
        int pin = keypad.getInput(); // input PIN
        
        // set userAuthenticated to boolean value returned by database
        userAuthenticated=bankDatabase.authenticateUser(accountNumber, pin);
        
        // check whether authentication succeeded
        if (userAuthenticated) {
            currentAccountNumber=accountNumber; // save user's account #
            iBlokir = 0;
        } else {
            if(accountNumber == currentAccountNumber) iBlokir++ ;
            currentAccountNumber=accountNumber; // save user's account #
            screen.displayMessageLine("Invalid account number or PIN. Please try again.");
        }
    }

    // display the main menu and perform transactions
    private void performTransactions() {
        // local variable to store transaction currently being processed
        Transaction currentTransaction=null;

        boolean userExited=false; // user has not chosen to exit

        // loop while user has not chosen option to exit system
        while (!userExited) {
            if(!bankDatabase.isAdmin(currentAccountNumber)) {
                // show main menu and get user selection
                int mainMenuSelection = displayMainMenu();
                
                // decide how to proceed based on user's menu selection
                switch (mainMenuSelection) {
                    // user chose to perform one of three transaction types
                    case INFO:
                        case BALANCE_INQUIRY: case ADM_MENU: case WITHDRAWAL: case DEPOSIT: case SAVING: case TRANSFER: case VACCOUNT: case PAYMENT: case HISTORY: // initialize as new object of chosen type
                        currentTransaction=createTransaction(mainMenuSelection);
                        currentTransaction.execute(); // execute transaction
                        break;
                    case EXIT: // user chose to terminate session
                        screen.displayMessageLine("\nExiting the system...");
                        userExited=true; // this ATM session should end
                        break;
                    default: // 
                        screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
                        break;
                }
            }
            else userExited=maintenance();
        }
    }

    // display the main menu and return an input selection
    private int displayMainMenu() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - Info");
        screen.displayMessageLine("2 - View my balance");
        screen.displayMessageLine("3 - Admin Menu");
        screen.displayMessageLine("4 - Withdraw cash");
        screen.displayMessageLine("5 - Deposit funds");
        screen.displayMessageLine("6 - Saving Money");
        screen.displayMessageLine("7 - Transfer");
        screen.displayMessageLine("8 - Virtual Account");
        screen.displayMessageLine("9 - Payment");
        screen.displayMessageLine("10 - History");
        screen.displayMessageLine("99 - Exit\n");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput(); // return user's selection
    }

    /*************************** UPDATE ***************************/
    private boolean maintenance() {
        Scanner sc=new Scanner(System.in);
        
        //screen.displayMessageLine("1 - Change Denomination");
        screen.displayMessageLine("NOW - "+cashDispenser.getCashCount() +" ply in $"+Denomination.SHARDS+" shards");
        screen.displayMessageLine("1 - Add Cash To Dispenser");
        screen.displayMessageLine("2 - Exit");
        screen.displayMessage("Enter a choice: ");
        int choice=keypad.getInput();
        switch (choice) {
            case 1:
                screen.displayMessage("Add cash to dispenser in $"+Denomination.SHARDS+" denomination : $");
                int nominal = sc.nextInt();
                if(nominal>0) {
                    cashDispenser.refillCash(nominal);
                    screen.displayMessageLine("Operation Succeed add "+nominal/Denomination.SHARDS+" of $"+Denomination.SHARDS);
                }
                else 
                    screen.displayMessageLine("Operation Canceled");
            break;
            case 2 : 
                return true;
                break;
            default : 
                screen.displayMessageLine("You did not enter a valid selection");
                break;
        }
        return false;
    }

    /************************* END UPDATE *************************/
    private Transaction createTransaction(int type) {
        Transaction temp = null;
        switch (type) {
            case INFO:
                temp = new Info(currentAccountNumber, screen, bankDatabase);
                break;
            case BALANCE_INQUIRY:
                temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
                break;
            case ADM_MENU:
                temp = new AdminMenu(currentAccountNumber, screen, bankDatabase, keypad);
                break;
            case WITHDRAWAL:
                temp = new Withdrawal(currentAccountNumber, screen,
                bankDatabase, keypad, cashDispenser);
                break;
            case DEPOSIT:
                temp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
                break;
            case SAVING:
                temp = new Saving(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
                break;
            case TRANSFER:
                temp = new Transfer(currentAccountNumber, screen, bankDatabase, 0, keypad);
                break;
            case VACCOUNT:
                temp = new vAccountMenu(currentAccountNumber, screen, bankDatabase, keypad);
                break;
            case PAYMENT:
                temp = new Payment(currentAccountNumber, screen, bankDatabase, keypad);
                break;
            case HISTORY:
                temp = new History(currentAccountNumber, screen, bankDatabase, keypad);
                break;
        }
        return temp;
    }
}
