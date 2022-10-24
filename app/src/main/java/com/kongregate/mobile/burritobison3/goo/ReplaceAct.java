package com.kongregate.mobile.burritobison3.goo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.facebook.applinks.AppLinkData;
import com.orhanobut.hawk.Hawk;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReplaceAct extends AppCompatActivity {

    public static final String C_STR1 = "cstR1";
    public static final String DLNK1 = "dfB1";
    private static final String AF_DEV_KEY = "Ta3ZKG3Vy456FUB8iP2p2E";

    String stMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace);
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            String hawkI = Hawk.get(C_STR1);
            if (hawkI != null) {
                Log.d("TestInUIHawk", hawkI);
                teleport();
                executorService.shutdown();
                finish();
            } else {
                Log.d("TestInUIHawk", "Received null");
            }
        }, 0, 2, TimeUnit.SECONDS);

        metaLnk();
        appsflyer();

        SharedPreferences prefs = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if (prefs.getBoolean("activity_exec", false)) {
            Intent intent = new Intent(this, Filter.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor exec = prefs.edit();
            exec.putBoolean("activity_exec", true);
            exec.apply();
        }
    }

    public void metaLnk() {

        AppLinkData.fetchDeferredAppLinkData(this,
                appLinkData -> {
                    if (appLinkData != null) {
                        String params;
                        params = appLinkData.getTargetUri().getHost();
                        Hawk.put(DLNK1, params);
                        Log.d("FB", params);
                    } else {
                        Log.d("FB", "Error Code:");
                    }
                }
        );
    }

    public void teleport() {

        Intent intent = new Intent(ReplaceAct.this, Filter.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    public void appsflyer() {
        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {

            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {


                Log.d("TESTING_ZONE", "af stat is " + conversionData.get("af_status"));
                String status = (String) conversionData.get("af_status");
                if (Objects.equals(status, "Organic")) {
                    stMain = "null";
                } else {
                    stMain = (String) conversionData.get("campaign");
                }
                Hawk.put(C_STR1, stMain);
                Log.d("NAMING TEST", "campaign attributed: " + stMain);
            }


            @Override
            public void onConversionDataFail(String errorMessage) {
            }

            @Override
            public void onAppOpenAttribution(Map<String, String> attributionData) {
            }

            @Override
            public void onAttributionFailure(String errorMessage) {
            }

        };

        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionListener, this);
        AppsFlyerLib.getInstance().start(this);
        AppsFlyerLib.getInstance().setDebugLog(true);

    }
}

