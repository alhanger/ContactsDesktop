package sample;

/**
 * Created by alhanger on 10/20/15.
 */
public class Contact {
    String name;
    String phoneNum;
    String email;

    public Contact(String name, String phoneNum, String email) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    @Override
    public String toString(){
        return String.format("%s, %s, %s", name, phoneNum, email);
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }
}
