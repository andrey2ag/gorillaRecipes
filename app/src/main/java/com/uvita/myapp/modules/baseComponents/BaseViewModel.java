package com.uvita.myapp.modules.baseComponents;

import androidx.lifecycle.ViewModel;
import com.uvita.myapp.general.MyApp;
import com.uvita.myapp.modules.main.SetTitleEvent;


public abstract class BaseViewModel<BView extends BaseView> extends ViewModel {
    private BView bView;

    public void setView(BView bView) {
        this.bView = bView;
    }

    public BView getView() {
        return bView;
    }

    public void setTitle(final String title){
        MyApp.getEventBus().post(new SetTitleEvent(title));
    }

    public void onResume(){}
    public abstract void onStart();
    public abstract void onStop();
}