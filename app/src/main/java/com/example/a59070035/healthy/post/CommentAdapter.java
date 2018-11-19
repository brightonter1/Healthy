package com.example.a59070035.healthy.post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a59070035.healthy.R;

import java.util.ArrayList;

public class CommentAdapter extends ArrayAdapter<Comment> {
    ArrayList<Comment> commentArrayList = new ArrayList<>();
    Context context;

    public CommentAdapter(Context context, int resouce, ArrayList<Comment> object){
        super(context, resouce, object);
        this.context = context;
        this.commentArrayList = object;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_sub_comment, parent, false);
        TextView mPostId,mId,mBody,mName,mEmail;
        mPostId = v.findViewById(R.id.sub_comment_postid);
        mId = v.findViewById(R.id.sub_comment_id);
        mBody = v.findViewById(R.id.sub_comment_body);
        mName = v.findViewById(R.id.sub_comment_name);
        mEmail = v.findViewById(R.id.sub_comment_email);

        Comment comment = commentArrayList.get(position);
        mPostId.setText(comment.getPostId() + " :");
        mId.setText(comment.getId());
        mBody.setText(comment.getBody());
        mEmail.setText("("+ comment.getEmail()+")");
        mName.setText(comment.getName());
        return v;
    }
}
