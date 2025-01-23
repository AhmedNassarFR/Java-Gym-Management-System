import java.io.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Admin implements Person {
    private String userName;
    private String password;

    public Admin(String username, String userPassword) {
        this.userName = username;
        this.password = userPassword;
    }

    public Admin() {
    }

    @Override
    public boolean Login(String userName, String password) {
        String FILE_PATH = "src\\loginInfoAdmin.txt";

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

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean setUsername(String username) {
        this.userName = username;
        return true;
    }

    public String getUsername() {
        return this.userName;
    }

    public boolean AddMember(String name, int age, String username, String pass) throws IOException {
        if (!isUsernameDuplicate("src/loginInfo.txt", username)) {
            Member member = new Member(name, age, username, pass);

            int memberid = member.getId();

            String filePathInfo = "src\\loginInfo.txt";
            //String filePathMC = "src\\membersCoach.txt";

            WriteToFile wtf = new WriteToFile(memberid, name, age, filePathInfo, username, pass);

           // FileHandler.addRecord(filePathMC, username + ",null");

            if (wtf.saveRecord()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean AddCoach(String name, int age, String username, String pass) throws IOException {
        if (!isUsernameDuplicate("src/loginInfoCoaches.txt", username)) {
            Coach coach = new Coach(name, age, username, pass);

            int coachId = coach.getId();

            String filePathInfo = "src\\loginInfoCoaches.txt";
            

            WriteToFile wtf = new WriteToFile(coachId, name, age, filePathInfo, username, pass);

            // FileHandler.addRecord(filePathMC, username + ",null");

            if (wtf.saveRecord()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    private boolean isUsernameDuplicate(String filePath, String newUsername) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    String username = parts[3].trim();

                    if (newUsername.equals(username)) {

                        return true;
                    }
                } else {

                    return false;
                }
            }
        }

        return false;
    }

    public boolean assignMemberToTheirCoach(String memberName, String coachName) {
         String MEMBERS_COACH_FILE = "src\\membersCoach.txt";
         String MEMBERS_FILE = "src\\loginInfo.txt";
         String COACHES_FILE = "src\\loginInfoCoaches.txt";
    
         
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEMBERS_COACH_FILE, true))) {
                // Check if both member and coach exist
                if (userExists(MEMBERS_FILE, memberName) && userExists(COACHES_FILE, coachName)) {
                    String assignmentRecord = String.format("%s,%s\n", memberName, coachName);
                    writer.write(assignmentRecord);
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    
        private boolean userExists(String filePath, String username) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split(",");
                    if (userData.length == 5 && userData[3].trim().equals(username.trim())) {
                        return true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    

    public boolean deleteMember(String username) {
        
        if(FileHandler.deleteRecord("src\\loginInfo.txt", username)&&FileHandler.deleteRecord("src\\membersCoach.txt", username,0))
        return true;
        else
        return false;

    }

    public boolean deleteCoach(String username) {
       if(FileHandler.deleteRecord("src\\loginInfoCoaches.txt", username)&&FileHandler.deleteRecord("src\\membersCoach.txt", username,1))
        return true;
        else
        return false;

    }

     public boolean updateMember(String username, String newName, String newAge, String newPassword) {
        String[] newRecordData = { "", newName, newAge, username, newPassword };
        return FileHandler.updateRecord("src\\loginInfo.txt", username, newRecordData);
    }

    public boolean updateCoach(String username, String newName, String newAge, String newPassword) {
        String[] newRecordData = { "", newName, newAge, username, newPassword };
        return FileHandler.updateRecord("src\\loginInfoCoaches.txt", username, newRecordData);
    }    

    public static List<String> listMembers() {
        List<String> memberInfoList = new ArrayList<>();
        String FILE_PATH = "src\\loginInfo.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                String memberId = parts[0].trim();
                String name = parts[1].trim();
                String age = parts[2].trim();
                String username = parts[3].trim();
                String password = parts[4].trim();

                String memberInfo = "Member ID: " + memberId + "\n" +
                        "Name: " + name + "\n" +
                        "Age: " + age + "\n" +
                        "Username: " + username + "\n" +
                        "Password: " + password + "\n" +
                        "-------------";

                memberInfoList.add(memberInfo);
            }

        } catch (IOException e) {
            e.getMessage();
        }

        return memberInfoList;
    }

    public static List<String> listCoaches() {
        List<String> coachInfoList = new ArrayList<>();
        String FILE_PATH = "src\\loginInfoCoaches.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                String coachId = parts[0].trim();
                String name = parts[1].trim();
                String age = parts[2].trim();
                String username = parts[3].trim();
                String password = parts[4].trim();

                String coachInfo = "Coach ID: " + coachId + "\n" +
                        "Name: " + name + "\n" +
                        "Age: " + age + "\n" +
                        "Username: " + username + "\n" +
                        "Password: " + password + "\n" +
                        "-------------";

                coachInfoList.add(coachInfo);
            }

        } catch (IOException e) {
            e.getMessage();
        }

        return coachInfoList;
    }

    public  String[] searchMembers(String username) {
        String filePath = "src\\loginInfo.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] employeeData = line.split(",");
                if (employeeData.length == 5 && employeeData[3].trim().equals(username.trim())) {

                    return employeeData;
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    public  String[] searchCoach(String username) {
        String filePath = "src\\loginInfoCoaches.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] employeeData = line.split(",");
                if (employeeData.length == 5 && employeeData[3].trim().equals(username.trim())) {

                    return employeeData;
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    public boolean addBill(String username, int durationInDays) {
        Bill bill = new Bill(username, durationInDays);

        if (bill.saveBillingData()) {

            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean updateInfo(String username, String newName, String newPassword) {
        List<String> lines = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader("src\\loginInfoAdmin.txt"))) {
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
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\loginInfoAdmin.txt"))) {
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

    public static List<String> findExpiredSubscriptions() {
    List<String> expiredUsers = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    try (BufferedReader br = new BufferedReader(new FileReader("src\\Billings.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] billingInfo = line.split(",");
            if (billingInfo.length == 5) {
                String username = billingInfo[0];
                String endDateString = billingInfo[3];

                try {

                    Date endDate = dateFormat.parse(endDateString);

                    
                    Calendar currentDate = Calendar.getInstance();
                    currentDate.setTime(new Date());

                   
                    Calendar endDateCal = Calendar.getInstance();
                    endDateCal.setTime(endDate);
                    int durationInDays = Integer.parseInt(billingInfo[1].trim());
                    endDateCal.add(Calendar.DAY_OF_MONTH, durationInDays);

                   
                    if (endDateCal.before(currentDate)) {
                        expiredUsers.add(username);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return expiredUsers;
}

    public boolean notifyExpiredUsers(List<String> expiredUsers) {

        String MESSAGE_FILE_PATH = "src\\Messages.txt";
        String END_OF_SUBSCRIPTION_MESSAGE = "Your subscription has ended. Please renew to continue using our services.";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MESSAGE_FILE_PATH, true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


            for (String username : expiredUsers) {
                writer.write(username + "," + END_OF_SUBSCRIPTION_MESSAGE);
                writer.newLine();
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

        
        public boolean makereport(String memberUsername, String report) {
        String REPORT_FILE = "src\\Report.txt";
        try {
            if (userExists("src\\loginInfo.txt", memberUsername)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE, true))) {
                    String reportRecord = String.format("%s,%s\n", memberUsername, report);
                    writer.write(reportRecord);
                    return true;
                }
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    }



