import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Plan {

    private static final String FILE_PATH = "src\\Plans.txt";

    private String username;
    private String plan;

    public Plan(){}
    public Plan(String username, String plan) {
        this.username = username;
        this.plan = plan;
    }

    public boolean makePlans(String userName,String Plan) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(userName + "," + Plan);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
    }
}