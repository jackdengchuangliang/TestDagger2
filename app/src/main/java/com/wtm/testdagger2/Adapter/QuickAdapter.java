package com.wtm.testdagger2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;

/**
 * 作者：凌章 on 7/14 0014 01:03
 * 邮箱：lilingzhang@longshine.com
 */

public abstract class QuickAdapter<T> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> data;
    protected LayoutInflater mInflater;

    public void setNewData(List<T> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    public QuickAdapter(final Context context, final int layoutId, List<T> data) {
        super(context, data);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        this.data = data;
        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(RecyclerHolder holder, T t, int position) {
                QuickAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(RecyclerHolder holder, T t, int position);
}
