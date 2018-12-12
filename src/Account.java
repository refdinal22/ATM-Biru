import java.util.ArrayList;
import java.util.LinkedList; 
import java.util.Queue; 

public class Account {
   private int accountNumber; // account number
   private int pin; // PIN for authentication
   private double availableBalance; // funds available for withdrawal
   private double totalBalance; // funds available & pending deposits
   private String Name;
   private String BankName;
   private boolean blocked;
   public ArrayList<String> History = new ArrayList<>(); //deposit withdrawal
//   private TrfHistory[] trfHis = new TrfHistory[5]; //transfer
   Queue<TrfHistory> lastTrans = new LinkedList<>();
   
   // Account constructor initializes attributes
   public Account(int theAccountNumber, int thePIN, 
      double theAvailableBalance, double theTotalBalance, String name, String Bname) {
      accountNumber = theAccountNumber;
      pin = thePIN;
      availableBalance= theAvailableBalance;
      totalBalance = theTotalBalance;
      Name = name;
      BankName = Bname;
      blocked = false;
   }

   // determines whether a user-specified PIN matches PIN in Account
   public boolean validatePIN(int userPIN) {
      if (userPIN == pin) {
         return true;
      }
      else {
         return false;
      }
   }
   /******** M Fajar **************/
   void SetPIN(int thePIN)
   {
       this.pin=thePIN;
   }
   // returns available balance
   public double getAvailableBalance() {
      return availableBalance;
   } 

   // returns the total balance
   public double getTotalBalance() {
      return totalBalance;
   } 

   public void credit(double amount) {
     totalBalance += amount;
   }

   public void debit(double amount) {
       totalBalance -= amount;
       availableBalance -= amount;  
           
   }
   /***** M Fajar **********/
   public void saving(double amount){
       totalBalance+=amount;
       availableBalance+=amount;
   }
   
   public int getAccountNumber() {
      return accountNumber;  
   }

    public String getName() {
        return Name;
    }
    public String getBankName() {
        return BankName;
    }
    
    /************* LUTHFI ************/
    public boolean isBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    /*********** Daffa *************/
    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }  
    public void addHistory(String his){
       History.add(his);
   }
   
   public void showHistory(){
          if(History.size() >=1 ){
       for(int i = 0 ; i<History.size();i++){
         System.out.println(History.get(i));}
   }else{
           System.out.println("There's no history")   ;
          }
   }
   /**** rico ****/
    public Queue<TrfHistory> getLastTrans() {
        return lastTrans;
    }

    public void setLastTrans(Queue<TrfHistory> lastTrans) {
        this.lastTrans = lastTrans;
    }
    public void show(){
        System.out.println("\n5 Last Transfer History");
        System.out.println("Amount    | To Account");
        int i=1;
        
        for(TrfHistory name : lastTrans){
            System.out.println(i+". "+name.getAmount()+" | "+name.getTrfAccount());
            i++;
        }
    }
    public void showAcc(){
        System.out.println("\n5 Last Account");
        int i=1;
        
        for(TrfHistory name : lastTrans){
            System.out.println(i+". "+name.getTrfAccount());
            i++;
        }
    }
    public int search (int sAcc){
        int i=1;
        
        for(TrfHistory name : lastTrans){
            if(sAcc==i) return name.getTrfAccount();
            i++;
        }
        
       return 0;
    }
    
   /***** Entol ****/
    public int getMaxValue() {
        int idMaxValue = 0;
        int id = 1;
        int i=1;
        
        for(TrfHistory name : lastTrans){
            if(idMaxValue < name.getAmount())  {
                idMaxValue = name.getAmount();
                id = i;
            }
            i++;
        }
        
        return id;
    }

    public int getMinValue() {
        int idMinValue=0;
        int id = 1;
        int i=1;
        
        for(TrfHistory name : lastTrans){
            
            if(idMinValue > name.getAmount())  {
                idMinValue = name.getAmount();
                id = i;
            }
            i++;
        }
        return id;
    }
    public double getAv(){
        double Av=0;
        
        for(TrfHistory name : lastTrans){
            Av = Av + name.getAmount();
        }
        return Av / lastTrans.size();
    }
    
    public TrfHistory searchH (int sAcc){        
        int i=1;
        
        for(TrfHistory name : lastTrans){
            if(sAcc==i) return name;
            i++;
        }
       return null;
    }
} 