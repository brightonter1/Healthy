package com.example.a59070035.healthy;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.ContentValues.TAG;

/**
 * Created by Brightonter on 9/8/2018.
 */

public class RegisterFragment extends Fragment {
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        initRegis();
    }

    public void initRegis() {
        Button _regisBtn = (Button) getView().findViewById(R.id.register_regisBtn);
        _regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    public void registerNewUser() {
        EditText _email = (EditText) getView().findViewById(R.id.register_email);
        EditText _password = (EditText) getView().findViewById(R.id.register_password);
        EditText _repassword = (EditText) getView().findViewById(R.id.register_rePassword);

        String _emailStr = _email.getText().toString();
        String _passwordStr = _password.getText().toString();
        String _repasswordStr = _repassword.getText().toString();
        if (_passwordStr.equals(_repasswordStr) == false){
            Toast.makeText(getActivity(), "password not equal re-password", Toast.LENGTH_SHORT).show();
        }else if(_emailStr.isEmpty() || _passwordStr.isEmpty() || _repasswordStr.isEmpty()){
            Toast.makeText(getActivity(), "please fill all information", Toast.LENGTH_SHORT).show();
        }else if (_passwordStr.length() >= 6 && (_passwordStr.equals(_repasswordStr))) {
            mAuth.createUserWithEmailAndPassword(_emailStr, _passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    sendVerifiedEmail(authResult.getUser());
                    Toast.makeText(getActivity(), "Register Complete !!", Toast.LENGTH_SHORT).show();
                    Log.d("System","[Register] Register Complete");
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new LoginFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("System", "[Register] Register failed");
                    Toast.makeText(getActivity(), "ERROR : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            Log.d("System", "[Register] go to menu");
        }else if (_passwordStr.length() < 6 || _repasswordStr.length() < 6){
            Toast.makeText(getActivity(), "password must have atleast 6 character", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "please check your information and try again", Toast.LENGTH_SHORT).show();
        }
    }
    public void sendVerifiedEmail(FirebaseUser _user) {
        _user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("System", "[Register] Send verifiedEmail ");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "ERROR : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
