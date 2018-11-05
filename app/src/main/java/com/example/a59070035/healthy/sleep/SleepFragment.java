package com.example.a59070035.healthy.sleep;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a59070035.healthy.MenuFragment;
import com.example.a59070035.healthy.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SleepFragment extends Fragment {

    ArrayList<Sleep> _sleeps = new ArrayList<>();
    ListView _sleepList;
    SleepAdapter _sleepAdapter;
    DBHelper dbHelper;

    public SleepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _sleepList = getView().findViewById(R.id.sleep_list);
        _sleepAdapter = new SleepAdapter(getActivity(), R.layout.fragment_sleep, _sleeps);
        showData();
        initBackBtn();
        initAddBtn();
    }

    public void showData(){
        _sleeps.clear();
        dbHelper = new DBHelper(getActivity());
        _sleeps = dbHelper.getAllTimeSleep(_sleeps);
        _sleepList.setAdapter(_sleepAdapter);
        _sleepList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sleep sleep = _sleeps.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("top", sleep.getDate());
                bundle.putString("bot", sleep.getTimeToSleep());
                bundle.putString("right", sleep.getTimeWakeUp());
                bundle.putInt("id", position+1);

                Fragment fragment = new Sleep_FormFragment();
                fragment.setArguments(bundle);

                Log.d("System", "SleepFragment say " + position);


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_view, fragment).addToBackStack(null).commit();
            }
        });

    }
    public void initBackBtn(){
        Button _backBtn = getView().findViewById(R.id.sleep_backBtn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
    public void initAddBtn(){
        Button _addBtn = getView().findViewById(R.id.sleep_addBtn);
        _addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new Sleep_FormFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

}
