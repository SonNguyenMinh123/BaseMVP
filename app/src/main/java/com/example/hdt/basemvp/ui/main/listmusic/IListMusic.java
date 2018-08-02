package com.example.hdt.basemvp.ui.main.listmusic;

import com.example.hdt.basemvp.model.ItemSongResponse;
import com.example.hdt.basemvp.model.ItemSongSearch;
import com.example.hdt.basemvp.ui.base.IBasePresenter;
import com.example.hdt.basemvp.ui.base.IViewMain;

import java.util.List;

/**
 * Created by hdt
 */

public interface IListMusic {
    interface View extends IViewMain {
        void finishListMusic(List<ItemSongResponse> itemSongResponses);

        void finishListMusicS(List<ItemSongSearch> itemSongSearches);

        void onErrorGetMusic(Throwable throwable);

        void onErrorGetMusicS(Throwable throwable);
    }

    interface Presenter extends IBasePresenter {
        void getMusic(String s);

        void getItemSongSearch(String content);
    }
}
