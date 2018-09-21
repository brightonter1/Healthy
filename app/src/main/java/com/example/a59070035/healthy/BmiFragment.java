package com.example.a59070035.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Brightonter on 9/8/2018.
 */

public class BmiFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBack();
        initCal();
    }

    public void initBack(){
        Button _backBtn = (Button) getView().findViewById(R.id.bmi_backBtn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d("System", "[BMI] Back to menu");
            }
        });
    }
    public void initCal(){
        Button _calBtn = (Button) getView().findViewById(R.id.bmi_calBtn);
        _calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _weight = (EditText) getView().findViewById(R.id.bmi_weight);
                EditText _height = (EditText) getView().findViewById(R.id.bmi_height);


                if (_weight.getText().toString().isEmpty() || _height.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Please fill all info",Toast.LENGTH_SHORT).show();
                }else {
                    float _weightFt = Float.valueOf(_weight.getText().toString());
                    float _heightFt = Float.valueOf(_height.getText().toString());
                    float _bmiResult = _weightFt / ( (_heightFt/100)*(_heightFt/100) );
                    TextView _result = (TextView) getView().findViewById(R.id.bmi_result);
                    _result.setText("Your BMI " + _bmiResult);
                    Log.d("System", "[BMI] Show result BMI");
                }
            }
        });
    }
}
