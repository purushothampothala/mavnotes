import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[]args) throws AccountNotFoundException {
        Transfer transfer= new Transfer();
        Account account=new Account();
        List<Account>accountlist=new ArrayList<>();

        Account acc=new Account("123456",10000);
        Account acc1=new Account("567891",2000);
        transfer.addAccount(acc);
        transfer.addAccount(acc1);
       accountlist.add(acc);
       accountlist.add(acc1);
        System.out.println(accountlist);
        //System.out.println(transfer.transferTo(acc1,1000));
try {
    transfer.transferFrom("123456", "567891", 1000);

}catch (AccountNotFoundException e){
    e.printStackTrace();

}
        System.out.println(acc1);
        System.out.println(acc);


    }
}
