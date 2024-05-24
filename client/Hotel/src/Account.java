public class Account {
    private String AccountNo;
    private double balance;

    public Account() {

    }

    public String getAccountNo() {
        return AccountNo;
    }
    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account(String accountNo, double balance) {
        AccountNo = accountNo;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "AccountNo='" + AccountNo + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
