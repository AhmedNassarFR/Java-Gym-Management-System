import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc= new Scanner(System.in);

        Admin ahmed =new Admin("ahmed","1234");
        List<String> expiredsubs2 = ahmed.findExpiredSubscriptions();
        boolean notif = ahmed.notifyExpiredUsers(expiredsubs2);
            
            
        UserInterface ui=new UserInterface();
        ui.mainMenu();
        


        // Message msg=new Message("hmooda", "hello");
        // msg.saveToFile("src\\Messages.txt");
        // System.out.println(Member.getCoachForMember("zeyad"));
    //    if(ahmed.addBill("hmooda", 60))
    //    System.out.println("zy elfol");
    //    else
    //    System.out.println("baz");


//        ahmed.assignMemberToTheirCoach("hmooda","z3lan");




    //    int input;

    // //    do {
    //    System.out.println("ENTER THE num");
    //    int n= sc.nextInt();
    //    sc.nextLine();

    // //    for (int i = 0; i < n; i++) {

    //        System.out.println("ENTER THE data");
    //        String username= sc.nextLine();
    //        String name= sc.nextLine();
    //        int age= sc.nextInt();
    //        sc.nextLine();
    //        String pass= sc.nextLine();

    //       if(!ahmed.AddCoach(name,age,username,pass)){
    //         //   break;
    //         System.out.println("zyelfl");
    //       }

    //    }
    //         input = sc.nextInt();
    //        sc.nextLine();
    //    }
    //    while( input !=-1);



    }
}