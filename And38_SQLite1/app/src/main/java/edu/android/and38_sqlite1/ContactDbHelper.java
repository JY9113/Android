package edu.android.and38_sqlite1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static edu.android.and38_sqlite1.Contact.ContactEntry.COL_CNAME;
import static edu.android.and38_sqlite1.Contact.ContactEntry.COL_EMAIL;
import static edu.android.and38_sqlite1.Contact.ContactEntry.COL_PHONE;
import static edu.android.and38_sqlite1.Contact.ContactEntry.TABLE_NAME;
import static edu.android.and38_sqlite1.Contact.ContactEntry._ID;

/**
 * Created by itwill on 2017-02-20.
 * SQliteOpenHelper를 상속받는 클래스
 * 테이블 생성, insert, update, delete, select 구현
 */

public class ContactDbHelper extends SQLiteOpenHelper{
    public static final String DB_NAME = "contact.db";
    public static final int DB_VERSION = 1;
    public static final String SQL_CREATE_TABLE = "create table " + TABLE_NAME + " (" + _ID + " integer primary key autoincrement, "
                                                                + COL_CNAME + " text, " + COL_PHONE + " text, " + COL_EMAIL + " text)";
    public static final String SQL_INSERT = "insert into " + TABLE_NAME
                                                        + " (" + COL_CNAME + ", " + COL_PHONE + ", " + COL_EMAIL + ") values(?, ?, ?)";
    public static final String SQL_SELECT = "select * from " + TABLE_NAME + " order by " + _ID;

    public ContactDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 테이블 생성
        Log.i(MainActivity.TAG, "DbHelper : onCreate");
        db.execSQL(SQL_CREATE_TABLE);
        // execSQL : insert, update, delete, select를 제외한 SQL 문장을 실행시킬 때

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(MainActivity.TAG, "DbHelper : onUpgrade");

    }

    public long insertContact(Contact c) {
        Log.i(MainActivity.TAG, "DbHelper: insertContact");
        // SQLiteDatabase 객체를 얻어옴
        // getReadableDatabase(), getWritableDatabase()

        // DB Connect 부분
        SQLiteDatabase db = getWritableDatabase();

        // SQL 문장의 ? 부분을 완성하기 위한 객체 : ContentValues
        ContentValues values = new ContentValues();
        values.put(COL_CNAME, c.getCname());
        values.put(COL_PHONE, c.getPhone());
        values.put(COL_EMAIL, c.getEmail());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public List<Contact> select() {
        List<Contact> list = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(SQL_SELECT, null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++) {
            // 테이블 만들 때의 순서가 getInt() 에서 index 값
            int _id = cursor.getInt(0);
            String cname = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);

            Contact c = new Contact(_id, cname, phone, email);
            list.add(c);
            // 여기까지 레코드 하나를 읽어서 list 에 저장.
            // 다음 레코드를 읽을 수 있도록 moveToNext 해줘야 한다.
            cursor.moveToNext();
        }

        db.close();
        return list;
    }

    public Contact select(int id) {
        // method overoading 사용!
        Contact contact = null;
        SQLiteDatabase db = getReadableDatabase();

        // select * from tbl_contact where _id = ?
        // table, columns(String[] 타입, 선택할 칼럼배열), selection(String 타입, where 구문의 내용-> where는 빼고! )
        // selectionArgs(String[] 타입,where 절의 ?자리를 채울 변수들), groupBy(groupBy 구문의 내용), having( having 구문의 내용)
        // orderBy( orderBy 구문의 내용), 안쓰는 부분은 null

        String[] columns = { _ID, COL_CNAME, COL_PHONE, COL_EMAIL};
        String selection = _ID  + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0){  // count 값이 0 보다커야 값이 있다는 뜻!
            int _id = cursor.getInt(0);
            String cname = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);
            contact = new Contact(_id, cname, phone, email);
        }

        db.close();
        return contact;
    }

    public int updateContact(Contact contact) {
        int result = 0;

        // update tbl_contact set cname = ?, phone = ?, email = ? where _id = ?
        SQLiteDatabase db = getWritableDatabase();
        // table(업데이트 할 테이블 이름), values(ContentValues 타입, set 구문에서 사용될 컬럼과 값),
        // whereClause(String 타입, where 구문의 내용), whereArgs(where 절에서 ? 를 완성시키는 내용)

        ContentValues values = new ContentValues();
        values.put(COL_CNAME, contact.getCname());
        values.put(COL_PHONE, contact.getPhone());
        values.put(COL_EMAIL, contact.getEmail());
        String whereClause = _ID + " =?";
        String[] whereArgs = {String.valueOf(contact.get_id())};

        result = db.update(TABLE_NAME, values, whereClause, whereArgs);
        // 몇 개의 행이 업데이트 되었는지 리턴

        db.close();
        return result;
    }

    public int deleteContact(int id) {
        int result = 0;

        SQLiteDatabase db = getWritableDatabase();
        // table(삭제할 테이블 이름), whereclause(delete 에서 where 구문의 내용), whereArgs( where 절의 ? 완성)
        String whereClause = _ID + " =?";
        String[] whereArgs = {String.valueOf(id)};
        result = db.delete(TABLE_NAME, whereClause, whereArgs);

        db.close();
        return  result;
    }

}
