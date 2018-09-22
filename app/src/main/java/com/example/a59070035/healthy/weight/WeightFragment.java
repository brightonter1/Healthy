package com.example.a59070035.healthy.weight;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a59070035.healthy.MenuFragment;
import com.example.a59070035.healthy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.sql.DatabaseMetaData;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * Created by Brightonter on 9/8/2018.
 */

public class WeightFragment extends Fragment {

    ArrayList<Weight> weights = new ArrayList<>();
    FirebaseFirestore mDB;
    FirebaseAuth _auth;
    ListView _weightList;
    WeightAdapter _weightAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _weightList = (ListView) getView().findViewById(R.id.weight_list);
        _weightAdapter = new WeightAdapter(getActivity(), R.layout.fragment_weight_list, weights);
        mDB = FirebaseFirestore.getInstance();
        _auth = FirebaseAuth.getInstance();
        initAdd();
        getDataForm();
        backBtn();
    }

    public ArrayList<Weight> checkUpDown(ArrayList<Weight> _weights){

        int count = _weights.size()-1;
        for (int i = count ; i > 0 ; i--){
            if(_weights.get(i).getWeight() < _weights.get(i-1).getWeight()){
                _weights.get(i-1).setStatus("UP");
            }else if(_weights.get(i).getWeight() > _weights.get(i-1).getWeight()){
                _weights.get(i-1).setStatus("DOWN");
            }else if(_weights.get(i).getWeight() == _weights.get(i-1).getWeight()){
                _weights.get(i-1).setStatus("");
            }
            Log.d("System",_weights.get(i).getWeight() +" : " +_weights.get(i-1).getWeight());
        }

        return _weights;
    }
    public void getDataForm(){
        String _uid = _auth.getCurrentUser().getUid();
        weights.clear();
        mDB.collection("myfitness").document(_uid).collection("weight").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(
                    @javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots,
                    @javax.annotation.Nullable FirebaseFirestoreException e) {
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots){
                    if(doc.get("date") != null){
                        Weight _data = new Weight();
                        String dateStr = doc.get("date").toString();
                        String statusStr = doc.get("status").toString();
                        String weightStr = doc.get("weight").toString();
                        _data.setDate(dateStr);
                        _data.setStatus(statusStr);
                        _data.setWeight(Integer.parseInt(weightStr));
                        weights.add(_data);
                    }
                }
                Collections.sort(weights, Weight.DateComparator);
                checkUpDown(weights);
                _weightList.setAdapter(_weightAdapter);
            }
        });
    }

    public void initAdd(){
        Button _addBtn = (Button) getView().findViewById(R.id.weight_addBtn);
        _addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new Weight_FormFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d("System", "[WeightFrag] go to WeightForm");
            }
        });
    }

    public void backBtn(){
        Button _backBtn = getView().findViewById(R.id.weight_backBtn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d("System", "[WeightFrag] back to menu");
            }
        });
    }

}
