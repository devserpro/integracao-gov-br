package br.serpro.cidadao.govbr

import android.content.Context
import android.util.AttributeSet
import android.webkit.*

import java.math.BigInteger
import java.security.SecureRandom

class GovBrWebView : WebView {

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    /**
     * @param ambiente
     */
    fun loginNoGovBr(ambiente: String, client: GovBrWebViewClient) {
        webViewClient = client

        loadUrl(this.getUrlProvider(ambiente))
    }

    private fun initView() {
        clearWebView()

        settings.setAppCacheEnabled(false)
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.saveFormData = false
        settings.javaScriptEnabled = true
    }

    private fun getUrlProvider(urlProvider: String): String {
        return urlProvider +
                "/authorize?response_type=code&client_id=" + context?.getString(R.string.gov_br_client_id) +
                "&scope=" + context?.getString(R.string.gov_br_scopes) +
                "&redirect_uri=" + context?.getString(R.string.gov_br_redirect_url) +
                "&nonce=" + randomString(12) +
                "&state=" + randomString(13)
    }

    private fun clearCookies() {
        CookieSyncManager.createInstance(context)
        val cookieManager = CookieManager.getInstance()
        cookieManager.removeAllCookie()
    }

    fun clearWebView() {
        clearCache(true)
        clearHistory()
        clearFormData()
        clearView()

        clearCookies()
    }

    companion object {
        val DESENV = "http://sso.staging.acesso.gov.br"
        val HOMOLOGACAO = "http://sso.validacao.acesso.gov.br"
        val PRODUCAO = "http://sso.acesso.gov.br"

        private fun randomString(length: Int): String {
            val letters = "abcdefghijklmnopqrstuvwxyz0123456789"
            return (1..length)
                .map { kotlin.random.Random.nextInt(0, letters.length) }
                .map(letters::get)
                .joinToString("")
        }
    }

}
