package edu.android.and04_simpleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnPrev;
    private Button btnNext;
    private ImageView imgView;
    private static final int[] imgStr = {
            R.drawable.n1,
            R.drawable.n2,
            R.drawable.n3,
            R.drawable.n4,
            R.drawable.n5
    };
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrev = (Button)findViewById(R.id.btnPrev);
        btnNext = (Button)findViewById(R.id.btnNext);
        imgView = (ImageView)findViewById(R.id.imageView);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(index > 0){
                    index--;
                    imgView.setImageResource(imgStr[index]);
                } else {
                    Toast.makeText(MainActivity.this, "첫번째 페이지입니다", Toast.LENGTH_SHORT).show();
                    index = 0;
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    if (index <= imgStr.length - 1) {
                        index++;
                        imgView.setImageResource(imgStr[index]);
                    } else {
                        Toast.makeText(MainActivity.this, "마지막 페이지입니다", Toast.LENGTH_SHORT).show();
                        index--;
                    }
                }catch(Exception e) {
                    Toast.makeText(MainActivity.this, "마지막 페이지입니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Bundle 객체에 데이터를 key-value 쌍으로 저장
        int result = MainActivity.this.index;

        outState.putInt("IMAGE", result);
    }

    // onRestoreInstnaceState():
    // 데이터를 복원하는 시점에 호출되는 메소드
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Bundle 객체에서 key값을 사용해서 데이터를 추출 -> 복원
        int result = savedInstanceState.getInt("IMAGE");
        // MSG는 키값.
        MainActivity.this.index = result;
        imgView.setImageResource(imgStr[result]);
    }


}
