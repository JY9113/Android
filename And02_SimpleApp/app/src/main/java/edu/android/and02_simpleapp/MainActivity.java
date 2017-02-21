package edu.android.and02_simpleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button btn;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Layout 리소스 중에서 activity_main을 찾아서
        // 레이아웃(xml파일) 안에 있는 모든 위젯들을 생성해주는 역할
        setContentView(R.layout.activity_main);
        // 생성된 위젯을 찾는 방법
        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                textView.setText(msg);
            }
        });
    }
}
