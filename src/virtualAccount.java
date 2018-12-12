/**
 *
 * @author Refdinal
 */
public class virtualAccount {
    private int code;
    private int receiveAccount;
    private double amount;
    private boolean status;
    
    public virtualAccount(int code, int receiveAccount, double amount){
        this.code = code;
        this.receiveAccount = receiveAccount;
        this.amount = amount;
        status = false;
    }

    public int getCode() {
        return code;
    }
    public int getReceiveAccount() {
        return receiveAccount;
    }
    public double getAmount() {
        return amount;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
