package com.example.a59070035.healthy.sleep;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a59070035.healthy.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sleep_FormFragment extends Fragment {

    EditText date;
    EditText timeToSleep;
    EditText timeToWakeup;
    FirebaseAuth mAuth;
    Context context;
    Bundle bundle;
    int id;
    public Sleep_FormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sleep__form, container, false);
    }

    public void fetchShow(){
        date = getView().findViewById(R.id.sleep_form_date);
        timeToSleep = getView().findViewById(R.id.sleep_form_timeToSleep);
        timeToWakeup = getView().findViewById(R.id.sleep_form_timeToWakeUp);
        Bundle bundle = this.getArguments();

        if (bundle == null){
            Log.d("System", "Bundle is Empty");
            id = 1150;
        }else{
            date.setText(getArguments().getString("top"));
            timeToSleep.setText(getArguments().getString("bot"));
            timeToWakeup.setText(getArguments().getString("right"));
            id = getArguments().getInt("id");
            Log.d("System", "This is position : "+ id);
        }
        initAddBtn();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        context = getActivity();
        fetchShow();
        initBackBtn();
        initAddBtn();


    }

    public void initBackBtn(){
        Button _backBtn = getView().findViewById(R.id.sleep_form_backBtn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new SleepFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void initAddBtn(){
        Button _addBtn = getView().findViewById(R.id.sleeP_form_addBtn);
        _addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = getView().findViewById(R.id.sleep_form_date);
                timeToSleep = getView().findViewById(R.id.sleep_form_timeToSleep);
                timeToWakeup = getView().findViewById(R.id.sleep_form_timeToWakeUp);


                String dateStr = date.getText().toString();
                String timeToSleepStr = timeToSleep.getText().toString();
                String timeToWakeUpStr = timeToWakeup.getText().toString();
                String uid = mAuth.getCurrentUser().getUid();


                Log.d("System", dateStr + " " + timeToSleepStr + " " + timeToWakeUpStr + " " +uid);
                    Toast.makeText(getActivity(), "Save Complete", Toast.LENGTH_SHORT).show();
                    DBHelper dbHelper = new DBHelper(context);;

                    if (id == 1150){
                        dbHelper.insertTime(new Sleep(dateStr, timeToSleepStr, timeToWakeUpStr, uid));
                    }else{
                        dbHelper.updateTime(new Sleep(dateStr, timeToSleepStr, timeToWakeUpStr, uid), id);

                    }
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new SleepFragment())
                            .addToBackStack(null)
                            .commit();
            }
        });
    }
}
