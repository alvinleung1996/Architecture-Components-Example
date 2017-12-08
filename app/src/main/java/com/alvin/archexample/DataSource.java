package com.alvin.archexample;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class DataSource {

    public static class Item {

        public final String str;
        public final int num;

        private Item(String str, int num) {
            this.str = str;
            this.num = num;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public static LiveData<List<Item>> queryData() {
        MutableLiveData<List<Item>> data = new MutableLiveData<>();

        new AsyncTask<Void, Void, List<Item>>() {

            @Override
            protected List<Item> doInBackground(Void... voids) {
                final int max = 9999;
                List<Item> list = new ArrayList<>(max);
                for (int num = 1; num <= max; ++num) {
                    StringBuilder numStr = new StringBuilder();
                    for (int n = num, digit; n > 0; n = (n - digit) / 26) {
                        digit = (n - 1) % 26;
                        numStr.insert(0, (char) (digit + 'A'));
                    }
                    list.add(new Item(numStr.toString(), num));
                }
                return list;
            }

            @Override
            protected void onPostExecute(List<Item> list) {
                data.setValue(list);
            }

        }.execute();

        return data;
    }
}
