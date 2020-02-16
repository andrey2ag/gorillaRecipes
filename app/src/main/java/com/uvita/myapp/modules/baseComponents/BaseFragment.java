package com.uvita.myapp.modules.baseComponents;


import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.view.View;

public abstract class BaseFragment<BViewModel extends BaseViewModel, BBinding extends ViewDataBinding>
        extends Fragment implements BaseView {
    protected BBinding binding;

    public abstract BViewModel getViewModel();


    @Override
    @CallSuper
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        getViewModel().setView(this);
    }

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        getViewModel().onStart();
    }

    @Override
    @CallSuper
    public void onStop() {
        super.onStop();
        getViewModel().onStop();
    }

    @Override
    public FragmentActivity getCurrentActivity() {
        return getActivity();
    }

    @Override
    public View getRootView() {
        return binding.getRoot();
    }

    @Override
    public Resources getSResources() {
        return getResources();
    }
}