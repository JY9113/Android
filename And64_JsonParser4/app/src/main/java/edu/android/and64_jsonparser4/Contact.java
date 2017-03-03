package edu.android.and64_jsonparser4;

/**
 * Created by itwill on 2017-03-03.
 */

public class Contact {
    private String name;
    private String phone;

    public Contact() {}

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "이름 : " + name + "\n" + "전화번호 : " + phone;
    }
}
