import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
class Main {
    public static void main(String[] args) throws IOException {

        System.out.print("Current Date:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cdate = br.readLine();
        Admin.current_date[0] = Integer.parseInt(cdate.substring(0, 2));
        Admin.current_date[1] = Integer.parseInt(cdate.substring(3, 5));
        Admin.current_date[2] = Integer.parseInt(cdate.substring(6, 10));
        while (true) {
            System.out.print("Login as 1.Admin|2.User :");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            if (choice == 1) {
                while (true) {
                    System.out.print("1.add users|2.inspect users|3.Change Pricing:");
                    int c = sc.nextInt();
                    switch (c) {
                        case 1:
                            Admin.add_user();
                            break;
                        case 2:
                            System.out.print("Enter Use ID:");
                            int id = sc.nextInt();
                            Admin.inspect(Admin.database.get(id));
                            break;
                        case 3:
                            Admin.set_rate();
                            break;
                        default:
                            System.out.println("Wrong Choice --->");

                    }
                    System.out.print("Go to admin/user select  ? 1.yes  2.no :");
                    int k = sc.nextInt();
                    if (k == 1)
                        break;
                }
            } else {
                System.out.print("Enter the user id " + 0 + "to" + (Admin.no_of_users-1) + ":");
                int id = sc.nextInt();
                Admin.database.get(id).payment_processing();
            }
            System.out.print("Exit program ....1.Yes 2.No:");
            int x = sc.nextInt();
            if(x == 1)
                break;

        }

    }
    static {
        System.out.println("*********************************INSTRUCTIONS********************************");
        System.out.println("Do Not forget to set pricing beforehand"); //Instructions for the user
        System.out.println("STEP 1.Login as Admin Set Pricing ");
        System.out.println("STEP 2.Then add Users ");
        System.out.println("STEP 3.Login as User and Pay monthly bil");
        System.out.println("STEP 4.Login as Admin to inspect an user ");
        System.out.println("*****************************************************************************");
    }
}
