import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Scanner;
//class defines an Admin
class Admin extends Functionalities{

    static public void set_rate()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Set Industrial Rate / Unit :");
        industrial_rate = sc.nextDouble();
        System.out.print("Set Domestic Rate / Unit :");
        domestic_rate   = sc.nextDouble();
        System.out.print("Set Penalty Rate:");
        penalty = sc.nextDouble();
    }
    static public void inspect(User user)
    {
        DecimalFormat df = new DecimalFormat("#.##");
        int[] date = user.first_date;
        System.out.println("ID:"+ user.id +   "| User Name:" + user.name + " |Type " + user.type + "| First Connection Date: "+date[0]+"/"+date[1]+"/"+date[2]);
        System.out.println("__________________________________________________________________________________________________________________________________");
        for(int i = 0; i< user.payment_ledger.size() ; i++)
        {
            date = next_month(date);
            System.out.println("Date:" + date[0] + "/" + date[1] + "/" + date[2] + "-->"+ "|Amount Payable:" + df.format(user.pending_ledger.get(i)) + "|Amount Payed:"+ df.format(user.payment_ledger.get(i)));
        }
    }
    static public void add_user() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name:");
        String name = br.readLine();
        System.out.print("Enter Connection Date dd/MM/YYYY:");
        String date = br.readLine();
        int day = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(3,5));
        int year = Integer.parseInt(date.substring(6,10));
        System.out.print("Enter Average Daily consumption:");
        double daily_used = Double.parseDouble(br.readLine());
        System.out.print("Enter type of use: 1.Domestic |2.Industrial :");
        int type = sc.nextInt();
        User user = new User(name,type,day,month,year,daily_used,no_of_users++);
        database.add(user);

    }


}
