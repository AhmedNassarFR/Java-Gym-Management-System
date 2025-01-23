import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Coach implements Person {
    private static int userId;
    private String username;
    private String pass;
    private String name;
    private int age;
    private String filePath = "src/loginInfoCoaches.txt";

    public Coach(String name, int age, String username, String pass) {

        this.username=username;
        this.pass=pass;
        this.userId = getLastId("src/loginInfoCoaches.txt") + 1; 
        this.name = name;
        this.age = age;
    }

        public Coach() {
    }

        private static int getLastId(String filePath) {
        int lastId = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 5) { 
                    int id = Integer.parseInt(parts[0].trim());
                    lastId = Math.max(lastId, id);
                }
            }
        } catch (IOException e) {
             e.getMessage();
             
        }

        return lastId;
    }


    public int getId() {
        return userId;
    }



    @Override
    public boolean Login(String userName, String password) {
        String FILE_PATH = "src\\loginInfoCoaches.txt";


        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                
                String[] parts = currentLine.split(",");
                String currentUsername = parts[3].trim();
                String currentPassword = parts[4].trim();

               
                if (currentUsername.equals(userName) && currentPassword.equals(password)) {
                
                    return true;
                    


                }
            }

          
            return false;

        } catch (IOException e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean updateInfo(String username, String newName, String newPassword) {
        List<String> lines = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader("src\\logininfoCoaches.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo.length == 5 && userInfo[3].equals(username)) {
                    
                    userInfo[1] = newName;
                    userInfo[4] = newPassword;
                    line = String.join(",", userInfo);
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\logininfoCoaches.txt"))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean makePlan(String userName,String content)
        {

            Plan plan =new Plan(userName, content);
            if(plan.makePlans(userName,content))
            return true;
            else
            return false;

        }

    public boolean makeSchedule(String userName,String content) {

            Schedule schedule =new Schedule(userName, content);
            if(schedule.makeSchedule(userName,content))
            return true;
            else
            return false;

        }

        public static List<String> seeMembers(String coachUsername) {
            List<String> assignedMembers = new ArrayList<>();
    
            try (BufferedReader reader = new BufferedReader(new FileReader("src\\membersCoach.txt"))) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    String[] parts = currentLine.split(",");
                    if (parts.length == 2) {
                        String memberName = parts[0].trim();
                        String currentCoachUsername = parts[1].trim();
    
                        if (currentCoachUsername.equals(coachUsername)) {
                            assignedMembers.add(memberName);
                        }
                    } else {
                        
                    }
                }
            } catch (IOException e) {
                e.getMessage();
            }
    
            return assignedMembers;
        }    
        
    public boolean writeMessageToMember(String userName,String content ) {

            Message msg =new Message(userName, content);
            if(msg.saveToFile("src\\Messages.txt"))
            return true;
            else
            return false;

        }   
    
}
