package com.giorgiolupo.bemaxxfinal;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OnlineShop extends AppCompatActivity {
    Button b1;
    WebView w1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_shop);

        //get variables
        w1 = (WebView) findViewById(R.id.webview1);

        //check if loading
        w1.setWebViewClient(new MyBrowser());

        //click
            String url = "https://www.be-maxx.com/en/shop/";
            w1.getSettings().setLoadsImagesAutomatically(true);
            w1.getSettings().setJavaScriptEnabled(true);

            w1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            w1.loadUrl(url);





    }

    private class MyBrowser extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}