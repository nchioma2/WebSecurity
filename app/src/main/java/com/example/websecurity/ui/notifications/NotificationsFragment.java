package com.example.websecurity.ui.notifications;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.websecurity.R;
import com.example.websecurity.ui.home.HomeFragment;

public class NotificationsFragment extends Fragment {

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        WebView myWebView = (WebView) root.findViewById(R.id.webview3);
        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        myWebView.loadUrl("https://www.facebook.com");
        myWebView.addJavascriptInterface(new MyJS(), "JsShow");
        myWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url){ view.loadUrl("https://www.google.com");
            }
        });

        return root;
    }

    public class MyJS {
        NotificationsFragment mContext;


        public void SendSecret(String secret) {
            Toast.makeText(getActivity(), secret, Toast.LENGTH_LONG).show();

        }
    }
}