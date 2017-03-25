package com.wtm.testdagger2.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：凌章 on 7/14 0014 01:08
 * 邮箱：lilingzhang@longshine.com
 */

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {
    protected Context mContext;
    protected List<T> data;
    protected ItemViewDelegateManager mItemViewDelegateManager;
    protected OnItemClickListener<T> mOnItemClickListener;

    public MultiItemTypeAdapter(Context context, List<T> data) {
        mContext = context;
        this.data = data == null ? new ArrayList<T>() : new ArrayList<>(data);
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(data.get(position), position);
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mItemViewDelegateManager.getItemViewLayoutId(viewType);
        RecyclerHolder holder = RecyclerHolder.createViewHolder(mContext, parent, layoutId);
        setListener(parent, holder, viewType);
        return holder;
    }

    public void convert(RecyclerHolder holder, T t) {
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    protected void setListener(final ViewGroup parent, final RecyclerHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, viewHolder, data.get(position), position);
                }
            }
        });
        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return mOnItemClickListener.onItemLongClick(v, viewHolder, data.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        convert(holder, data.get(position));
    }

    @Override
    public int getItemCount() {
        int itemCount = data.size();
        return itemCount;
    }


    public List<T> getDatas() {
        return data;
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public MultiItemTypeAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public void notifyDataChanged(List<T> ts, boolean isRefresh) {
        if (isRefresh) {
            ts.clear();
        }
        ts.addAll(data);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return data;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, RecyclerHolder holder, T o, int position);

        boolean onItemLongClick(View view, RecyclerHolder holder, T o, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void replaceAll(List<T> elem) {
        data.clear();
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public void add(T t) {
        data.add(t);
        notifyDataSetChanged();
    }

    public void addAll(List<T> elem) {
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

}
