package com.example.websecurity.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.websecurity.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        WebView webView = (WebView) root.findViewById(R.id.webview);
        //javascript could be a vector to exploit your applications
        webView.getSettings().setJavaScriptEnabled(false);

        //default is off, but just in case. plugins could be a vector to exploit your applications process
        webView.getSettings().setPluginState(WebSettings.PluginState.OFF);

        //Should an attacker somehow find themselves in a position to inject script into a WebView, then they could exploit the opportunity to access local resources. This can be somewhat prevented by disabling local file system access. It is enabled by default. The Android WebSettings class can be used to disable local file system access via the public method setAllowFileAccess.
        //This restricts the WebView to loading local resources from file:///android_asset (assets) and file:///android_res (resources).
        webView.getSettings().setAllowFileAccess(false);

        //disable Geolocation API
        webView.getSettings().setGeolocationEnabled(false);

        webView.loadUrl("https://www.facebook.com");

        return root;
    }
}