package com.example.hdt.basemvp.interact;

import com.example.hdt.basemvp.model.ItemSongSearch;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hdt
 */

public interface ApiService {
    // base link: bỏ qua
    @GET("jOut.ashx")
    // đây là sub link
    Observable<List<ItemSongSearch>> queryMusic(@Query("k") String musicName,
                                                @Query("h") String webSite,
                                                @Query("code") String code);
//    Thuộc tính cần trả về cần phải "ở trong" Observable
}
