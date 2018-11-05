package com.example.a59070035.healthy.sleep;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a59070035.healthy.R;

import java.text.ParseException;
import java.util.ArrayList;

public class SleepAdapter extends ArrayAdapter<Sleep> {

    ArrayList<Sleep> sleeps = new ArrayList<Sleep>();
    Context context;

    public SleepAdapter(Context context, int resource, ArrayList<Sleep> objects) {
        super(context, resource, objects);
        this.context = context;
        this.sleeps = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_sleep_list, parent, false);
        TextView _top, _bot, _right;
        _top = v.findViewById(R.id.sleep_list_top);
        _bot = v.findViewById(R.id.sleep_list_bot);
        _right = v.findViewById(R.id.sleep_list_right);

        Sleep _row = sleeps.get(position);
        _top.setText(_row.getDate());
        _bot.setText(_row.getTimeToSleep() + " - " +  _row.getTimeWakeUp());
        try {
            _right.setText(_row.calculateTime(_row.getTimeToSleep(), _row.getTimeWakeUp()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            _row.calculateTime(_row.getTimeToSleep(), _row.getTimeWakeUp());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return v;
    }
}
