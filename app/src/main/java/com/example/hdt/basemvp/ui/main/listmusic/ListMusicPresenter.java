package com.example.hdt.basemvp.ui.main.listmusic;

import com.example.hdt.basemvp.interact.AccountInteract;
import com.example.hdt.basemvp.model.ItemSongResponse;
import com.example.hdt.basemvp.ui.base.Action;
import com.example.hdt.basemvp.ui.base.BasePresenter;

import java.util.List;

/**
 * Created by hdt
 */

public class ListMusicPresenter extends BasePresenter<IListMusic.View>
        implements IListMusic.Presenter {
    public ListMusicPresenter(IListMusic.View view) {
        super(view);
    }

    @Override
    public void getMusic(String name) {
        interact(AccountInteract.getAccountInteract().getMusic(name),
                new Action<List<ItemSongResponse>>() {
                    @Override
                    public void call(List<ItemSongResponse> itemSongResponses) {
                        mView.finishListMusic(itemSongResponses);
                    }
                }, new Action<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.onErrorGetMusic(throwable);
                    }
                });
    }

    @Override
    public void getItemSongSearch(String content) {
        interact(AccountInteract.getAccountInteract().getListMusic(content),
                re -> {
                    mView.finishListMusicS(re);
                },
                er -> {
                    mView.onErrorGetMusicS(er);
                });
    }
}
