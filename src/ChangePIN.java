public class ChangePIN extends Transaction{
    Keypad keypad;
    public ChangePIN(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad Key)
    {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad=Key;
    }
    @Override
   public void execute(){
       if(getCorrectPIN())
       {
           setNewPIN();
       }else {
           System.out.println("PIN incorrect.");
           System.out.println("Canceling change PIN...");
       }
   }
   private boolean getCorrectPIN(){
       System.out.print("Input your recent PIN : ");
       int pin=keypad.getInput();
       return super.getBankDatabase().authenticateUser(super.getAccountNumber(), pin);
   }
   private void setNewPIN(){
       boolean confirmed= false;
       int pin=0;
       while(!confirmed){
       System.out.print("Input your new PIN : ");
       pin=keypad.getInput();
       System.out.print("Konfirm your new PIN : ");
       int pin2=keypad.getInput();
       confirmed=confirmNewPIN(pin, pin2);
       if(confirmed){
           System.out.println("PIN confirmed");
       }else System.out.println("PIN not confirmed. Try again!");
       }
       getBankDatabase().SetPIN(pin, getAccountNumber());
       
   }
   private boolean confirmNewPIN(int pin1, int pin2){
       return pin1==pin2;
   }
}
