package com.example.hdt.basemvp.interact;

import com.example.hdt.basemvp.model.ItemSongResponse;
import com.example.hdt.basemvp.model.ItemSongSearch;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hdt
 */

class ApiConnector {
    private static ApiConnector apiConnector = new ApiConnector();
    private ApiService apiService;

    private ApiConnector() {
//  Tạo ApiService bằng OkHttp
        OkHttpClient.Builder builderOk = new OkHttpClient.Builder();
        builderOk.connectTimeout(30, TimeUnit.SECONDS)// Timeout khi kết nối đến Sever
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        Retrofit.Builder builderRe = new Retrofit.Builder();
        builderRe.baseUrl("http://j.ginggong.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builderOk.build());

        apiService = builderRe.build().create(ApiService.class);
    }

    static ApiConnector getApiConnector() {
        return apiConnector;
    }

    public io.reactivex.Observable<List<ItemSongResponse>> getMusic(String name) {
        String newName = name.replace(" ", "+");
//        Tương tác Server
//        Sử dụng RxJava
        return io.reactivex.Observable.create(new ObservableOnSubscribe<List<ItemSongResponse>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ItemSongResponse>> e) throws Exception {
                /*a*/
                //noinspection MismatchedQueryAndUpdateOfCollection
                List<ItemSongResponse> responses = new ArrayList<>();
                Document document = Jsoup.connect("http://mp3.zing.vn/tim-kiem/bai-hat.html?q=" + newName).get();

                Element element = document.select("div.wrap-content").first();
                Elements elements = element.select("div.item-song");

                for (Element elSong : elements) {
                    String dataCode = elSong.attr("data-code").toString();
                    String title = elSong.select("h3").select("a").first().attr("title").toString();
                    responses.add(new ItemSongResponse(dataCode, title));
                }
                /*a*/
                e.onNext(responses); // Trả về kết quả cho Thread tiếp theo, Thread /*2*/
            }
        });
    }

    public Observable<List<ItemSongSearch>> getListMusic(String nameMusic, String webSite, String code) {
        return apiService.queryMusic(nameMusic, webSite, code);
    }
}
