package contacts;
public class Person extends Record {
    private String name;
    private String surname;
    private String birthDate;
    private String gender;
    private String phoneNumber;
    public Person(String name, String surname, String birthDate, String gender, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate.isEmpty() ? "[no data]" : birthDate;
        this.gender = gender.isEmpty() ? "[no data]" : gender;
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String[] getEditableFields() {
        return new String[]{"name", "surname", "birth", "gender", "number"};
    }
    @Override
    public void editField(String field, String newValue) {
        switch (field) {
            case "name": this.name = newValue; break;
            case "surname": this.surname = newValue; break;
            case "birth": this.birthDate = newValue.isEmpty() ? "[no data]" : newValue; break;
            case "gender": this.gender = newValue.isEmpty() ? "[no data]" : newValue; break;
            case "number": this.phoneNumber = newValue; break;
        }
        setLastEdited();
    }
    @Override
    public String getField(String field) {
        switch (field) {
            case "name": return name;
            case "surname": return surname;
            case "birth": return birthDate;
            case "gender": return gender;
            case "number": return phoneNumber;
            default: return "[no data]";
        }
    }
    @Override
    public String getDetails() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Birth date: " + birthDate + "\n" +
                "Gender: " + gender + "\n" +
                "Number: " + phoneNumber + "\n" +
                "Time created: " + getCreated() + "\n" +
                "Time last edit: " + getLastEdited();
    }
}
