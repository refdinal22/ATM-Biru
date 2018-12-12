import java.util.LinkedList; 
import java.util.Queue; 

public class BankDatabase {
   private Account[] accounts; // array of Accounts
   private virtualAccount vAccounts; // array of vAccounts
   
   public BankDatabase() {
      accounts = new Account[4]; // just 2 accounts for testing
      accounts[0] = new Account(901001, 901001, 0.0, 0.0,"ADMIN","BRI");
      accounts[1] = new Account(12345, 54321, 1000.0, 1200.0, "Refdinal","BRI");
      accounts[2] = new Account(8765, 5678, 200.0, 200.0, "Luthfi","BRI");  
      //account untuk menampung zakat
      accounts[3] = new Account(64321, 123, 100.0, 100.0,"Rumah Zakat","BRI");  
      
      //Virtual Account
      vAccounts = new virtualAccount(11223,8765,40); 
   }
   
   private Account getAccount(int accountNumber) {
      for(int i = 0;i<accounts.length;i++){
          if(accountNumber == accounts[i].getAccountNumber()){
              return accounts[i];
          }
      }
       return null; // if no matching account was found, return null
   } 
   
   //untuk mengecek akun yang tersedia
   public boolean availableAccount(int accountNumber){
       return getAccount(accountNumber)!=null;
   }
   //untuk cek virtual account
   public boolean availableVaccount(int code){ 
    if(code == vAccounts.getCode()) return true;          
    return false;
   }
   
   public boolean authenticateUser(int userAccountNumber, int userPIN) {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount(userAccountNumber);

      // if account exists, return result of Account method validatePIN
      if (userAccount != null && !getAccount(userAccountNumber).isBlocked()) {
         return userAccount.validatePIN(userPIN);
      }
      else {
         return false; // account number not found, so return false
      }
   } 

   public double getAvailableBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getAvailableBalance();
   } 

   public double getTotalBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getTotalBalance();
   } 

   public void credit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).credit(amount);
   }

   public void debit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).debit(amount);
   }
   
   public boolean getStat(){
       return vAccounts.isStatus();
   }
   public void setStat(){
       vAccounts.setStatus(true);
   }
   public int getNoVacc(){
       return vAccounts.getCode();
   }
   public double getAmount(){
       return vAccounts.getAmount();
   }
   public int getVccount(){
       return vAccounts.getReceiveAccount();
   }
    /*****************REFDINAL*******************/
    int getAcc(int accountNumber) {
        return getAccount(accountNumber).getAccountNumber();
    }
    String getAccName(int accountNumber) {
        return getAccount(accountNumber).getName();
    }
    String getBName(int accountNumber) {
        return getAccount(accountNumber).getBankName();
    }
    
    /*************************** RIFKY OE ***************************/  
    public boolean isAdmin(int userAccountNumber){
        return (getAccount(userAccountNumber)==accounts[0]);
    }
    
    /************************* LUTHFI *************************/
    
     public void payment(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).debit(amount);
   }
     public void setBlocked(int userAccount){
         getAccount(userAccount).setBlocked(true);
     }
     
     /********** M Fajar ***********/
    void SetPIN(int thePIN, int acc)
   {
       getAccount(acc).SetPIN(thePIN);
   }
    
    public void save(int userAccountNumber,double amount){
       getAccount(userAccountNumber).saving(amount);
   }
    
    /** Daffa **/
   public void setting(int userAccountNumber, double amount){
       getAccount(userAccountNumber).setAvailableBalance(amount);
   }
   public void addHistory(int userAccountNumber, String his){
       getAccount(userAccountNumber).addHistory(his);
   }
   
   public void showHistory(int userAccountNumber){
       getAccount(userAccountNumber).showHistory();
   }
   
   /***** Rico ****/
   public int currsize(int userAcc){
       return getAccount(userAcc).getLastTrans().size();
   }
   public Queue getTrfH(int userAcc){
       return getAccount(userAcc).getLastTrans();
   }
   public void show(int userAcc){
       getAccount(userAcc).show();    
   }
   public void setTrfH(int userAcc,Queue last){
       getAccount(userAcc).setLastTrans(last);
   }
   public void showAcc(int userAcc){
       getAccount(userAcc).showAcc();    
   }
   /** entol**/
   public int getmax(int userAcc){
       return getAccount(userAcc).getMaxValue();    
   }
   
   public int getmin(int userAcc){
       return getAccount(userAcc).getMinValue();    
   }
   
   public double getAvg(int userAcc){
       return getAccount(userAcc).getAv();    
   }
   
   public int search(int userAcc, int sAcc){
       return getAccount(userAcc).search(sAcc);    
   }
    public TrfHistory searchH(int userAcc, int sAcc){
       return getAccount(userAcc).searchH(sAcc);    
   }
    
   
} 
