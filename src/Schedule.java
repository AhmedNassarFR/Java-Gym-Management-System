import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Schedule {

    private static final String FILE_PATH = "src\\Schedules.txt";

    private String username;
    private String content;

    public Schedule(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public boolean makeSchedule(String username,String content) {
        if (!isUsernameExists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                writer.write(username + "," + content);
                writer.newLine();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isUsernameExists() {
        try {
            return Files.lines(Paths.get(FILE_PATH)).anyMatch(line -> line.startsWith(username + ","));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
