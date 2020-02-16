package com.uvita.myapp.general;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.uvita.myapp.R;

public class UIUtils {
    public static void showErrorSnackbar(@NonNull final Context context, @NonNull final View view, @NonNull final String message) {
        final Snackbar sb = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        sb.setAction("Ok", v1 -> sb.dismiss());
        sb.setActionTextColor(ResourcesCompat.getColor(context.getResources(), R.color.white, null));
        sb.getView().setBackgroundResource(R.color.red);
        UIUtils.setSnackBarBaseParam(context, sb);
        sb.show();
    }

    public static void showSuccessSnackbar(@NonNull final Context context, @NonNull final View view, @NonNull final String message) {
        final Snackbar sb = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        sb.setAction("Ok", v1 -> sb.dismiss());
        sb.setActionTextColor(ResourcesCompat.getColor(context.getResources(), R.color.white, null));
        sb.getView().setBackgroundResource(R.color.green);
        UIUtils.setSnackBarBaseParam(context, sb);
        sb.show();
    }

    public static void showInfoSnackbar(@NonNull final Context context, @NonNull final View view, @NonNull final String message) {
        final Snackbar sb = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        sb.setAction("Ok", v1 -> sb.dismiss());
        sb.setActionTextColor(ResourcesCompat.getColor(context.getResources(), R.color.white, null));
        sb.getView().setBackgroundResource(R.color.blue);
        UIUtils.setSnackBarBaseParam(context, sb);
        sb.show();
    }

    public static Snackbar showLoadingSnackbar(@NonNull final Context context, @NonNull final View view, @NonNull final String message) {
        Snackbar bar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        ViewGroup contentLay = (ViewGroup) bar.getView().findViewById(com.google.android.material.R.id.snackbar_text).getParent();
        contentLay.addView(new ProgressBar(context));
        UIUtils.setSnackBarBaseParam(context, bar);
        bar.getView().setBackgroundResource(R.color.darkBlue);
        bar.show();
        return bar;
    }

    private static void setSnackBarBaseParam(@NonNull final Context context, @NonNull final Snackbar sb) {
//        sb.getView().setMinimumHeight((int) c.getResources().getDimension(R.dimen.mainFooterHeight));
        TextView tv = sb.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setGravity(Gravity.CENTER_VERTICAL);
//        tv.setMinimumHeight((int) c.getResources().getDimension(R.dimen.mainFooterHeight));
        tv.setSingleLine(false);
        tv.setMaxLines(5);
    }

    public static void hideKeyboard(Activity c) {
        View view = c.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
