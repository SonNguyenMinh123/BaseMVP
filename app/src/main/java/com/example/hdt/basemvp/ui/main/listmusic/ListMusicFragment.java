package com.example.hdt.basemvp.ui.main.listmusic;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.hdt.basemvp.R;
import com.example.hdt.basemvp.model.ItemSongResponse;
import com.example.hdt.basemvp.model.ItemSongSearch;
import com.example.hdt.basemvp.ui.base.fragment.BaseMVPFragment;

import java.util.List;

/**
 * Created by hdt
 */

public class ListMusicFragment extends BaseMVPFragment<IListMusic.Presenter>
        implements IListMusic.View, TextWatcher {

    private static final String TAG = ListMusicFragment.class.getSimpleName();
    private RecyclerView rcMusic;
    private EditText editText;

    @Override
    public int getLayout() {
        return R.layout.fragment_list_music;
    }

    @Override
    public void findViewByIds() {
        //noinspection ConstantConditions
        rcMusic = (RecyclerView) getView().findViewById(R.id.rc_song);
        editText = (EditText) getView().findViewById(R.id.edt_name);
    }

    @Override
    public void initComponents() {
        mPresenter = new ListMusicPresenter(this);
        mPresenter.getMusic("xa em");
    }

    @Override
    public void setEvents() {
        editText.addTextChangedListener(this);
    }

    @Override
    public void finishListMusic(List<ItemSongResponse> itemSongResponses) {
//        Thành công thì vào phương thức này
        Log.i(TAG, "finishListMusic: ");
    }

    @Override
    public void finishListMusicS(List<ItemSongSearch> itemSongSearches) {
        Log.i(TAG, "finishListMusic SSSSSSSSSSS: ");
    }

    @Override
    public void onErrorGetMusic(Throwable throwable) {
//        Lỗi thì vào phương thức này
        Log.i(TAG, "onErrorGetMusic: ");
    }

    @Override
    public void onErrorGetMusicS(Throwable throwable) {
        Log.i(TAG, "onErrorGetMusic ssssssssssss: ");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String content = s.toString().trim();
        mPresenter.getItemSongSearch(content);
    }
}
