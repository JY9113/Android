package edu.android.and38_sqlite1;

import android.provider.BaseColumns;

/**
 * Created by itwill on 2017-02-20.
 * MVC 디자인 패턴에서 model에 해당하는 클래스
 * 데이터베이스 테이블의 구조를 클래스로 정의
 */

public class Contact {
    private int _id;    // primary key
    private String cname;
    private String phone;
    private String email;

    // 테이블의 이름과 column의 이름을 정의
    public class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbl_contact";
        public static final String COL_CNAME = "cname";
        public static final String COL_EMAIL = "email";
        public static final String COL_PHONE = "phone";
    }
    public Contact() {}

    public Contact(int _id, String cname, String phone, String email) {
        this._id = _id;
        this.cname = cname;
        this.phone = phone;
        this.email = email;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return cname;
    }
}
