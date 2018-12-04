package com.example.robertbaranov.biofeedback;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    protected static final String TAG = "HistoryRecyclerView";
    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUI();
    }
    private void updateUI() {
        ItemCollection itemCollection = ItemCollection.get();
        List<Item> items = itemCollection.getItems();
        mAdapter = new ItemAdapter(items);
        mRecyclerView.setAdapter(mAdapter);
    }


    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Item mItem;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mImageView;
        public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.item_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.item_date);
            mImageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
        public void bind(Item item) {
            mItem = item;
            Log.i(TAG, "ItemHolder.bind(" + item.getTitle() + ")");
            mTitleTextView.setText(mItem.getTitle());
            mDateTextView.setText(mItem.getDate());
            int resourceID = item.getImageResource();
            Drawable drawable = getResources().getDrawable(resourceID);
            mImageView.setImageDrawable(drawable);
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ViewHistoryActivity.class);
            startActivity(intent);
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        private List<Item> mItems;
        public ItemAdapter(List<Item> items) {
            mItems = items;
        }
        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            return new ItemHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Item item = mItems.get(position);
            holder.bind(item);
        }
        @Override
        public int getItemCount() {
            Log.i(TAG, "ItemAdapter.getItemCount()");
            return mItems.size();
        }
    }


}
