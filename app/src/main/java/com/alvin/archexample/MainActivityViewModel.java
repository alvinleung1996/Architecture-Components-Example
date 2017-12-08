package com.alvin.archexample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import com.alvin.archexample.DataSource.Item;

public class MainActivityViewModel extends ViewModel {

    private LiveData<List<Item>> mData;

    public LiveData<List<Item>> getData() {
        if (mData == null) {
            mData = DataSource.queryData();
        }
        return mData;
    }

}
