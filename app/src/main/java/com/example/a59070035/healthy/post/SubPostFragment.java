package com.example.a59070035.healthy.post;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a59070035.healthy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubPostFragment extends Fragment {


    public SubPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sub_post, container, false);
    }

}
