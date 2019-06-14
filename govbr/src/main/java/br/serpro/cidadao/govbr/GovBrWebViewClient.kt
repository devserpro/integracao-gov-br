package br.serpro.cidadao.govbr

import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import java.util.regex.Pattern

abstract class GovBrWebViewClient(private val redirectUrl: String) : WebViewClient() {

    /**
     * @param code
     */
    abstract fun onCodeRecuperado(code: String)

    override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
        //super.onReceivedSslError(view, handler, error);
        //permitir certificado https do SERPRO INVALIDO
        handler.proceed()
    }

    override fun onPageFinished(view: WebView, url: String) {
        val pattern = Pattern.compile("code=([^&]+)")

        val matcher = pattern.matcher(url)

        if (url.startsWith(redirectUrl) && matcher.find()) {
            onCodeRecuperado(matcher.group(1))
        }
    }

}
