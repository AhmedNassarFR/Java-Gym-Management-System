import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteToFile {

    private int id ;
    private String name;
    private int age ;
    private String filePath;
    private String username;
    private String password;

    WriteToFile(int id,String name,int age,String filePath,String username,String password){
        this.id=id;
        this.name=name;
        this.age=age;
        this.filePath=filePath;
        this.username=username;
        this.password=password;
    }
    public boolean saveRecord() {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            pw.write(id + "," + name + "," + age+","+username+","+password);
            pw.println();
//            System.out.println("Record has been saved");
            return true;
        } catch (IOException e) {
//            System.out.println("Record hasn't been saved");
            e.printStackTrace();
            return false;
        }
    }
}