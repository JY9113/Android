package edu.android.and38_sqlite1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "edu.android.and38";

    private EditText editId, editName, editEmail, editPhone;
    private Button btnInsert, btnSelect, btnSelectAll, btnDelete, btnUpdate;
    private TextView textResult;
    private ContactDbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editId = (EditText) findViewById(R.id.editId);
        editName = (EditText) findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPhone = (EditText) findViewById(R.id.editPhone);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnSelectAll = (Button) findViewById(R.id.btnSelectAll);
        textResult = (TextView) findViewById(R.id.textResult);

        // ContactDbHelper 객체 생성
        helper = new ContactDbHelper(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertContact();
            }
        });

        btnSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Contact> list = helper.select();
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    Contact c = list.get(i);
                    buffer.append(c.get_id()).append(" | ").append(c.getCname()).append(" | ")
                            .append(c.getPhone()).append(" | ").append(c.getEmail()).append("\n");
                }
                textResult.setText(buffer);
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectById();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact();
            }
        });
    }

    private void insertContact() {
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String phone = editPhone.getText().toString();
        Contact contact = new Contact(0, name, phone, email);
        long result = helper.insertContact(contact);
        textResult.setText(result + " INSERT 성공");
    }

    private void selectById() {
        int id = Integer.parseInt(editId.getText().toString());
        // id 가 integer변환이 되는가, select 결과가 null인가 ... 에러처리!
        Contact contact = helper.select(id);
        editName.setText(contact.getCname());
        editPhone.setText(contact.getPhone());
        editEmail.setText(contact.getEmail());

    }

    private void updateContact() {
        int id = Integer.parseInt(editId.getText().toString());
        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();
        String email = editEmail.getText().toString();

        Contact c = new Contact(id, name, phone, email);
        int result = helper.updateContact(c);

        textResult.setText(result + " 행이 업데이트 되었습니다");
    }

    private void deleteContact(){
        int id = Integer.parseInt(editId.getText().toString());

        int result = helper.deleteContact(id);
        textResult.setText(result + " 행이 삭제되었습니다");
    }
}
