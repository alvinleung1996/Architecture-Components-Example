package com.alvin.archexample;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class DataSource {

    @SuppressLint("StaticFieldLeak")
    public static LiveData<List<String>> queryAllData() {
        final MutableLiveData<List<String>> data = new MutableLiveData<>();

        new AsyncTask<Void, Void, List<String>>() {

            @Override
            protected List<String> doInBackground(Void... voids) {
                final int size = 999999;
                List<String> list = new ArrayList<>(size);
                for (int i = 0; i < size; ++i) {
                    list.add(String.valueOf(i));
                }
                return list;
            }

            @Override
            protected void onPostExecute(List<String> list) {
                data.setValue(list);
            }

        }.execute();

        return data;
    }
}
