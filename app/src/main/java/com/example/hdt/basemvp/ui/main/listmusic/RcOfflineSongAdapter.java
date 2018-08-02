package com.example.hdt.basemvp.ui.main.listmusic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hdt.basemvp.R;
import com.example.hdt.basemvp.model.ItemSongSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hdt
 */

public class RcOfflineSongAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private LayoutInflater mInflater;
    private List<ItemSongSearch> mOfflineSongs;
    private ISongAdapter mISongAdapter;

    public RcOfflineSongAdapter(Context context, ISongAdapter iSongAdapter) {
        this.mInflater = LayoutInflater.from(context);
        this.mOfflineSongs = new ArrayList<>();
        this.mISongAdapter = iSongAdapter;
    }

    //    Begin Override RecyclerView.Adapter
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_song, parent, false);
        return new ViewHolderOfflineSong(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (null == mOfflineSongs) {
            return;
        }
        ItemSongSearch itemDataSong = mOfflineSongs.get(position);
        ViewHolderOfflineSong viewHolderOfflineSong = (ViewHolderOfflineSong) holder;
        viewHolderOfflineSong.txtTitle.setText(itemDataSong.getTitle());
        viewHolderOfflineSong.txtArtist.setText(itemDataSong.getArtist());
    }

    @Override
    public int getItemCount() {
        if (null == mOfflineSongs) {
            return 0;
        }
        return mOfflineSongs.size();
    }
    //    End Override RecyclerView.Adapter

    private class ViewHolderOfflineSong extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtArtist;

        private ViewHolderOfflineSong(View itemView, View.OnClickListener onClick) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            txtArtist = (TextView) itemView.findViewById(R.id.txt_artist);

//            tạo ra đối tượng getPosition
            IGetPosition getPosition = new IGetPosition() {
                @Override
                public int getPosition() {
                    return getAdapterPosition();
//                    trả về vị trí của ItemData
                }
            };
            itemView.setTag(getPosition);// đưa đối tượng getPosition vào túi của itemView
            itemView.setOnClickListener(onClick);
        }
    }

    public void setmOfflineSongs(List<ItemSongSearch> offlineSongs) {
        mOfflineSongs = offlineSongs;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        IGetPosition getPosition = (IGetPosition) v.getTag();// lấy đối tượng getPosition trong túi của v
        mISongAdapter.onClickItem(getPosition.getPosition());// getPosition.getPosition() trả về position của View v
    }

    private interface IGetPosition {
        int getPosition();
    }

    public interface ISongAdapter {
        void onClickItem(int position);
    }
}