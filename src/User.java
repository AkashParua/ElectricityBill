
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//class to define each user
class User extends Functionalities {
    String name;        //Name of User
    int type;          //Industrial / Personal use . 1 for personal , 0 for Industrial
    int[] first_date = new int[3];      //Storing the date of the first day of connection
    int daily_usage;
    int id;
    Scanner sc = new Scanner(System.in);
    User(String n, int t, int d, int m, int y, int du,int id) {
        name = n;
        type = t;
        first_date[0] = d;
        first_date[1] = m;
        first_date[2] = y;
        daily_usage = du;
        this.id = id;
    }

    List<Integer> payment_ledger = new ArrayList<Integer>(); //holds the ledger gets updated every month
    List<Integer> pending_ledger = new ArrayList<Integer>(); //records the pending for e very month

    public void payment_processing() {
        int rate;
        int pending_sum = 0;
        if(type == 1)
        {
            rate = domestic_rate;
        }else
        {
            rate = industrial_rate;
        }
        int[] date = new int[3];
        date[0] = first_date[0];
        date[1] = first_date[1];
        date[2] = first_date[2];
        int prev_payment_date[] ;
        int status = 0;
        prev_payment_date = previous_month(current_date,first_date);
        int iterator = 0;
        while (date[1] != prev_payment_date[1] || date[2] != prev_payment_date[2])
        {
            date = next_month(date);
            if(payment_ledger.size()>1)
            {
               if(payment_ledger.get(iterator - 1) == 0)
               {
                   if(payment_ledger.size()>3 && payment_ledger.get(iterator-2) == 0 && payment_ledger.get(iterator - 3) == 0)
                       status = -1;
                   else
                       status = 1;
               }else
                   status = 0;
            }
            if(status == -1)
            System.out.println("Connection Terminated ...... Pay to renew connection..............");
            if(status == 1)
                System.out.println("Late penalty applied to rate .............");
            pending_sum = pending_sum + bill_payment(rate,date[1],status,daily_usage);
            pending_ledger.add(pending_sum);
            System.out.print("Pay "+pending_sum+" on :"+date[0]+"/" + date[1]+ "/"+ date[2]+": (1)Yes/(2)No :");
            int choice = sc.nextInt();
            if(choice == 1) {
                payment_ledger.add(pending_sum);
                pending_sum -= pending_sum;
            }else {
                payment_ledger.add(0);
            }
            iterator ++ ;
        }

    }

}