package com.example.a59070035.healthy.post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a59070035.healthy.R;


import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {

    ArrayList<Post> posts = new ArrayList<>();
    Context context;
    public PostAdapter(Context context, int resource, ArrayList<Post> objects) {
        super(context, resource, objects);
        this.context = context;
        this.posts = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_sub_post, parent, false);
        TextView vId, vTitle, vBody;
        vId = v.findViewById(R.id.sub_post_id);
        vTitle = v.findViewById(R.id.sub_post_title);
        vBody = v.findViewById(R.id.sub_post_body);

        Post _row = posts.get(position);
        vId.setText(String.valueOf(_row.getId()) + " :");
        vTitle.setText(_row.getTitle());
        vBody.setText(_row.getBody());


        return v;

    }
}
