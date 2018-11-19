package com.example.a59070035.healthy.post;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.a59070035.healthy.MenuFragment;
import com.example.a59070035.healthy.R;
import com.example.a59070035.healthy.sleep.Sleep_FormFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    ArrayList<Post> postArrayList = new ArrayList<>();
    ListView postList;
    PostAdapter postAdapter;

    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        postList = getView().findViewById(R.id.post_list);
        postAdapter = new PostAdapter(getActivity(), R.layout.fragment_post, postArrayList);
        fetchData();
        backBtn();
    }

    public void backBtn(){
        Button btn = getView().findViewById(R.id.post_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void fetchData(){
        OkHttpClient client = new OkHttpClient();
        String url = "http://jsonplaceholder.typicode.com/posts";

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
                if (response.isSuccessful()){
                    String myResponse = response.body().string();
                    Gson gson = new Gson();
                    Type type = new TypeToken<Collection<Post>>(){}.getType();
                    Collection<Post> post = gson.fromJson(myResponse, type);
                    Post[] postResult = post.toArray(new Post[post.size()]);

                    for (int i = 0 ; i < postResult.length ; i++){
                        Post data = new Post();
                        int id = postResult[i].getId();
                        String title = postResult[i].getTitle();
                        String body = postResult[i].getBody();
                        data.setId(id);
                        data.setTitle(title);
                        data.setBody(body);
                        postArrayList.add(data);
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        postList.setAdapter(postAdapter);
                        postList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Post post = postArrayList.get(position);
                                Bundle bundle = new Bundle();
                                bundle.putString("id", String.valueOf(post.getId()));

                                Fragment fragment = new CommentFragment();
                                fragment.setArguments(bundle);

                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.main_view, fragment).addToBackStack(null).commit();
                            }
                        });
                    }
                });
            }
        });
    }


}
