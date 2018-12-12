/**
 *
 * @author Rico
 */
public class TrfHistory {
    private int amount;
    private int trfAccount;
    
    public TrfHistory(int amount, int trfAccount){
        this.amount=amount;
        this.trfAccount=trfAccount;
    }

    public int getAmount() {
        return amount;
    }

    public int getTrfAccount() {
        return trfAccount;
    }
    
}
