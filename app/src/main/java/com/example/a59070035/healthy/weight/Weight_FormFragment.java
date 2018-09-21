package com.example.a59070035.healthy.weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a59070035.healthy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;



/**
 * Created by Brightonter on 9/8/2018.
 */

public class Weight_FormFragment extends Fragment{

    FirebaseFirestore _fireStore;
    FirebaseAuth _auth;



    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_item, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _fireStore = FirebaseFirestore.getInstance();
        _auth = FirebaseAuth.getInstance();
        initBackBtn();
        initSaveBtn();

    }


    public void initBackBtn(){
        Button _addBtn = (Button) getView().findViewById(R.id.weight_form_backBtn);
        _addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new WeightFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d("System", "[WeightForm] Back to menu");
            }
        });
    }
    public void initSaveBtn(){
        Button _btn = getView().findViewById(R.id.weight_form_saveBtn);
        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _date = getView().findViewById(R.id.weight_form_date);
                EditText _weight = getView().findViewById(R.id.weight_form_weight);

                String _dateString = _date.getText().toString();
                String _weightString = _weight.getText().toString();

                if (_dateString.isEmpty() || _weightString.isEmpty()){
                    Toast.makeText(getActivity(),"Please fill all info", Toast.LENGTH_SHORT).show();
                }else{
                    String _uid = _auth.getCurrentUser().getUid();
                    Weight _data = new Weight(_dateString,Integer.valueOf(_weightString), "");
                    _fireStore.collection("myfitness")
                            .document(_uid).collection("weight")
                            .document(_dateString)
                            .set(_data)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("System", "[WeightForm] Add Data to Firebase");
                                    Toast.makeText(getActivity(), "Save complete !!",Toast.LENGTH_SHORT).show();
                                    getActivity().getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.main_view, new WeightFragment())
                                            .addToBackStack(null)
                                            .commit();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }

}
