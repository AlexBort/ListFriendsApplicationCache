package com.test.listfriendsapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.listfriendsapplication.R;
import com.test.listfriendsapplication.activity.UserActivity;
import com.test.listfriendsapplication.model.User;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by User on 5/14/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<User> listUsers;

    public RecyclerAdapter(Context context, List<User> listUsers) {
        this.listUsers = listUsers;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {


        holder.firstName.setText(listUsers.get(position).getName().getFirst());
        Picasso.get().load(listUsers.get(position).getPicture().getMedium()).into(holder.smallAva);
        holder.click(listUsers.get(position));
    }


    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView firstName;
        private ImageView smallAva;
        private View view;


        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
            smallAva = itemView.findViewById(R.id.small_ava);
            firstName = itemView.findViewById(R.id.first_name);

        }

        public void click(final User user) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent userActivity = new Intent(view.getContext(), UserActivity.class);
                    userActivity.putExtra("ava", user.getPicture().getLarge());
                    userActivity.putExtra("first", user.getName().getFirst());
                    userActivity.putExtra("last", user.getName().getLast());
                    userActivity.putExtra("mail", user.getEmail());
                    view.getContext().startActivity(userActivity);
                }
            });
        }


    }


}
