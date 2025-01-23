import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Subscription {
    private String username;
    private int durationInDays;
    private Date startDate;
    private Date endDate;
    private double totalPrice;

    public Subscription(String username, int durationInDays, Date startDate, Date endDate, double totalPrice) {
        this.username = username;
        this.durationInDays = durationInDays;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public String hasSubscriptionEnded() {
        Date currentDate = new Date();
        return currentDate.after(endDate) ? username : null;
    }

    public static String[] findExpiredSubscriptions(String filePath) {
        String[] expiredUsernames = new String[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String username = parts[0].trim();
                    int durationInDays = Integer.parseInt(parts[1].trim());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date startDate = dateFormat.parse(parts[2].trim());
                    Date endDate = dateFormat.parse(parts[3].trim());
                    double totalPrice = Double.parseDouble(parts[4].trim());

                    Subscription subscription = new Subscription(username, durationInDays, startDate, endDate, totalPrice);
                    String expiredUsername = subscription.hasSubscriptionEnded();
                    if (expiredUsername != null) {
                        expiredUsernames = Arrays.copyOf(expiredUsernames, expiredUsernames.length + 1);
                        expiredUsernames[expiredUsernames.length - 1] = expiredUsername;
                    }
                } else {
                   
                }
            }
        } catch (IOException | ParseException e) {
            e.getMessage();
        }

        return expiredUsernames;
    }

   
    public String getUsername() {
        return username;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}