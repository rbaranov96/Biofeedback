package com.example.robertbaranov.biofeedback;

import java.util.ArrayList;
import java.util.List;
public class ItemCollection {

    private ItemCollection() {
        mItems = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            Item item = new Item();
            item.setTitle("Panic Event #" + i);
            item.setDate("10/10/" + (i+1));
            switch (i % 4) {
                case 0:
                    item.setImageResource(R.drawable.hr);
                    break;
                case 1:
                    item.setImageResource(R.drawable.hr);
                    break;
                case 2:
                    item.setImageResource(R.drawable.hr);
                    break;
                case 3:
                    item.setImageResource(R.drawable.hr);
                    break;
            }
            mItems.add(item);
        }
    }

    private static ItemCollection sItemCollection;
    private List<Item> mItems;
    public static ItemCollection get() {
        if (sItemCollection == null) {
            sItemCollection = new ItemCollection();
        }
        return sItemCollection;
    }
    public List<Item> getItems() {
        return mItems;
    }
}
