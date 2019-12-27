package com.sandalisw.mobileapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sandalisw.mobileapp.R;
import com.sandalisw.mobileapp.models.SongR;

import java.util.ArrayList;
import java.util.List;

public class RecentlyPlayedSongAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "RecentlyPlayedAdapter";

    private Context mContext;
    private List<SongR> songList = new ArrayList<>();
    private RecentSongListener mRecentSongLstener;
    private int mCategory;
    private int mIndex = -1;

    public RecentlyPlayedSongAdapter(Context context, RecentSongListener recentSongListener, int category){
        mContext = context;
        mRecentSongLstener = recentSongListener;
        mCategory = category;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recently_played_song, viewGroup, false);
        return new RecentlyPlayedSongViewHolder(view,mRecentSongLstener);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i){

        Glide.with(mContext)
                .load(songList.get(i).getThumbnailurl())
                .error(R.drawable.ic_launcher_background)
                .into(((RecentlyPlayedSongViewHolder)viewHolder).thumbnail);
        ((RecentlyPlayedSongViewHolder)viewHolder).song_title.setText(songList.get(i).getSongname());
        ((RecentlyPlayedSongViewHolder)viewHolder).artist_title.setText(songList.get(i).getArtistname());

        if(mIndex == i)
            ((RecentlyPlayedSongViewHolder)viewHolder).song_title.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
        else
            ((RecentlyPlayedSongViewHolder)viewHolder).song_title.setTextColor(ContextCompat.getColor(mContext,R.color.fontAshDarker));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIndex = viewHolder.getAdapterPosition();
                mRecentSongLstener.onSongClick(mIndex,mCategory);
                notifyDataSetChanged();
            }
        });

    }

    public void setDataList(List<SongR> mData){
        Log.d(TAG, "onChanged: CALLED"+mData.size());
        songList = mData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if(songList != null){
            return songList.size();
        }
        return 0;
    }

    private class RecentlyPlayedSongViewHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        TextView song_title;
        TextView artist_title;

        public RecentlyPlayedSongViewHolder(@NonNull View itemView, RecentSongListener recentsonglistener){
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            song_title = itemView.findViewById(R.id.recent_song_name);
            artist_title = itemView.findViewById(R.id.recent_artist_name);
        }
    }

    public interface RecentSongListener{

        void onSongClick(int position, int category);
    }


}
