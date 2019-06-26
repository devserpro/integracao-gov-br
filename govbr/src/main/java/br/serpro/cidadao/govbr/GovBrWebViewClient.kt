package br.serpro.cidadao.govbr

import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import java.util.regex.Pattern

/**
 * @param redirectUrl Url efetiva que foi cadastrada com o GOV.BR
 * @param urlPageLoaded Url final que será comparada ao carregar a página
 */
abstract class GovBrWebViewClient(private val redirectUrl: String, private val urlPageLoaded: String?) : WebViewClient() {

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

        val urlToCompare = urlPageLoaded ?: redirectUrl

        if (url.startsWith(urlToCompare) && matcher.find()) {
            onCodeRecuperado(matcher.group(1))
        }
    }

}
