package edu.android.and05_lifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG ="edu.android.and05";

    private TextView text;
    private EditText edit;
    private Button btn;



    // onCreate() : 생명주기(Life cycle) 메소드 중 하나
    // 앱이 처음 실행될 때 ART(Android Run-Time)이 호출하는 메소드
    // 레이아웃과 위젯들이 생성되지만 화면에 보이지는 않는 상태(invisible)
    // setContentView()로 xml파일에 있는 레이아웃들 생성
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate 메소드");

        text = (TextView)findViewById(R.id.textView);
        edit = (EditText)findViewById(R.id.editText);
        btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String msg = edit.getText().toString();
                text.setText(msg);
            }
        });

    }

    // 액티비티 클래스가 가지고 있는 onStart를 override
    // onStart() : onCreate() 이후에 호출되는 생명주기 메소드
    // 화면에 보이는 상태가 된다(visible)
    // 화면에는 보이지만 이벤트 처리는 할 수 없는 상태 - > focus를 받지 않은 상태
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart 메소드");
    }

    // onResume() : onStart() 다음에 호출되는 생명 주기 메소드
    // 화면에 보이는 상태
    // 이벤트 처리도 가능한 상태
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume 메소드");
    }

    // onPause() : 앱이 백그라운드로 가거나 종료될 때 불리는 메소드
    // 화면에는 보이는 상태
    // 이벤트 처리를 할 수 없는 상태로 바뀜
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause 메소드");
    }

    // onStop() : onPause() 이후에 호출되는 생명주기 메소드
    // 화면에서 보이지 않는 상태, 이벤트 처리 할 수 없는 상태
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop 메소드");
    }

    // onDestroy() : onStop() 이후에 호출되는 생명주기 메소드
    // 레이아웃과 위젯 객체들이 모두 소멸
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy 메소드");
    }

    // onRestart() : onStop() 상태에서 onStart() 상태로 바뀔 때 중간에 호출되는 생명주기
    // onStop() -> onRestart() -> onStart()
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart 메소드");
    }

    // onSaveInstanceState() :
    // 앱이 (stop/destroy 된 후) 다시 시작할 때 필요한 데이터를 저장할 때 사용하는 메소드

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Bundle 객체에 데이터를 key-value 쌍으로 저장
        String msg = text.getText().toString();

        outState.putString("MSG", msg);
        Log.i(TAG, " 상태 저장 됨.....");
    }

    // onRestoreInstnaceState():
    // 데이터를 복원하는 시점에 호출되는 메소드
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Bundle 객체에서 key값을 사용해서 데이터를 추출 -> 복원
        String msg = savedInstanceState.getString("MSG");
        // MSG는 키값.
        text.setText(msg);
        Log.i(TAG, "데이터 복원 됨......");
    }
}
