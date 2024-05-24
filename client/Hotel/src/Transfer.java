import java.util.ArrayList;
import java.util.List;

public class Transfer {

    Account account = new Account();
    List<Account> list = new ArrayList<>();



    public Transfer() {
        this.list = new ArrayList<>();
    }

    public void addAccount(Account account) {
        list.add(account);
    }

    public void credit(String toAccount,double amount) throws AccountNotFoundException {
        for (Account accounts : list) {
            if(accounts.getAccountNo().equals(toAccount)) {
                accounts.setBalance(accounts.getBalance() + amount);
            }

    }


    }
    public void debit(String fromAccount,double amount) throws AccountNotFoundException {
        for (Account accounts : list) {
            if(accounts.getAccountNo().equals(fromAccount))
             accounts.setBalance(accounts.getBalance() - amount);
          }
    }



    public void transferFrom(String fromAccount,String toAccount ,double amount) throws AccountNotFoundException {
        for (Account accounts : list) {
            if (accounts.getAccountNo().equals(fromAccount)) {
               credit(toAccount,amount);
            }
            if(accounts.getAccountNo().equals(toAccount)){
                debit(fromAccount,amount);
            }

        }

        throw new AccountNotFoundException("Account not found");

    }
}

