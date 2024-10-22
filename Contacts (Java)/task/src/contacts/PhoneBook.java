package contacts;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
public class PhoneBook implements Serializable {
    Scanner scanner = new Scanner(System.in);
    private List<Record> records = new ArrayList<>();
    private transient File file;
    public PhoneBook() { }
    public PhoneBook(File file) {
        this.file = file;
        loadFromFile();
    }
    public void addRecord(Record record) {
        records.add(record);
        saveToFile();
    }
    public void removeRecord(int index) {
        records.remove(index);
        saveToFile();
    }
    public int countRecords() {
        return records.size();
    }
    public Record getRecord(int index) {
        return records.get(index);
    }
    public void editRecord(int index, String field, String newValue) {
        Record record = records.get(index);
        record.editField(field, newValue);
        saveToFile();
    }
    public void listRecords() {
        for (int i = 0; i < records.size(); i++) {
            System.out.println((i + 1) + ". " + records.get(i).getField("name"));
        }
    }
    public void displayInfo(int index) {
        System.out.println(records.get(index).getDetails());
    }
    public void search(String query) {
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        List<Record> foundRecords = new ArrayList<>();
        for (Record record : records) {
            String data = record.getField("name") + " " +
                    record.getField("surname") + " " +
                    record.getField("address") + " " +
                    record.getField("number");
            if (pattern.matcher(data).find()) {
                foundRecords.add(record);
            }
        }
        if (foundRecords.isEmpty()) {
            System.out.println("No records found.");
        } else {
            System.out.println("Found " + foundRecords.size() + " results:");
            for (int i = 0; i < foundRecords.size(); i++) {
                System.out.println((i + 1) + ". " + foundRecords.get(i).getField("name"));
            }
            System.out.println("[search] Enter action ([number], back, again): ");
            Scanner scanner = new Scanner(System.in);
            String action = scanner.nextLine();
            if (action.equalsIgnoreCase("back")) {
                return; // Go back to the main menu
            } else if (action.equalsIgnoreCase("again")) {
                System.out.println("Enter search query: ");
                String newQuery = scanner.nextLine();
                search(newQuery); // Repeat search
            } else {
                try {
                    int index = Integer.parseInt(action) - 1;
                    if (index >= 0 && index < foundRecords.size()) {
                        Record selectedRecord = foundRecords.get(index);
                        System.out.println(selectedRecord.getDetails()); // Show full details
                    } else {
                        System.out.println("Invalid index.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        }
    }
    private void saveToFile() {
        if (file == null) return;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(records);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        if (!file.exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            records = (List<Record>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
