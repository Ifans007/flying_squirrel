package com.ifansdev.flyingsquirrel.helpers;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.ifansdev.flyingsquirrel.AndroidLauncher;

public class AdMob implements IAdMob {
    private AdView adView;

    private final int SHOW_ADS = 1;
    private final int HIDE_ADS = 0;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_ADS:
                    adView.setVisibility(View.VISIBLE);
                    break;
                case HIDE_ADS:
                    adView.setVisibility(View.GONE);
                    break;
            }
        }
    };

    public AdMob(AndroidLauncher androidLauncher) {
        adView = new AdView(androidLauncher);
        adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest.Builder builder = new AdRequest.Builder();

        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        adView.setLayoutParams(adParams);
        adView.loadAd(builder.build());
        adView.setVisibility(AdView.VISIBLE);
    }

    public AdView getAdView() {
        return adView;
    }

    public void showAds(boolean show) {
        handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
    }
}
