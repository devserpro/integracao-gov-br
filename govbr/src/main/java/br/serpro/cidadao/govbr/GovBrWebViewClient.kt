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
abstract class GovBrWebViewClient(private val redirectUrl: String, private val urlPageLoaded: String = redirectUrl) :
    WebViewClient() {

    /**
     * @param code
     */
    abstract fun onCodeRecuperado(code: String)

    override fun onPageFinished(view: WebView, url: String) {
        val pattern = Pattern.compile("code=([^&]+)")

        val matcher = pattern.matcher(url)

        if (url.startsWith(urlPageLoaded) && matcher.find()) {
            onCodeRecuperado(matcher.group(1))
        }
    }

}
