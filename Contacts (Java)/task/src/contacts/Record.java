package contacts;
import java.io.Serializable;
import java.time.LocalDateTime;
public abstract class Record implements Serializable {
    private LocalDateTime created;
    private LocalDateTime lastEdited;
    public Record() {
        this.created = LocalDateTime.now();
        this.lastEdited = created;
    }
    public abstract String[] getEditableFields();
    public abstract void editField(String field, String newValue);
    public abstract String getField(String field);
    public LocalDateTime getCreated() {
        return created;
    }
    public LocalDateTime getLastEdited() {
        return lastEdited;
    }
    public void setLastEdited() {
        this.lastEdited = LocalDateTime.now();
    }
    public abstract String getDetails();
}