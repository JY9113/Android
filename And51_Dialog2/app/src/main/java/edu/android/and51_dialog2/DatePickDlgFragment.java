package edu.android.and51_dialog2;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickDlgFragment
        extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    // MainActivity에서 구현할 인터페이스 정의
    public interface OnDatePickCallback {
        void onDatePick(int year, int month, int day);
    }

    private OnDatePickCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDatePickCallback) {
            callback = (OnDatePickCallback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    public DatePickDlgFragment() {
        // Required empty public constructor
    }

    public static DatePickDlgFragment newInstance() {
        return new DatePickDlgFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 현재 시간 정보
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    } // end onCreateDialog()

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // MainActivity에게 년/월/일 정보를 전달
        if (callback != null) {
            callback.onDatePick(year, month, dayOfMonth);
        }
    } // end onDateSet()

} // end class DatePickDlgFragment
