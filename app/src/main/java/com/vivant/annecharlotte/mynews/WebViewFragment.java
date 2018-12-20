package com.vivant.annecharlotte.mynews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {

    @BindView(R.id.webview) WebView mWebView;

    public WebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://www.google.com");
        return view;
    }

}
