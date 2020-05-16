package tz.co.nyotaapps.bbcradio.bbcradio;

import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.skyfishjy.library.RippleBackground;


import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.multidex.MultiDex;

public class MainActivity extends AppCompatActivity implements ExoPlayer.EventListener {



    private Handler mainHandler;
    private RenderersFactory renderersFactory;
    private BandwidthMeter bandwidthMeter;
    private LoadControl loadControl;
    private DataSource.Factory dataSourceFactory;
    private ExtractorsFactory extractorsFactory;
    private MediaSource mediaSource;
    private TrackSelection.Factory trackSelectionFactory;
    private SimpleExoPlayer player;
    private boolean playing = false;
    private int firsttime = 1;
    //private final String streamUrl = "http://bbcwssc.ic.llnwd.net/stream/bbcwssc_mp1_ws-einws"; //bbc world service url
    private final String streamUrl = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_6music_mf_p"; //bbc world service url
    private final String BBCRadio1 = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio1_mf_p";
    private final String BBCRadio1xtra = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio1xtra_mf_p";
    private final String BBCRadio2 = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio2_mf_p";
    private final String BBCRadio3 = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio3_mf_p";
    private final String BBCRadio4FM = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio4fm_mf_p";
    private final String BBCRadio4LW = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio4lw_mf_p";
    private final String BBCRadio4Extra = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio4extra_mf_p";
    private final String BBCRadio5Live = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio5live_mf_p";
    private final String BBCRadio5LiveSportsballExtra = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio5extra_mf_p";
    private final String BBCRadio6Music = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_6music_mf_p";
    private final String BBCAsianNetwork = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_asianet_mf_p";
    private final String BBCWorldServiceUKstream = "http://bbcwssc.ic.llnwd.net/stream/bbcwssc_mp1_ws-eieuk";
    private final String BBCWorldServiceNewsstream = "http://bbcwssc.ic.llnwd.net/stream/bbcwssc_mp1_ws-einws";

    private final String BBCRadioCymru = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_cymru_mf_p";
    private final String BBCRadioFoyle = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_foyle_mf_p";
    private final String BBCRadioGàidheal = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_nangaidheal_mf_p";
    private final String BBCRadioScotland = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_scotlandfm_mf_p";
    private final String BBCRadioUlster = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_ulster_mf_p";
    private final String BBCRadioWales = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_walesmw_mf_p";

    private final String BBCRadioBerkshire = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrberk_mf_p";
    private final String BBCRadioBristol = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrbris_mf_p";
    private final String BBCRadioCambridgeshire = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrcambs_mf_p";
    private final String BBCRadioCornwall = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrcorn_mf_p";
    private final String BBCCoventryWarwickshire = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrwmcandw_mf_p";
    private final String BBCRadioCumbria = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrcumbria_mf_p";
    private final String BBCRadioDerby = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrderby_mf_p";
    private final String BBCRadioDevon = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrdevon_mf_p";
    private final String BBCEssex = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lressex_mf_p";
    private final String BBCRadioGloucestershire = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrgloucs_mf_p";
    private final String BBCRadioGuernsey = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrguern_mf_p";
    private final String BBCHerefordWorcester = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrhandw_mf_p";
    private final String BBCRadioHumberside = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrhumber_mf_p";
    private final String BBCRadioJersey = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrjersey_mf_p";
    private final String BBCRadioKent = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrkent_mf_p";
    private final String BBCRadioLancashire = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrlancs_mf_p";
    private final String BBCRadioLeeds = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrleeds_mf_p";
    private final String BBCRadioLeicester = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrleics_mf_p";
    private final String BBCRadioLincolnshire = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrlincs_mf_p";
    private final String BBCRadioLondon = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrldn_mf_p";
    private final String BBCRadioManchester = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrmanc_mf_p";
    private final String BBCRadioMerseyside = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrmersey_mf_p";
    private final String BBCNewcastle = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrnewc_mf_p";
    private final String BBCRadioNorfolk = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrnorfolk_mf_p";
    private final String BBCRadioNorthampton = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrnthhnts_mf_p";
    private final String BBCRadioNottingham = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrnotts_mf_p";
    private final String BBCRadioOxford = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lroxford_mf_p";
    private final String BBCRadioSheffield = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrsheff_mf_p";
    private final String BBCRadioShropshire = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrshrops_mf_p";
    private final String BBCRadioSolent = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrsolent_mf_p";
    private final String BBCSomerset = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrsomer_mf_p";
    private final String BBCRadioStoke = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrsomer_mf_p";
    private final String BBCRadioSuffolk = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrsuffolk_mf_p";
    private final String BBCSurrey = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrsurrey_mf_p";
    private final String BBCSussex = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrsussex_mf_p";
    private final String BBCTees = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrtees_mf_p";
    private final String BBCThreeCountiesRadio = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lr3cr_mf_p";
    private final String BBCWiltshire = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrwilts_mf_p";
    private final String BBCWM = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lrwm_mf_p";
    private final String BBCRadioYork = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_lryork_mf_p";

    private TrackSelector trackSelector;
    //private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private PlaybackControlView simpleExoPlayerView;
    private int p =1;
    private RippleBackground rippleBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow(). addFlags (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //MobileAds.initialize(this, "ca-app-pub-4482019772887748~6245522576");

       // mAdView = findViewById(R.id.adView);
        /*
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(MainActivity.this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4482019772887748/4872731152");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

         */


        simpleExoPlayerView = (PlaybackControlView) findViewById(R.id.video_view);
        simpleExoPlayerView.requestFocus();
        renderersFactory = new DefaultRenderersFactory(getApplicationContext());
        bandwidthMeter = new DefaultBandwidthMeter();
        trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(trackSelectionFactory);
        loadControl = new DefaultLoadControl();

        player = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector, loadControl);
        player.addListener(this);

        dataSourceFactory = new DefaultDataSourceFactory(getApplicationContext(), "ExoplayerDemo");
        extractorsFactory = new DefaultExtractorsFactory();
        mainHandler = new Handler();


        rippleBackground=(RippleBackground)findViewById(R.id.content);


        runstuff();

        TextView usiku = findViewById(R.id.weekendtwo);
        usiku.setText("BBC World Service UK stream");


        mediaSource = new ExtractorMediaSource(Uri.parse("http://bbcwssc.ic.llnwd.net/stream/bbcwssc_mp1_ws-eieuk"),
                dataSourceFactory,
                extractorsFactory,
                mainHandler,
                null);

        simpleExoPlayerView.setPlayer(player);
        player.prepare(mediaSource);



        rippleBackground.stopRippleAnimation();


        MobileAds.initialize(this, "ca-app-pub-4482019772887748~6245522576");

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(MainActivity.this);
       // mInterstitialAd.setAdUnitId("ca-app-pub-4482019772887748/4872731152");
        mInterstitialAd.setAdUnitId("ca-app-pub-4482019772887748/4872731152");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        //mInterstitialAd.loadAd(new AdRequest.Builder().build());




    }





public void runstuff(){


    HashMap<String, String> hashMap = new HashMap<String, String>();

    hashMap.put("BBC Radio 1", "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio1_mf_p");
    hashMap.put("BBC Radio 1 xtra", "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio1xtra_mf_p");
    hashMap.put("BBC Radio 2", "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio2_mf_p");
    hashMap.put("BBC Radio 3", "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio3_mf_p");
    hashMap.put("BBC Radio 4 FM", "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio4fm_mf_p");
    hashMap.put("BBC Radio 4 LW", "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio4lw_mf_p");
    hashMap.put("BBC Radio 4 Extra", "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio4extra_mf_p");
    hashMap.put("BBC Radio 5 Live", "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio5live_mf_p");
    hashMap.put("BBC Radio 5 Live Sports ball Extra", "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio5extra_mf_p");
    hashMap.put("BBC Radio 6 Music", "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_6music_mf_p");
    hashMap.put("BBC Asian Network", "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_asianet_mf_p");
    hashMap.put("BBC World Service UK stream", "http://bbcwssc.ic.llnwd.net/stream/bbcwssc_mp1_ws-eieuk");
    hashMap.put("BBC World Service News stream", "http://bbcwssc.ic.llnwd.net/stream/bbcwssc_mp1_ws-einws");
    hashMap.put("BBC World Service", "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_6music_mf_p");//bbc world service url


            final LinearLayout coff = findViewById(R.id.trump);

            // looping through All Contacts
            for (final Map.Entry<String, String> entry : hashMap.entrySet()) {

                //System.out.println(entry.getKey() + " = " + entry.getValue());



                //System.out.println(file.getName());
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                final View convertViewxxx = inflater.inflate(R.layout.songview, coff, false);





                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        TextView bnh = convertViewxxx.findViewById(R.id.info_text);
                        bnh.setText(entry.getKey());

                        convertViewxxx.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                //onDestroy();


                                if (mInterstitialAd.isLoaded()) {
                                    mInterstitialAd.show();
                                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                } else {
                                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                    Log.e("TAG", "The interstitial wasn't loaded yet.");
                                }



                                LinearLayout nnm = findViewById(R.id.weekend);
                                nnm.setVisibility(View.VISIBLE);
                                TextView usiku = findViewById(R.id.weekendtwo);
                                usiku.setText(entry.getKey());


                                mediaSource = new ExtractorMediaSource(Uri.parse(entry.getValue()),
                                        dataSourceFactory,
                                        extractorsFactory,
                                        mainHandler,
                                        null);

                                simpleExoPlayerView.setPlayer(player);
                                player.prepare(mediaSource);

                            }
                        });



                        if(p == 6 || p == 12 || p == 50){

                            /*
                            AdView adViewp = new AdView(MainActivity.this);
                            adViewp.setAdSize(AdSize.BANNER);
                            adViewp.setAdUnitId("ca-app-pub-4482019772887748/9442531551");
                            AdRequest adRequest = new AdRequest.Builder().build();
                            adViewp.loadAd(adRequest);
                            coff.addView(adViewp);

                             */

                        }else {
                            coff.addView(convertViewxxx);
                        }



                    }
                });


                p = p+1;

            }

    //mInterstitialAd.loadAd(new AdRequest.Builder().build());



}

















    @Override
    protected void onResume() {
        super.onResume();
        player.setPlayWhenReady(true);
        Log.e("PLAYER","ON RESUME");
        //rippleBackground.startRippleAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.setPlayWhenReady(false);
        //Toast.makeText(MainActivity.this, "Exoplayer is on pause.", Toast.LENGTH_SHORT).show();
        rippleBackground.stopRippleAnimation();
        Log.e("PLAYER","ON PAUSE");
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
        Log.e("PLAYER","ON TIMELINECHANGED");
    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        Log.e("PLAYER","ON TRACKCHANGED");
        playing = true;
        ProgressBar usiku = findViewById(R.id.loading_spinner);
        usiku.setVisibility(View.GONE);
    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

        rippleBackground.stopRippleAnimation();
        Log.e("PLAYER","ON LOADINGCHANGED");


    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if(playbackState == 3){
            ProgressBar usiku = findViewById(R.id.loading_spinner);
            usiku.setVisibility(View.GONE);
            TextView usikux = findViewById(R.id.wloading);
            usikux.setVisibility(View.GONE);
            TextView usikuhh = findViewById(R.id.weekendtwo);
            usikuhh.setVisibility(View.VISIBLE);
            rippleBackground.startRippleAnimation();
        }else{
            ProgressBar usiku = findViewById(R.id.loading_spinner);
            usiku.setVisibility(View.VISIBLE);
            TextView usikux = findViewById(R.id.wloading);
            usikux.setVisibility(View.VISIBLE);
            TextView usikuhh = findViewById(R.id.weekendtwo);
            usikuhh.setVisibility(View.GONE);
            rippleBackground.stopRippleAnimation();
        }




        Log.e("PLAYER","ON PLAYERSTATUS CHANGED. PLAYBACK STATE "+playbackState);
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        rippleBackground.stopRippleAnimation();
        Log.e("PLAYER","ON ERROR");


        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setTitle("Network error");
        builder.setMessage("Can not play stream due to network error..");
        builder.setPositiveButton("OK", null);
        //builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    @Override
    public void onPositionDiscontinuity(int reason) {
        Log.e("PLAYER","ON POSIYIONDISCONTINUITY");
    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        Log.e("PLAYER","ON PLAYBACK PARAMETER CHANGED");
    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onSeekProcessed() {

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        Log.e("PLAYER","ON CONTEXT ATTACHED");
    }




}
