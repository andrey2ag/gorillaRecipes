package com.uvita.myapp.modules.baseComponents;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import com.uvita.myapp.R;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public abstract class BaseActivity<BViewModel extends BaseViewModel, BBinding extends ViewDataBinding>
        extends AppCompatActivity implements BaseView {
    protected BBinding binding;

    public abstract BViewModel getViewModel();

    public abstract void initializeDataBinding();

    @SuppressWarnings("unchecked")
    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        // initialize Data-binding must be called before getViewModel() because to generate the viewModel needs the UI data-binding
        initializeDataBinding();
        getViewModel().setView(this);
        setupToolbar();
    }

    private void setupToolbar() {
        setToolbarTitle(getSResources().getText(getToolbarTitle()).toString());
    }

    protected void setToolbarTitle(String title){
        final Toolbar toolbar = binding.getRoot().findViewById(R.id.toolbar);
//         check because not all activities use toolbar... ex, LoginActivity
        if (toolbar != null) {
            final TextView textView = toolbar.findViewById(R.id.tvTitle);
            textView.setText(title);
            setSupportActionBar(toolbar);
        }
    }

    public int getToolbarTitle() {
        return R.string.title_activity_main;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getViewModel().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getViewModel().onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getViewModel().onResume();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    @NonNull
    public FragmentActivity getCurrentActivity() {
        return this;
    }

    @NonNull
    public View getRootView() {
        return binding.getRoot();
    }

    @Override
    @NonNull
    public Resources getSResources() {
        return getResources();
    }
}