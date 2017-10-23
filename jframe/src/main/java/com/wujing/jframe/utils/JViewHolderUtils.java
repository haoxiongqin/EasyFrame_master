package com.wujing.jframe.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * ViewHolder (ListView适用)简化创建工具类
 */

public class JViewHolderUtils {

    private JViewHolderUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View convertView, int id) {

        SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            convertView.setTag(viewHolder);
        }

        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = convertView.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
