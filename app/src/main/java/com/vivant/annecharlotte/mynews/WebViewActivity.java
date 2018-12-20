package com.vivant.annecharlotte.mynews;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    private String mURL;

    public void setURL(String URL) {
        mURL = URL;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebView = findViewById(R.id.webview);
        mURL = getIntent().getStringExtra("ArticleURL");

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        //mWebView.loadUrl("http://www.google.com");
        Log.d("testUrl", "onCreate: " + mURL);
        mWebView.loadUrl(mURL);

    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
