package com.uvita.myapp.modules.baseComponents;

import android.content.res.Resources;
import androidx.fragment.app.FragmentActivity;
import android.view.View;

public interface BaseView {
    /**
     *
     * Returns activity of the view.
     *
     * @return Activity of the view.
     */
    FragmentActivity getCurrentActivity();
    View getRootView();
    Resources getSResources();
}