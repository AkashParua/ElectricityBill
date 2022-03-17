import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//contains all the methods used in Admin and User
abstract class Functionalities {
    static int[] current_date = new int[3];      //current date , month and year are made static i.e same across all instances
    static int penalty;           //the penalty , fine if unpaid for >3 months
    static int industrial_rate;   //Industrial and Personal Rates Constant
    static int domestic_rate;
    static int no_of_users = 0;
    static List<User> database = new ArrayList<User>();     //List of all the users
    public int no_of_days(int n)     //function finds number of days in a given month
    {
        int[] month31 = {1,3,5,7,8,10,12};
        int[] month30 = {4,6,9,11};
        if(binary_search(month31,0,6,n) != -1)
            return 31;
        else if (binary_search(month30,0,3,n) != -1)
            return 30;
        else if(n == 2 && n % 4 == 0)
            return 29;
        else
            return 28;
    }
    static public int[] next_month(int[] date)          //provides the next date of payment
    {
          int[] next_date = new int[3];
          next_date[0] = date[0];
          next_date[1] = date[1] + 1;
          next_date[2] = date[2];
          if(next_date[1]>12) {
              next_date[1] = next_date[1] % 12;
              next_date[2] = date[2] + 1;
          }
          return next_date;
    }
    public int[] previous_month(int[] date,int[] first_date)     //previous payment date of the current month
    {
        int[] prev_date = new int[3];
        prev_date[0] = first_date[0];
        prev_date[1] = date[1] - 1;
        prev_date[2] = date[2];
        if(date[1] == 0)
        {
            prev_date[1] = 12;
            prev_date[2] -= 1;
        }
        return prev_date;
    }

    public int bill_payment(int rate, int month, int status,int usage) //status 0 -> on time ,1 ->fine , -1 -> connection cut , function finds bill for current month
    {
        int sum = 0;
        switch (status)
        {
            case(1):
                 sum = no_of_days(month) * (penalty + rate) * usage ;
                 break;
            case(0):
                sum = no_of_days(month) * rate * usage;
                break;
            case(-1):
                sum = 0;
                break;
        }
        return sum;
    }
    public static int binary_search(int[] arr,int low,int high,int key) //utility function or binary search
    {
        if(low > high)
            return -1;
        int mid = (low + high) /2 ;
        if(arr[mid] ==  key)
            return mid;
        else if(arr[mid] > key)
            return binary_search(arr,low,mid -1,key);
        else
            return binary_search(arr,mid+1,high,key);
    }

}
