package com.example.easywallet.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easywallet.R;
import com.example.easywallet.model.Item;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by balie on 10-Dec-17.
 */

public class ItemListAdapter extends ArrayAdapter<Item> {

    static Context mContext;
    private int mLayoutResId;
    private ArrayList<Item> mItemListAdapter;

    public ItemListAdapter(@NonNull Context context, int layoutResId, @NonNull ArrayList<Item> ItemListAdapter) {
        super(context, layoutResId, ItemListAdapter);

        this.mContext = context;
        this.mLayoutResId = layoutResId;
        this.mItemListAdapter = ItemListAdapter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemLayout = inflater.inflate(mLayoutResId, null);

        Item item = mItemListAdapter.get(position);

        ImageView Imageview = (ImageView) itemLayout.findViewById(R.id.imageView);
        TextView TitleTextView = (TextView) itemLayout.findViewById(R.id.textView);


        TitleTextView.setText(item.title);
        String pictureFileName = item.picture;

        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(pictureFileName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            Imageview.setImageDrawable(drawable);

        } catch (IOException e) {
            e.printStackTrace();

            File pictureFile = new File(mContext.getFilesDir(), pictureFileName);
            Drawable drawable = Drawable.createFromPath(pictureFile.getAbsolutePath());
            Imageview.setImageDrawable(drawable);
        }

        return itemLayout;
    }
}
