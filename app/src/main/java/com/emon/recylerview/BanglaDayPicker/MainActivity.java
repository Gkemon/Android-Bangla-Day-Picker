package com.emon.recylerview.BanglaDayPicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gk.emon.android.OnWeekdaysChangeListener;
import com.gk.emon.android.BanglaDaysPicker;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BanglaDaysPicker widget = (BanglaDaysPicker) findViewById(R.id.weekdays);
        widget.setOnWeekdaysChangeListener(new OnWeekdaysChangeListener() {
            @Override
            public void onChange(View view, int clickedDayOfWeek, List<Integer> selectedDays) {

            }
        });
    }
}
