package com.test.listfriendsapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.test.listfriendsapplication.R;
import com.test.listfriendsapplication.activity.UserActivity;
import com.test.listfriendsapplication.contracts.UserContract;
import com.test.listfriendsapplication.model.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by User on 5/14/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private Context context;
    private List<User> listUsers;

    private UserContract.ImageInCache cacheInterface;


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

//        Picasso.get().load(listUsers.get(position).getPicture().getLarge()).into(new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//
//            }
//
//            @Override
//            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//            }
//        });

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


                    userActivity.putExtra("ava", user.getPicture().getLarge()); // FIXME: 14.06.2018 тут надо передавать путь в кеш!
                    userActivity.putExtra("first", user.getName().getFirst());
                    userActivity.putExtra("last", user.getName().getLast());
                    userActivity.putExtra("mail", user.getEmail());
                    view.getContext().startActivity(userActivity);
                }
            });
        }

        private byte[] bitmapToByteArray(Bitmap bitmap) {
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            return bStream.toByteArray();

        }


    }

    /*          Picasso.get().load(user.getPicture().getLarge()).into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                            byte[] bytes = bitmapToByteArray(bitmap);
                            String encodedImage = Base64.encodeToString(bytes, Base64.DEFAULT);
                            Log.e(TAG, "onBitmapLoaded: " + encodedImage);
                            userActivity.putExtra(key, encodedImage);
                           // bitmap.
                        }

                        @Override
                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });*/


}
