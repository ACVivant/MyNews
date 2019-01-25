package com.vivant.annecharlotte.mynews.Views;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;

        import com.vivant.annecharlotte.mynews.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebView = findViewById(R.id.webview);
        String mURL = getIntent().getStringExtra("ArticleURL");

        mWebView.setWebViewClient(new WebViewClient());

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
