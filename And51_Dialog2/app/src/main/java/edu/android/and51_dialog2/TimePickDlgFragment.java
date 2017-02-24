package edu.android.and51_dialog2;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickDlgFragment
        extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public interface OnTimePickCallback {
        void onTimePick(int hour, int minute);
    }

    private OnTimePickCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTimePickCallback) {
            callback = (OnTimePickCallback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    public TimePickDlgFragment() {
        // Required empty public constructor
    }

    public static TimePickDlgFragment newInstance() {
        return new TimePickDlgFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),
                this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
        // DateFormat.is24HourFormat(context):
        // 안드로이드 설정 메뉴에서 설정된 값(24시간/12시간)을
        // 읽어와서 true/false를 리턴하는 메소드
    } // end onCreateDialog()

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (callback != null) {
            callback.onTimePick(hourOfDay, minute);
        }
    } // end onTimeSet()
}
