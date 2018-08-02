package com.example.hdt.basemvp.ui.base;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hdt
 */

public class BasePresenter<V extends IViewMain> implements IBasePresenter {
    protected V mView;
    private List<Disposable> disposables;

    public BasePresenter(V view) {
        mView = view;
        disposables = new ArrayList<>();
    }

    public <E> void interact(Observable<E> ob, Action<E> onNext, Action<Throwable> onError) {

        ob = ob.subscribeOn(/*Thread thực hiện a*/Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()/*2*/);
        checkDis();
        Disposable disposable = ob.subscribe(response -> {
            onNext.call(response);
        }, error -> {
            onError.call(error);
        });
        disposables.add(disposable);
    }

    private void checkDis() {
        for (int i = disposables.size() - 1; i >= 0; i--) {
            if (disposables.get(i).isDisposed()) {
                disposables.remove(i);
            }
        }
    }

    @Override
    public void onDestroy() {
        for (Disposable disposable : disposables) {
            disposable.dispose();
        }
    }
}
