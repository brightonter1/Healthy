package com.example.a59070035.healthy;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;

/**
 * Created by Brightonter on 9/8/2018.
 */

public class LoginFragment extends Fragment {
    FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        Log.d("System","[Login]"+ mAuth.toString());
        initLoginBtn();
        initRegister();
    }
    public void initRegister(){
        TextView _regisBtn = (TextView) getView().findViewById(R.id.login_register);
        _regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d("System", "[Login] Go to register");
            }
        });
    }
    public void initLoginBtn(){
        final Button _loginBtn = (Button) getView().findViewById(R.id.login_loginBtn);
        _loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText _username = (EditText) getView().findViewById(R.id.login_user);
                EditText _password = (EditText) getView().findViewById(R.id.login_password);
                String _usernameStr = _username.getText().toString();
                String _passwordStr = _password.getText().toString();

                if (_usernameStr.isEmpty() || _passwordStr.isEmpty()){
                    Log.d("System", "User or password is empty");
                    Toast.makeText(getActivity(), "Please fill your info ", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.signInWithEmailAndPassword(_usernameStr,_passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if (authResult.getUser().isEmailVerified()){
                                Log.d("System","[Login] Email verified already");
                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_view, new MenuFragment())
                                        .commit();
                            }
                            if(authResult.getUser().isEmailVerified() == false){
                                Log.d("System", "[Login] Email not verified");
                                Toast.makeText(getActivity() , "Please Confirm Your Email" , Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("System"  ,"[Login] Invalid = " + e.getMessage());
                            Toast.makeText(getActivity() , "Invalid user or password" , Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }


}
