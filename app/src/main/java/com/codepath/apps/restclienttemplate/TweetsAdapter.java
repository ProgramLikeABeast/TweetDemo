package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    Context context;
    List<Tweet> tweets;

    //for each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    //bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the data's position
        Tweet tweet=tweets.get(position);
        //bind the date with view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    //clean all the elements in the recycler
    public void clear(){
        tweets.clear();
        notifyDataSetChanged();
    }

    //add a list of items
    public void addAll(List<Tweet> tweetList){
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }


    //passing context and a list of tweets and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    //define a view holder
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfileImage;
        TextView tvScreenName;
        TextView tvBody;

        public ViewHolder(@NotNull View itemView){
            super(itemView);
            ivProfileImage=itemView.findViewById(R.id.ivProfileImage);
            tvScreenName=itemView.findViewById(R.id.tvScreenName);
            tvBody=itemView.findViewById(R.id.tvBody);
        }

        public void bind(Tweet tweet) {
            tvScreenName.setText(tweet.getUser().getScreenName());
            tvBody.setText(tweet.getBody());
            Glide.with(context).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);
        }
    }
}
