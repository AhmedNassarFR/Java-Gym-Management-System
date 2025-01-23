import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Member implements Person {

    private static int userId;
    private String username;
    private String pass;
    private String name;
    private int age;
    private String filePath = "src/loginInfo.txt";

    public Member(String name, int age, String username, String pass) {
        this.username=username;
        this.pass=pass;
        this.userId = getLastId("src/loginInfo.txt") + 1; 
        this.name = name;
        this.age = age;
    }

    public Member() {

    }

    @Override
    public boolean Login(String userName, String password) {
         String FILE_PATH = "src\\loginInfo.txt";


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


    public String seeEndOfSubscription(String filePath, String username) {
        String endDate = null;
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] subscriptionInfo = line.split(",");
                if (subscriptionInfo.length == 5 && subscriptionInfo[0].equals(username)) {
                    endDate = subscriptionInfo[3];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return endDate;
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

    public String getCoachForMember(String memberName) {

        String filePath = "src\\membersCoach.txt";
        File file = new File(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
               
                String[] parts = currentLine.split(",");
                String currentMemberName = parts[0].trim();
                String currentCoachName = parts[1].trim();

                
                if (currentMemberName.equals(memberName)) {
                 
                    return currentCoachName;
                }
            }

            
            return "Member not found or coach not assigned.";

        } catch (IOException e) {
            e.getMessage();
            return "Error reading file.";
        }
    }


    // private void saveUserInfo(String[] userInfo) {
    //     // Here you can save the user information for later use, for example, in class variables.
    //     // In a real system, you might want to use a more secure way to store user information.

    //     this.name= userInfo[1].trim();
    //     this.age= Integer.parseInt(userInfo[2].trim());

    //     userInfo[4].trim();
    // }

   
    @Override
    public boolean updateInfo(String username, String newName, String newPassword) {
        List<String> lines = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader("src\\logininfo.txt"))) {
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
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\logininfo.txt"))) {
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

    public String[] seeMessages(String filePath, String specifiedUsername) {
        StringBuilder messagesBuilder = new StringBuilder();
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] messageInfo = line.split(",");
                if (messageInfo.length == 2) {
                    String username = messageInfo[0].trim();
                    String message = messageInfo[1].trim();
    
                   
                    if (username.equals(specifiedUsername)) {
                        messagesBuilder.append("- ").append(message).append("\n");
                    }
                } else {
                 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return messagesBuilder.toString().split("\n");
    }
    

    public static String getScheduleByUsername(String username) {
        String schedule = null;

        try (BufferedReader br = new BufferedReader(new FileReader("src\\Schedules.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] scheduleInfo = line.split(",");
                if (scheduleInfo.length == 2 && scheduleInfo[0].equals(username)) {
                    schedule = scheduleInfo[1];
                    break; 
                }
            }
        } catch (IOException e) {
            e.getMessage() ;
        }

        return schedule;
    }

    

    public static String seePlans(String username) {
        String plan = null;

        try (BufferedReader br = new BufferedReader(new FileReader("src\\Plans.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] planInfo = line.split(",");
                if (planInfo.length == 2 && planInfo[0].equals(username)) {
                    plan = planInfo[1];
                    break; 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return plan;
    }


}
