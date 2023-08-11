package com.cekresi.cekongkir.ads;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStoreOwner;

import com.cekresi.cekongkir.Adapter.ResiHistoryAdapterTest;
import com.cekresi.cekongkir.ui.home.HomeViewModel;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdmobAdapter {

    Activity activity;
    Context context;
    private ArrayList<Object> objects;
    private List<NativeAd> nativeAdList;
    private HomeViewModel homeViewModel;
    ViewModelStoreOwner viewModelStoreOwner;
    private ResiHistoryAdapterTest resiHistoryAdapterTest;


    private static final String AD_BANNER_ID = "ca-app-pub-9161313753252016/8371255828";
    private AdView adView;
    private FrameLayout adContainerView;
    private InterstitialAd mInterstitialAd;


    public AdmobAdapter (Activity activity, Context ctx){
        this.activity = activity;
        this.context = ctx;
    }

    public void initizeadmob(){
        MobileAds.setRequestConfiguration(new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("BCAD6EB3C56B8812C539F8C6D5CBC900")).build());
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                loadInter();
            }
        });
    }

    public void loadInter(){
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(context, "ca-app-pub-9161313753252016/8654724603", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.d("interstitialAd", loadAdError.toString());
                mInterstitialAd = null;
                initizeadmob();
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                mInterstitialAd = interstitialAd;
                Log.i("interstitialAd", "onAdLoaded");
            }
        });
    }

    private void InterstitialCallBack() {
        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                Log.d("interstitialAd", "Ad was clicked.");
                loadInter();
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.d("interstitialAd", "Ad dismissed fullscreen content.");
                mInterstitialAd = null;
                loadInter();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show.
                Log.e("interstitialAd", String.valueOf(adError));
                mInterstitialAd = null;
                loadInter();
            }

            @Override
            public void onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d("interstitialAd", "Ad recorded an impression.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                loadInter();
                Log.d("interstitialAd", "Ad showed fullscreen content.");
            }
        });
    }

    public void showInterstitial(){
        if (mInterstitialAd != null) {
            InterstitialCallBack();
            mInterstitialAd.show(activity);
        } else {
            Log.d("interstitialAd", "The interstitial ad wasn't ready yet.");
        }
    }
}
