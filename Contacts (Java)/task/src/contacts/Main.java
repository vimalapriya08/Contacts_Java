package contacts;
import java.io.File;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook;
        if (args.length > 0) {
            File file = new File(args[0]);
            phoneBook = new PhoneBook(file);
        } else {
            phoneBook = new PhoneBook();
        }
        while (true) {
            System.out.println();
            System.out.println("[menu] Enter action (add, list, search, count, edit, exit): ");
            String action = scanner.nextLine();
            switch (action.toLowerCase()) {
                case "add":
                    System.out.println("Enter the type (person, organization): ");
                    String type = scanner.nextLine();
                    if (type.equalsIgnoreCase("person")) {
                        System.out.println("Enter the name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter the surname: ");
                        String surname = scanner.nextLine();
                        // Validate birth date input
                        String birthDate;
                        System.out.println("Enter the birth date: ");
                        birthDate = scanner.nextLine();
                        if (isValidBirthDate(birthDate)) {
                            // break;
                        } else {
                            birthDate = "[no data]";
                            System.out.println("Bad birth date!");
                        }
                        // Validate gender input
                        String gender;
                        System.out.println("Enter the gender (M, F): ");
                        gender = scanner.nextLine().toUpperCase();
                        if (gender.equals("M") || gender.equals("F")) {
                            //   break;
                        } else {
                            gender = "[no data]";
                            System.out.println("Bad gender!");
                        }
                        System.out.println("Enter the number: ");
                        String number = scanner.nextLine();
                        Person person = new Person(name, surname, birthDate, gender, number);
                        phoneBook.addRecord(person);
                    } else if (type.equalsIgnoreCase("organization")) {
                        System.out.println("Enter the organization name: ");
                        String orgName = scanner.nextLine();
                        System.out.println("Enter the address: ");
                        String address = scanner.nextLine();
                        System.out.println("Enter the number: ");
                        String orgNumber = scanner.nextLine();
                        Organization organization = new Organization(orgName, address, orgNumber);
                        phoneBook.addRecord(organization);
                    } else {
                        System.out.println("Invalid type!");
                    }
                    break;
                case "list":
                    if (phoneBook.countRecords() > 0) {
                        phoneBook.listRecords();
                        System.out.println("Enter index to show info: ");
                        int infoIndex = scanner.nextInt() - 1; // Convert to zero-based index
                        phoneBook.displayInfo(infoIndex);
                        scanner.nextLine(); // Consume the newline
                    } else {
                        System.out.println("No records to display info!");
                    }
                    break;
                case "edit":
                    if (phoneBook.countRecords() > 0) {
                        phoneBook.listRecords();
                        // System.out.println("Select a record: ");
                        int editIndex = 0; // Convert to zero-based index
                        Record record = phoneBook.getRecord(editIndex);
                        if (record instanceof Person) {
                            System.out.println("Select a field (name, surname, birth, gender, number): ");
                        } else {
                            System.out.println("Select a field (address, number): ");
                        }
                        String field = scanner.next();
                        scanner.nextLine(); // Consume the newline
                        System.out.println("Enter new value: ");
                        String newValue = scanner.nextLine();
                        phoneBook.editRecord(editIndex, field, newValue);
                    } else {
                        System.out.println("No records to edit!");
                    }
                    break;
                case "search":
                    System.out.println("Enter search query: ");
                    String query = scanner.nextLine();
                    phoneBook.search(query);
                    break;
                case "count":
                    System.out.println("The Phone Book has " + phoneBook.countRecords() + " records.");
                    break;
                case "exit":
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid action. Please try again.");
                    break;
            }
        }
    }
    private static boolean isValidBirthDate(String birthDate) {
        // Simple validation for format YYYY-MM-DD
        // You can expand this with a regex or date parsing for better validation
        return birthDate.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}