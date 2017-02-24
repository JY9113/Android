package edu.android.and51_dialog2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements DatePickDlgFragment.OnDatePickCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    } // end onCreate()

    public void onClickDate(View view) {
        DatePickDlgFragment dlg =
                DatePickDlgFragment.newInstance();
        dlg.show(getSupportFragmentManager(), "dlg_date");

    } // end onClickDate()

    public void onClickTime(View view) {
        TimePickDlgFragment dlg =
                TimePickDlgFragment.newInstance();
        dlg.show(getSupportFragmentManager(), "dlg_time");
    } // end onClickTime()

    @Override
    public void onDatePick(int year, int month, int day) {
        String text = year + "/" + (month + 1) + "/" + day;
        Button btn = (Button) findViewById(R.id.btnDate);
        btn.setText(text);
    } // end onDatePick()

} // end class MainActivity
