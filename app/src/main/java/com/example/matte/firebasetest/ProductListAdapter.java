package com.example.matte.firebasetest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by matte on 12/04/2018.
 */

public class ProductListAdapter extends BaseAdapter{

    private Context mContext;
    private List<Product> mProductList;

    public ProductListAdapter(Context mContext, List<Product> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int i) {
        return mProductList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.item_pippo, null);
        TextView tvName = v.findViewById(R.id.tv_name);
        TextView tvPrice = v.findViewById(R.id.tvPrice);
        TextView tvDescription = v.findViewById(R.id.tv_description);

        tvName.setText(mProductList.get(i).getName());
        tvPrice.setText(String.valueOf(mProductList.get(i).getPrice()));
        tvDescription.setText(mProductList.get(i).getDescription());

        v.setTag(mProductList.get(i).getId());
        return v;
    }
}
