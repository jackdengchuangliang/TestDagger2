package com.wtm.testdagger2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.netease.hearttouch.htrefreshrecyclerview.HTLoadMoreListener;
import com.netease.hearttouch.htrefreshrecyclerview.HTRefreshListener;
import com.netease.hearttouch.htrefreshrecyclerview.HTRefreshRecyclerView;
import com.netease.hearttouch.htrefreshrecyclerview.base.HTBaseViewHolder;
import com.netease.hearttouch.htrefreshrecyclerview.viewimpl.HTDefaultVerticalRefreshViewHolder;
import com.wtm.testdagger2.Adapter.MultiItemTypeAdapter;
import com.wtm.testdagger2.Adapter.QuickAdapter;
import com.wtm.testdagger2.Adapter.RecyclerHolder;

import java.util.ArrayList;
import java.util.List;


public class DataActivity extends AppCompatActivity implements HTLoadMoreListener, HTRefreshListener {

    public static final String TAG = DataActivity.class.getSimpleName();
    private final ArrayList<String> mData = new ArrayList<>();
    private final ArrayList<String> mNewData = new ArrayList<>();
    private final ArrayList<String> userData = new ArrayList<>();
    private HTRefreshRecyclerView mRefreshLayout;
    private QuickAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        initData();
        initView();
        //test

    }

    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.startAutoRefresh();
    }

    public void initView() {
        mAdapter = adapter(userData);
        mRefreshLayout = (HTRefreshRecyclerView) findViewById(R.id.vertical_refresh);
        HTBaseViewHolder viewHolder = new HTDefaultVerticalRefreshViewHolder(this);
        viewHolder.setRefreshViewBackgroundResId(R.color.foreground_material_dark);
        mRefreshLayout.setRefreshViewHolder(viewHolder);//不设置样式,则使用默认箭头样式
        mRefreshLayout.setLayoutManager(new LinearLayoutManager(this));//设置列表布局方式
        mRefreshLayout.setEnableScrollOnRefresh(false);
//        mRefreshLayout.addItemDecoration(new DividerItemDecoration(DividerItemDecoration.VERTICAL));
        mRefreshLayout.setAdapter(mAdapter);//设置数据源
        mRefreshLayout.setOnLoadMoreListener(this);//实现OnLoadMoreListener接口
        mRefreshLayout.setOnRefreshListener(this);//实现OnRefreshListener接口
        mRefreshLayout.setLoadMoreViewShow(false);
        mRefreshLayout.setEnableScrollOnRefresh(true);
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerHolder holder, Object o, int position) {
                showMsg("点你妹"+position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerHolder holder, Object o, int position) {
                showMsg("长按你妹 "+position);
                return false;
            }
        });
    }
    private void initData() {

        for (int i = 0; i < 20; i++) {
            mData.add("条目 "+i);
        }

        for (int i = 0; i < 20; i++) {
            mNewData.add("新条目 "+i);
        }
        userData.addAll(mData);

    }
    private void showMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        if (mAdapter.getData().size() >= mData.size()+mNewData.size()) {
            mRefreshLayout.setRefreshCompleted(false);//设置为false表示加载完毕
        } else {
            new Handler(getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.addAll(mNewData);
                    mRefreshLayout.setRefreshCompleted(true);
                    Toast.makeText(DataActivity.this, "loadMore Completed", Toast.LENGTH_SHORT).show();

                }
            }, 500);
        }
    }

    @Override
    public void onRefresh() {
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                mAdapter.replaceAll(mData);
                mRefreshLayout.setRefreshCompleted(true);
            }

        }, 500);
    }

    private QuickAdapter<String> adapter(List<String> data) {
        return new QuickAdapter<String>(DataActivity.this,R.layout.vertical_item,data) {
            @Override
            protected void convert(RecyclerHolder holder, String s, int position) {
                holder.setText(R.id.text,s);
            }
        };
    }
}
