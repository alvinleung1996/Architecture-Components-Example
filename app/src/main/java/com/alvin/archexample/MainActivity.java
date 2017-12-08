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

import com.alvin.archexample.DataSource.Item;

public class MainActivity extends AppCompatActivity {

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private List<Item> mData = new ArrayList<>();

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Item item = mData.get(position);
            holder.onBind(item);
        }

        private void setData(List<Item> data) {
            mData = data;
            notifyDataSetChanged();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mStrText;
        private final TextView mNumText;

        private ViewHolder(View view) {
            super(view);
            mStrText = view.findViewById(R.id.str_text);
            mNumText = view.findViewById(R.id.num_text);
        }

        private void onBind(Item item) {
            mStrText.setText(item.str);
            mNumText.setText(String.valueOf(item.num));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Adapter adapter = new Adapter();

        RecyclerView list = findViewById(R.id.list);
        list.setAdapter(adapter);

        MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        viewModel.getData().observe(this, adapter::setData);
    }
}
