import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Message {
    private String username;
    private String content;

    public Message(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public boolean saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(username + "," + content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    
}
