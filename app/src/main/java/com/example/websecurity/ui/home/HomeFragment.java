package com.example.websecurity.ui.home;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
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
import com.example.websecurity.ui.MainActivity;

public class HomeFragment extends Fragment {


    final Handler myHandler = new Handler();

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        WebView myWebView = (WebView) root.findViewById(R.id.webview2);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false; // then it is not handled by default action
            }
        });

        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebChromeClient(new WebChromeClient() {
            // Grant permissions for cam

            String TAG = "HI";
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                getActivity().runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                            request.grant(request.getResources());
                    }
                });
            }


        });
        final JavaScriptInterface myJavaScriptInterface = new JavaScriptInterface(this);
        myWebView.addJavascriptInterface(myJavaScriptInterface, "jsinterface");
        myWebView.loadUrl("https://facebook.com");

        return root;
    }


    // interface for the webview
    public class JavaScriptInterface {
        HomeFragment mContext;

        public JavaScriptInterface(HomeFragment homeFragment) {
            mContext = homeFragment;
        }

        public void showToast(String webMessage){
            myHandler.post(new Runnable() {
                @Override
                public void run() {
                    // This gets executed on the UI thread so it can safely modify Views"
                }
            });

            Toast.makeText(getActivity(), webMessage, Toast.LENGTH_SHORT).show();
        }
    }
}