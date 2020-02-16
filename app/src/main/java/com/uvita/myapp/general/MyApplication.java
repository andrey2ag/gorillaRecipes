package com.uvita.myapp.general;


import androidx.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.uvita.myapp.BuildConfig;
import com.uvita.myapp.modules.repository.local.AppDB;

import static com.uvita.myapp.general.LogUtil.LOGE;

public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize DB INSTANCE
        AppDB.createAppDatabase(this);

        checkAndInitStetho();
    }

    private void checkAndInitStetho() {
        //Stetho integration
        if (BuildConfig.DEBUG) {
            try {
                Stetho.initialize(
                        Stetho.newInitializerBuilder(MyApplication.this)
                                .enableDumpapp(Stetho.defaultDumperPluginsProvider(MyApplication.this))
                                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(MyApplication.this))
                                .build());
            } catch (Exception e) {
                LOGE("ERROR", "Error on checkAndInitStetho()", e);
            }
        }
    }

}
