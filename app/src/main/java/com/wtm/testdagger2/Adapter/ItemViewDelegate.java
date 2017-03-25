
package com.wtm.testdagger2.Adapter;

/**
 * 作者：凌章 on 7/14 0014 01:10
 * 邮箱：lilingzhang@longshine.com
 */

public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(RecyclerHolder holder, T t, int position);


}