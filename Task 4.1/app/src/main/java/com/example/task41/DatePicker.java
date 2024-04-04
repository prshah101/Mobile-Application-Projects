
package tutorials.droid.datepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

// DialogFragment is extended to display a date picker dialog
public class DatePicker extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Get current date
        Calendar mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);
        // Create and return a new DatePickerDialog with the current date. So the calender is automatically on Today's date
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener)
                getActivity(), year, month, dayOfMonth);
    }
}