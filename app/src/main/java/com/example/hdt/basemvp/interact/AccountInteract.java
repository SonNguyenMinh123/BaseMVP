package com.example.hdt.basemvp.interact;

import com.example.hdt.basemvp.common.Constants;
import com.example.hdt.basemvp.model.ItemSongResponse;
import com.example.hdt.basemvp.model.ItemSongSearch;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by hdt
 */

public class AccountInteract implements IInteract {
    private static IInteract accountInteract = new AccountInteract();

    private AccountInteract() {

    }

    public static IInteract getAccountInteract() {
        return accountInteract;
    }

    @Override
    public Observable<List<ItemSongResponse>> getMusic(String name) {
        return ApiConnector.getApiConnector().getMusic(name);
////        Nên sử dụng Java 8
//        ApiConnector.getApiConnector().getMusic(name).subscribe(
//                listMusic -> {
////                    Update giao diện
//                }, error -> {
//
//                });
////        ApiConnector.getApiConnector().getMusic(name).subscribe(
////                new Consumer<List<ItemSongResponse>>() {
////                    @Override
////                    public void accept(List<ItemSongResponse> itemSongResponses) throws Exception {
////
////                    }
////                }, new Consumer<Throwable>() {
////                    @Override
////                    public void accept(Throwable throwable) throws Exception {
////
////                    }
////                });
    }

    @Override
    public Observable<List<ItemSongSearch>> getListMusic(String nameMusic) {
        return ApiConnector.getApiConnector().getListMusic(nameMusic, Constants.WEB_SITE_NAME, Constants.CODE);
    }
}
