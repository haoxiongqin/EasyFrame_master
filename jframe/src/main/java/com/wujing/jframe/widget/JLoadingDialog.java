package com.wujing.jframe.widget;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wujing.jframe.R;
import com.wujing.jframe.utils.JEmptyUtils;
import com.wujing.jframe.utils.JOutMethodUtils;

/**
 * 加载等待提示框
 */
public class JLoadingDialog extends Dialog {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private static JLoadingDialog dialog;
    private Context context;
    private TextView loadingMessage;
    private ProgressBar progressBar;
    private LinearLayout loadingView;
    private JColorDrawable drawable;

    public JLoadingDialog(Context context) {
        super(context, R.style.loading_dialog);
        this.context = context;
        drawable = new JColorDrawable();
        setContentView(R.layout.jloading_dialog);
        loadingMessage = (TextView) findViewById(R.id.jframe_loading_message);
        progressBar = (ProgressBar) findViewById(R.id.jframe_loading_progressbar);
        loadingView = (LinearLayout) findViewById(R.id.jframe_loading_view);
        loadingMessage.setPadding(15, 0, 0, 0);
        drawable.setColor(Color.WHITE);
        JOutMethodUtils.setBackground(loadingView, drawable);
    }

    public static JLoadingDialog with(Context context) {
        if (dialog == null) {
            dialog = new JLoadingDialog(context);
        }
        return dialog;
    }

    public JLoadingDialog setOrientation(int orientation) {
        loadingView.setOrientation(orientation);
        if (orientation == HORIZONTAL) {
            loadingMessage.setPadding(15, 0, 0, 0);
        } else {
            loadingMessage.setPadding(0, 15, 0, 0);
        }
        return dialog;
    }

    public JLoadingDialog setBackgroundColor(@ColorInt int color) {
        drawable.setColor(color);
        JOutMethodUtils.setBackground(loadingView, drawable);
        return dialog;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (dialog != null)
            dialog = null;
    }

    public JLoadingDialog setCanceled(boolean cancel) {
        setCanceledOnTouchOutside(cancel);
        setCancelable(cancel);
        return dialog;
    }

    public JLoadingDialog setMessage(String message) {
        if (!JEmptyUtils.isSpace(message)) {
            loadingMessage.setText(message);
        }
        return this;
    }

    public JLoadingDialog setMessageColor(@ColorInt int color) {
        loadingMessage.setTextColor(color);
        return this;
    }
}
