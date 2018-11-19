package com.example.a59070035.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a59070035.healthy.post.PostFragment;
import com.example.a59070035.healthy.sleep.SleepFragment;
import com.example.a59070035.healthy.weight.WeightFragment;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Brightonter on 9/8/2018.
 */

public class MenuFragment extends Fragment {
    FirebaseAuth mAuth;
    String[] list = {"BMI", "WEIGHT", "SLEEP","POST", "SIGN OUT"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        listMenu();
    }

    public void listMenu(){
        final ArrayAdapter<String> _menuAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list);
        ListView _menuList = (ListView) getView().findViewById(R.id.menu_list);
        _menuList.setAdapter(_menuAdapter);
        _menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (_menuAdapter.getItem(position).equals("BMI")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new BmiFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("System", "[Menu] Go to BMI");
                }else if(_menuAdapter.getItem(position).equals("WEIGHT")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new WeightFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("System", "[Menu] Go to Weight");
                }else if (_menuAdapter.getItem(position).equals("SIGN OUT")){
                    mAuth.signOut();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new LoginFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("System", "[Menu] Go to Logout");
                    Toast.makeText(getActivity(), "Sign out complete", Toast.LENGTH_SHORT).show();
                }else if (_menuAdapter.getItem(position).equals("SLEEP")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new SleepFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("System", "[MENU] Go to Sleep");
                }else if (_menuAdapter.getItem(position).equals("POST")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new PostFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
}
