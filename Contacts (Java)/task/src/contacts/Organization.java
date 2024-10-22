package contacts;
public class Organization extends Record {
    private String orgName;
    private String address;
    private String phoneNumber;
    public Organization(String orgName, String address, String phoneNumber) {
        this.orgName = orgName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String[] getEditableFields() {
        return new String[]{"name", "address", "number"};
    }
    @Override
    public void editField(String field, String newValue) {
        switch (field) {
            case "name": this.orgName = newValue; break;
            case "address": this.address = newValue; break;
            case "number": this.phoneNumber = newValue; break;
        }
        setLastEdited();
    }
    @Override
    public String getField(String field) {
        switch (field) {
            case "name": return orgName;
            case "address": return address;
            case "number": return phoneNumber;
            default: return "[no data]";
        }
    }
    @Override
    public String getDetails() {
        return "Organization name: " + orgName + "\n" +
                "Address: " + address + "\n" +
                "Number: " + phoneNumber + "\n" +
                "Time created: " + getCreated() + "\n" +
                "Time last edit: " + getLastEdited();
    }
}