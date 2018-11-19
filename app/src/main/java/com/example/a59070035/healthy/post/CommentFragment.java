package com.example.a59070035.healthy.post;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a59070035.healthy.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {

    ArrayList<Comment> comments = new ArrayList<>();
    ListView _ListView;
    CommentAdapter commentAdapter;
    TextView mPostId,mId,mBody,mName,mEmail;


    public CommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        backBtn();
        getPostId();
        _ListView = getView().findViewById(R.id.comment_list);
        commentAdapter = new CommentAdapter(getActivity(), R.layout.fragment_comment, comments);
    }

    public void getPostId(){
        Bundle bundle = this.getArguments();
        String id = getArguments().getString("id");
        fetchComment(id);
        Log.d("System", id);

    }
    public void fetchComment(String id){
        OkHttpClient client = new OkHttpClient();
        String url = "http://jsonplaceholder.typicode.com/posts/" + id + "/comments";

        final Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                    Log.d("System", myResponse);
                    Gson gson = new Gson();
                    Type type = new TypeToken<Collection<Comment>>(){}.getType();
                    Collection<Comment> collection = gson.fromJson(myResponse, type);
                    Comment[] commentResult = collection.toArray(new Comment[collection.size()]);

                    for (int i = 0 ; i < commentResult.length ; i++) {
                        Comment cm = new Comment(
                                commentResult[i].getPostId(),
                                commentResult[i].getName(),
                                commentResult[i].getEmail(),
                                commentResult[i].getBody(),
                                commentResult[i].getId()
                        );
                        comments.add(cm);
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _ListView.setAdapter(commentAdapter);
                    }
                });
            }
        });
    }







    public void backBtn(){
        Button btn = getView().findViewById(R.id.comment_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new PostFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
