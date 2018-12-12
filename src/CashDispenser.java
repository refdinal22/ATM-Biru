public class CashDispenser{
    // the default initial number of bills in the cash dispenser
    private final static int INITIAL_COUNT = 500;
    private int count; // number of $20 bills remaining
   
    /*************************** UPDATE ***************************/
    // no-argument CashDispenser constructor initializes count to default
    public CashDispenser() {
       count = INITIAL_COUNT ; // set count attribute to default
    }
    
    public int getCashCount(){
        return count;
    }

    // simulates dispensing of specified amount of cash
    public void dispenseCash(int amount) {
        int billsRequired = amount / Denomination.SHARDS; // number of $20 bills required
        count -= billsRequired; // update the count of bills
    }
    
    // simulates refilling dispenser of specified amount of cash
    public void refillCash(int amount){
        int billsRequired = amount / Denomination.SHARDS; // number of $20 bills required
        count += billsRequired; // update the count of bills
    }  
    
   // indicates whether cash dispenser can dispense desired amount
    public boolean isSufficientCashAvailable(int amount) {
        int billsRequired = amount / Denomination.SHARDS; // number of $20 bills required

         // enough bills available if true
        return (count >= billsRequired);
    }
    /************************* END UPDATE *************************/
} 