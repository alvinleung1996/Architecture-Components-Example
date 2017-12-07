package com.alvin.archexample;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private class DataListAdapter extends RecyclerView.Adapter<DataItemViewHolder> {

        private List<String> mData = new ArrayList<>();

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public DataItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
            return new DataItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DataItemViewHolder holder, int position) {
            String entry = mData.get(position);
            holder.onBindData(entry);
        }

        private void setData(List<String> data) {
            mData = data;
            notifyDataSetChanged();
        }
    }

    private class DataItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView mDataText;

        private DataItemViewHolder(View view) {
            super(view);
            mDataText = view.findViewById(R.id.data_text);
        }

        private void onBindData(String entry) {
            mDataText.setText(entry);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        DataListAdapter adapter = new DataListAdapter();

        RecyclerView dataList = findViewById(R.id.data_list);
        dataList.setAdapter(adapter);

        MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        viewModel.getData().observe(this, adapter::setData);
    }
}
