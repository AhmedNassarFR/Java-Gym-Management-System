import java.util.List;
import java.util.Scanner;


public class UserInterface {


Scanner input = new Scanner(System.in);
boolean loginResult= false;
public void mainMenu(){
    System.out.println("Login As (1-member, 2-coach, 3-admin  -1-exit): ");
    int menuNum= input.nextInt();
    input.nextLine();
switch (menuNum) {
    case 1:
    Member member=new Member();
    System.out.println("Enter Username: ");
    String username=input.nextLine();
    System.out.println("Enter password: ");
    String pass = input.nextLine();
    loginResult = member.Login(username,pass);
    if (loginResult) {
        System.out.println("Login successful!");


        System.out.println("select action:");
        MemberMenu(username);

        
        break;



    } else {
        System.out.println("Login failed.");
    }
        break;
    case 2:
    Coach coach= new Coach();
    System.out.println("Enter Username: ");
    String cUsername=input.nextLine();
    System.out.println("Enter password: ");
    String cPass = input.nextLine();
    boolean cLoginResult = coach.Login(cUsername,cPass);
    if (cLoginResult) {
        System.out.println("Login successful!");


        System.out.println("select action:");
        CoachMenu(cUsername);

        break;

        

    } else {
        System.out.println("Login failed.");
    }
        break;
    case 3:
    Admin admin=new Admin();
    System.out.println("Enter Username: ");
    String aUsername=input.nextLine();
    System.out.println("Enter password: ");
    String aPass = input.nextLine();
    boolean aLoginResult = admin.Login(aUsername,aPass);
    if (aLoginResult) {
        System.out.println("Login successful!");



        AdminMenu(aUsername);


    } else {
        System.out.println("Login failed.");
    }
        break;

    case -1:
    System.out.println("goodbye");
    break;
  
    default:
        break;
    
        
}
}

public void MemberMenu(String username) {
    String filePath = "src\\Billings.txt";
    Member member = new Member();

    int action;

    do {
        System.out.println("1- see expiration date. 2-get coach name. 3-check notifications. 4-see your coach plans. 5-get your schedule. 6-change name and password. -1-EXIT: ");
        action = input.nextInt();
        input.nextLine(); 

        switch (action) {
            case 1://see end of sub
                String endDate = member.seeEndOfSubscription(filePath, username);
                System.out.println("The end of your subscription is in: " + endDate);
                break;
            case 2://get your coach name
                String coachMember = member.getCoachForMember(username);
                System.out.println("Your coach is " + coachMember);
                break;
            case 3://check notifications
                String messagePath = "src\\Messages.txt"; 
                String[] allMessages = member.seeMessages(messagePath,username);

                if (allMessages.length > 0) {
                    System.out.println("Messages:");
                    for (String message : allMessages) {
                        System.out.println(message);
                    }break;
                } else {
                    System.out.println("No messages found");
                }
                break;
            case 4://see your coach plans
                String plan = member.seePlans(username);
                if (plan != null) {
                    System.out.println("plan for " + username + ": " + plan);
                } else {
                    System.out.println("No plans found for this username.");
                }
                break;
            case 5://get your schedule
                String schedule = member.getScheduleByUsername(username);

                if (schedule != null) {
                    System.out.println("Schedule for " + username + ": " + schedule);
                } else {
                    System.out.println("No schedule found for this username.");
                }
                break;
            case 6://change name and password
                System.out.println("Enter your new name: ");
                String newname = input.nextLine();
                System.out.println("Enter your new password: ");
                String newpassword = input.nextLine();
                if (member.updateInfo(username, newname, newpassword)){
                    System.out.println("info updated successfully");
                    break;
                } 
                else{
                    System.out.println("Failed to update info");
                }
                break;
            case -1:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                break;
        }
    } while (action != -1);
}



public void CoachMenu(String cUsername){
    String filePath = "";
    Coach coach = new Coach();
    

    int action;

    do {
        System.out.println("1- make a plan for a member 2-make a schedule for a member 3-list your members 4-send a message for a member 5-change name and password -1-EXIT: ");
        action = input.nextInt();
        input.nextLine(); 
        switch (action) {
            case 1://
                System.out.println("Enter the member username");
                String username = input.nextLine();
                System.out.println("Enter the plan for the member");
                String content = input.nextLine();

                Plan plan = new Plan( );

                if (plan.makePlans(username, content)){
                    System.out.println("Plan has been added");

                    break;
                }
            case 2 : 
            
                System.out.println("Enter the member username");
                String username2 = input.nextLine();
                System.out.println("Enter the schedule for the member");
                String content2 = input.nextLine();

                Schedule schedule=new Schedule(username2, content2);
                if (schedule.makeSchedule(username2, content2)){
                    System.out.println("schedule has been added");

                    break;
                }
                break;

            case 3 :
           
            
                // System.out.println("Enter the coach username");
                // String username3 = input.nextLine();
                
                // System.out.print("\nViewing all members\n");
                List<String> list=coach.seeMembers(cUsername); 
                
                if(!list.isEmpty()){
                for(String member:list){
                    System.out.println(member+" ");
                }
                }
                else {
                    System.out.println("No assigned members found for Coach " + cUsername);
                }
                break;

            case 4:
            
                System.out.println("Enter the member username");
                String username4 = input.nextLine();
                System.out.println("Enter the message");
                String content4 = input.nextLine();

                  
                if (coach.writeMessageToMember(username4, content4 + " -"+cUsername)){
                    System.out.println("the message has been sent");

                    break;
                }else{
                    System.out.println("The user does not exist in the system or there have been some error");
                }




            
            case 5:
                System.out.println("Enter your new name:");
                String newName= input.nextLine();
                System.out.println("Enter your new password:");
                String newPassword = input.nextLine();

                if (coach.updateInfo(cUsername,newName, newPassword)) {
                    System.out.println("Password updated successfully.");
                } else {
                    System.out.println("Failed to update password.");
                }


            case -1:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                break;
        }
    } while (action != -1);
}

public void AdminMenu(String aUsername){
    Admin admin=new Admin();

  
    int action;

    do {
        System.out.println("MEMBER:1- add 2-update 3-delete 4-list 5- search | COACH: 6- add 7- update 8- delete 9- list 10- search");
        System.out.println("11- assign mem to coach 12- add a bill for a mem 13-update own info - 14- find expired subs 15- notify expired subs 16-make a report for a member -1-EXIT");  
        action = input.nextInt();
        input.nextLine(); 

        switch (action) {
            case 1://addMem
            System.out.println("Enter the member name");
                String name = input.nextLine();
            System.out.println("Enter the member age");
                int age = input.nextInt();
                input.nextLine(); 
            System.out.println("Enter the member username");
                String username = input.nextLine();
            System.out.println("Enter the member pass");
                String pass = input.nextLine();            

            try{
              if (admin.AddMember(name,age,username, pass))
              System.out.println("Member has been added");
              else
              System.out.println("Failed to add Member");
            }
              catch(Exception e){System.out.println("Error with adding user:"+e);}


              break;
            case 2 ://updateM 
                System.out.println("Enter the member username");
                    String username2 = input.nextLine();
                System.out.println("Enter the member new name");
                    String name2 = input.nextLine();
                    
                System.out.println("Enter the member new age");
                    String age2 = input.nextLine();
                    
                System.out.println("Enter the member new password");
                    String pass2 = input.nextLine();     
                
                try{
              if (admin.updateMember(username2,name2,age2,pass2))
              System.out.println("Member has been updated");
              else
              System.out.println("Failed to update Member");
            }
              catch(Exception e){System.out.println("Error with updating user:"+e);}    
                    


              break;
            case 3 ://deleteM
                System.out.println("Enter the member username");
                    String username3 = input.nextLine();
                if(admin.deleteMember(username3))
                    System.out.println("the member has been deleted");
                else
                    System.out.println("The member does not exist or there has been an error");
            

                break;    
            case 4://listMs
            List<String> members = admin.listMembers();

            if (!members.isEmpty()) {
                System.out.println("Member Information:");
                for (String memberInfo : members) {
                    System.out.println(memberInfo);
                }
            } else {
                System.out.println("No members found");
            }
            
                break;              

            
            case 5://searchMs
                System.out.println("Enter a member's username to look for: ");
                String usernameToSearch=input.nextLine();
                String[] memberInfo = admin.searchMembers(usernameToSearch);

                if (memberInfo != null) {
                    System.out.println("Member found:");
                    for (String info : memberInfo) {
                        System.out.println(info);
                    }
                    break;
                } else {
                    System.out.println("Member not found");
                }
                break;
            

            case 6://addCoach
            System.out.println("Enter the coach name");
                String name6 = input.nextLine();
            System.out.println("Enter the coach age");
                int age6 = input.nextInt();
                input.nextLine(); 
            System.out.println("Enter the coach username");
                String username6 = input.nextLine();
            System.out.println("Enter the coach pass");
                String pass6 = input.nextLine();            

            try{
              if (admin.AddCoach(name6,age6,username6, pass6))
              System.out.println("coach has been added");
              else
              System.out.println("Failed to add coach");
            }
              catch(Exception e){System.out.println("Error with adding user:"+e);}


              break;
            case 7 ://updatec 
                System.out.println("Enter the coach username");
                    String username7 = input.nextLine();
                System.out.println("Enter the coach new name");
                    String name7 = input.nextLine();
                    
                System.out.println("Enter the coach new age");
                    String age7 = input.nextLine();
                    
                System.out.println("Enter the coach new password");
                    String pass7 = input.nextLine();     
                
                try{
              if (admin.updateCoach(username7,name7,age7,pass7))
              System.out.println("coach has been updated");
              else
              System.out.println("Failed to update coach");
            }
              catch(Exception e){System.out.println("Error with updating user:"+e);}    
                    


              break;
            case 8 ://deletec
                System.out.println("Enter the coach username");
                    String username8 = input.nextLine();
                if(admin.deleteCoach(username8))
                    System.out.println("the coach has been deleted");
                else
                    System.out.println("The coach does not exist or there has been an error");
            

                break;    
            case 9://listcs
            List<String> coaches = admin.listCoaches();

            if (!coaches.isEmpty()) {
                System.out.println("coach Information:");
                for (String coachInfo : coaches) {
                    System.out.println(coachInfo);
                }
            } else {
                System.out.println("No coaches found");
            }
            
                break;              

            
            case 10://searchcs
                System.out.println("Enter a coach's username to look for: ");
                String usernameToSearch10=input.nextLine();
                String[] coachInfo = admin.searchCoach(usernameToSearch10);

                if (coachInfo != null) {
                    System.out.println("coach Information for Username " + usernameToSearch10 + ":");
                    for (String info : coachInfo) {
                        System.out.println(info);
                    }
                } else {
                    System.out.println("No coach found with the username " + usernameToSearch10);
                }
                break;

            
            case 11://assign member to coach
                System.out.println("Enter the member's username");
                String username11= input.nextLine();
                System.out.println("Enter the coach's username");
                String coach11 = input.nextLine();
                if (admin.assignMemberToTheirCoach(username11,coach11 )){
                    System.out.println("member assigned successfully");
                    break;
                }
                else
                     System.out.println("Failed to assign member");
                break;

            case 12://addbill
                System.out.println("Enter the member's username: ");
                String username12= input.nextLine();
                System.out.println("Enter the duration in days: ");
                int durationInDays = input.nextInt();
                input.nextLine();
                if (admin.addBill(username12, durationInDays)){
                    System.out.println("Bill added successfully");
                    break;
                }
                else{
                    System.out.println("Failed to add bill");
                }
                break;
            case 13://update info
                System.out.println("Enter your new name: ");
                String newname = input.nextLine();
                System.out.println("Enter your new password: ");
                String newpassword = input.nextLine();
                if (admin.updateInfo(aUsername, newname, newpassword)){
                    System.out.println("info updated successfully");
                    break;
                } 
                else{
                    System.out.println("Failed to update info");
                }
                break;



            case 14://find expired subs
            
            List<String> expiredsubs = admin.findExpiredSubscriptions();

            if (!expiredsubs.isEmpty()) {
                System.out.println("Expired subs are: ");
                for (String member : expiredsubs) {
                    System.out.println(member);
                    
                }
                break;
            } else {
                System.out.println("No expired subs");
            }
            break;


            case 15://notfiy expired subs
            List<String> expiredsubs2 = admin.findExpiredSubscriptions();
            boolean notif = admin.notifyExpiredUsers(expiredsubs2);
            if (notif) {
                System.out.println("Message added successfully");
                break;
                }
                
             else {
                System.out.println("No expired subs");
            }
            break;


             case 16://make report
                System.out.println("Enter the username: ");
                String memuser = input.nextLine();
                System.out.println("Enter the report: ");
                String Report = input.nextLine();
                if (admin.makereport(memuser, Report)){
                    System.out.println("Report has been made");
                    break;
                }
                else
                    System.out.println("error in creating report");
                break;                   






            case -1:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                break;
        }
    } while (action != -1);






    
}


    
}
