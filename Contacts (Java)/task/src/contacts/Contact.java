package contacts;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public abstract class Contact {
    protected String number;
    protected LocalDateTime created;
    protected LocalDateTime lastEdit;
    public Contact(String number) {
        setNumber(number);
        this.created = LocalDateTime.now();
        this.lastEdit = LocalDateTime.now();
    }
    // Method to validate and set phone number
    public void setNumber(String number) {
        if (isValidPhoneNumber(number)) {
            this.number = number;
            this.lastEdit = LocalDateTime.now(); // Update the last edited time
        } else {
            System.out.println("Wrong number format!");
            this.number = "[no number]";
        }
    }
    public String getNumber() {
        return number;
    }
    public LocalDateTime getCreated() {
        return created;
    }
    public LocalDateTime getLastEdit() {
        return lastEdit;
    }
    // Phone number validation logic
    private boolean isValidPhoneNumber(String number) {
        String regex = "\\+?([A-Za-z0-9]+|\\([A-Za-z0-9]+\\))([- ]([A-Za-z0-9]{2,}|\\([A-Za-z0-9]+\\)))*";
        int openingParenthesisCount = number.length() - number.replace("(", "").length();
        int closingParenthesisCount = number.length() - number.replace(")", "").length();
        if (openingParenthesisCount > 1 || closingParenthesisCount > 1) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
    // Abstract methods for display and edit functionality
    public abstract void displayInfo();
    public abstract void edit(String field, String newValue);
}