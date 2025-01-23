import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill {
    private String username;
    private int durationInDays;
    private Date startDate;
    private Date endDate;
    private String filePath = "src\\Billings.txt";
    private static final double PRICE_PER_30_DAYS = 10.0;
    private double totalPrice;

    public Bill(String username, int durationInDays) {
        this.username = username;
        this.durationInDays = durationInDays;
        this.startDate = new Date(); 

       
        calculateEndDateAndPrice();
    }

    private void calculateEndDateAndPrice() {
    
        long endDateMillis = startDate.getTime() + (durationInDays * 24L * 60 * 60 * 1000);
        this.endDate = new Date(endDateMillis);

     
        int numIntervals = durationInDays / 30;
        this.totalPrice = numIntervals * PRICE_PER_30_DAYS;
    }

    private boolean isUsernameValid(String newUsername) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\loginInfo.txt"))) {
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

    public boolean saveBillingData() {
        try {
            if (isUsernameValid(username)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String billingRecord = String.format("%s,%d,%s,%s,%.2f\n", username, durationInDays,
                            dateFormat.format(startDate), dateFormat.format(endDate), totalPrice);
                    writer.write(billingRecord);
                    return true;
                } catch (IOException e) {
                    e.getMessage();
                    return false;
                }
            } else {
                return false;
            }
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
    }
}