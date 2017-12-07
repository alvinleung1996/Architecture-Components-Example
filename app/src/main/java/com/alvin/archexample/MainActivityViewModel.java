package com.alvin.archexample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private LiveData<List<String>> mData;

    public LiveData<List<String>> getData() {
        if (mData == null) {
            mData = DataSource.queryAllData();
        }
        return mData;
    }

}
