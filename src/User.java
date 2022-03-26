
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//class to define each user
class User extends Functionalities {
    String name;        //Name of User
    int type;          //Industrial / Personal use . 1 for personal , 0 for Industrial
    int[] first_date = new int[3];      //Storing the date of the first day of connection
    double daily_usage;
    int id;
    Scanner sc = new Scanner(System.in);
    User(String n, int t, int d, int m, int y, double du,int id) {
        name = n;
        type = t;
        first_date[0] = d;
        first_date[1] = m;
        first_date[2] = y;
        daily_usage = du;
        this.id = id;
    }

    List<Double> payment_ledger = new ArrayList<>(); //holds the ledger gets updated every month
    List<Double> pending_ledger = new ArrayList<>(); //records the pending for e very month

    public void payment_processing() {       //processing of the payment process
        DecimalFormat df = new DecimalFormat("#.##");
        double rate;
        double pending_sum = 0;
        if(type == 1)
        {
            rate = domestic_rate;
        }else
        {
            rate = industrial_rate;
        }
        System.out.println("Rate per Unit:"+rate);
        int[] date = new int[3];
        date[0] = first_date[0];
        date[1] = first_date[1];
        date[2] = first_date[2];
        int[] prev_payment_date ;
        int status = 0;
        prev_payment_date = previous_month(current_date,first_date);
        int iterator = 0;
        if(payment_ledger.size()> 0) {
            System.out.println("Already Paid:");
            Admin.inspect(this);         //if the payment is already done ledger is printed
            return;
        }
        while (date[1] != prev_payment_date[1] || date[2] != prev_payment_date[2])
        {
            date = next_month(date);
            if(payment_ledger.size()>=1)
            {
               if(payment_ledger.get(iterator - 1) == 0)
               {
                   if(payment_ledger.size()>=3 && payment_ledger.get(iterator-2) == 0 && payment_ledger.get(iterator - 3) == 0)
                       status = -1;
                   else
                       status = 1;
               }else
                   status = 0;
            }
            if(status == -1)
            System.out.println("!!!!Connection Terminated!!!! < Pay to renew connection >");
            if(status == 1)
                System.out.println("Late penalty applied to rate/unit +"+penalty);
            pending_sum = pending_sum + bill_payment(rate,date[1],date[2],status,daily_usage);
            pending_ledger.add(pending_sum);
            if(status != -1)
            System.out.println("Units Used: "+df.format(no_of_days(date[1],date[2])*daily_usage));
            System.out.print("Pay "+df.format(pending_sum)+" on :"+date[0]+"/" + date[1]+ "/"+ date[2]+": (1)Yes/(2)No :");
            int choice = sc.nextInt();
            if(choice == 1) {
                payment_ledger.add(pending_sum);
                pending_sum -= pending_sum;
            }else {
                payment_ledger.add(0.00);
            }
            iterator ++ ;
        }

    }

}