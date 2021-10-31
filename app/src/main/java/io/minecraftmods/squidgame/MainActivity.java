package io.minecraftmods.squidgame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int[] CAROUSEL_SCREEN_RES_IDS = { R.drawable.screen1, R.drawable.screen2, R.drawable.screen3, R.drawable.screen4, R.drawable.screen5, R.drawable.screen6 };
    //private static final int[] CAROUSEL_ASO_RES_IDS = { R.drawable.aso1, R.drawable.aso2, R.drawable.aso3 };
    private static final String MOD_EXT = "mcaddon";
    private static final String TRAILER_URL = "https://www.youtube.com/embed/4OFNxCzz3xA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showCustomMenuBar();
        initMainViews();
        initTrailer();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initTrailer() {
        WebView trailerView = findViewById(R.id.trailerView);

        trailerView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        WebSettings webSettings = trailerView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        trailerView.loadUrl(TRAILER_URL);
    }

    private void initMainViews() {
        CarouselView carouselScreens = findViewById(R.id.carouselScreens);
        initCarousel(carouselScreens, CAROUSEL_SCREEN_RES_IDS);
        //CarouselView carouselAso = findViewById(R.id.carouselAso);

        TextView descrPt1 = findViewById(R.id.descrPt1);
        descrPt1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView descrPt2 = findViewById(R.id.descrPt2);
        descrPt2.setMovementMethod(LinkMovementMethod.getInstance());

        ImageView helpView = findViewById(R.id.helpView);
        setHelpOnClickListener(helpView);

        AppCompatButton installButton = findViewById(R.id.installButton);
        installButton.setOnClickListener(new ModInstallOnClickListener(R.raw.mod, MOD_EXT));
    }

    private void initCarousel(CarouselView carouselView, int[] resIds) {

        ImageListener imageListener = (position, imageView) -> imageView.setImageResource(resIds[position]);
        carouselView.setImageListener(imageListener);

        int pageCount = resIds.length;
        carouselView.setPageCount(pageCount);

    }

    private void setHelpOnClickListener(ImageView helpView) {
        helpView.setOnClickListener(view ->
                startActivity(new Intent(this, InfoActivity.class)));
    }

    private void showCustomMenuBar() {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setElevation(0);
    }

}