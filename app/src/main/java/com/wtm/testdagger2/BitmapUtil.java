package com.wtm.testdagger2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Base64;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.OutputStream;


/**
 * @author LiLingZhang
 * @version 1.0
 * @time 2016/4/5
 * @updateAuthor $Author$
 * @updateDate $Date$
 */
public class BitmapUtil {
    /**
     * 设置TextView图片
     */
    public static void setLeftDrawable(Context context, int id, TextView v, String text, Object color) {
        if (id != -1) {
            Drawable drawable = getDrawable(context, id);
            assert drawable != null;
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            v.setCompoundDrawables(drawable, null, null, null);
        } else {
            v.setCompoundDrawables(null, null, null, null);
        }
        if (text != null)
            v.setText(text);
        if (color != null)
            setTextColor(context, v, (int) color);
    }

    public static void setTopDrawable(Context context, int id, TextView v, String str, Object color) {
        Drawable drawable = getDrawable(context, id);
        assert drawable != null;
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        v.setCompoundDrawables(null, drawable, null, null);
        if (str != null)
            v.setText(str);
        if (color != null)
            setTextColor(context, v, (int) color);
    }

    public static void setRightDrawable(Context context, int id, TextView v, String str, Object color) {
        Drawable drawable = getDrawable(context, id);
        assert drawable != null;
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        v.setCompoundDrawables(null, null, drawable, null);
        v.setCompoundDrawablePadding(6);
        if (str != null)
            v.setText(str);
        if (color != null)
            setTextColor(context, v, (int) color);
    }

    public static void setBottomDrawable(Context context, int id, TextView v, String str, Object color) {
        Drawable drawable = getDrawable(context, id);
        assert drawable != null;
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        v.setCompoundDrawables(null, null, null, drawable);
        if (str != null)
            v.setText(str);
        if (color != null)
            setTextColor(context, v, (int) color);

    }

    public static void setItemDrawable(Context context, int id, TextView v, String str, Object color) {
        Drawable drawable = getDrawable(context, id);
        Drawable drawable1 = getDrawable(context, R.mipmap.arrow_right);
        assert drawable != null;
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        assert drawable1 != null;
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        v.setCompoundDrawables(drawable, null, drawable1, null);
        if (str != null)
            v.setText(str);
        if (color != null)
            setTextColor(context, v, (int) color);

    }

    private static Drawable getDrawable(Context context, int id) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return context.getResources().getDrawable(id);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(id, null);
        }
        return null;
    }

    private static void setTextColor(Context context, TextView v, int color) {
        if (color != -1)
            v.setTextColor(context.getResources().getColor(color));
    }

    /**
     * base64转图
     */
    public static boolean GenerateImage(String imgStr, String headPicPath) {
        boolean flag = false;
        // 对字节数组字符串进行Base64解码并生成图片
        try {

            byte[] b = Base64.decode(imgStr, Base64.DEFAULT);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    // 调整异常数据
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(headPicPath);
            out.write(b);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }


}
