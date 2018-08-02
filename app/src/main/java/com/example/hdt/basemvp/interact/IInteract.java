package com.example.hdt.basemvp.interact;

import com.example.hdt.basemvp.model.ItemSongResponse;
import com.example.hdt.basemvp.model.ItemSongSearch;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by hdt
 */

public interface IInteract {
    Observable<List<ItemSongResponse>> getMusic(String name);

    Observable<List<ItemSongSearch>> getListMusic(String nameMusic);
}
