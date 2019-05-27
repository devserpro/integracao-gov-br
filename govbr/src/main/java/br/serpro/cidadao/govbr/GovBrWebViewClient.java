package br.serpro.cidadao.govbr;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class GovBrWebViewClient extends WebViewClient {

    /**
     *
     * @param code
     */
    public abstract void onCodeRecuperado(String code);

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
//				  permitir certificado https do SERPRO INVALIDO
        handler.proceed();
    }

    @Override
    public void onPageStarted(WebView view, String url,
                              Bitmap favicon) {
    }

    @Override
    public void onPageFinished(WebView view, String url) {

        Pattern pattern = Pattern.compile("code=([^&]+)");

        Matcher matcher = pattern.matcher(url);

        if(matcher.find()) {

            onCodeRecuperado(matcher.group(1));

        }

    }


}
