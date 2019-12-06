package br.serpro.govbr.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.serpro.cidadao.govbr.GovBrWebView
import br.serpro.cidadao.govbr.GovBrWebViewClient
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Para usar esse app de exemplo, modifique as seguintes strings no strings.xml:
 * gov_br_client_id, gov_br_scopes e gov_br_redirect_url com os seus dados
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webViewGovBr.loginNoGovBr(GovBrWebView.DESENV, Client(getString(R.string.gov_br_redirect_url), this))
    }

    override fun onDestroy() {
        webViewGovBr.clearWebView()
        super.onDestroy()
    }

    class Client(redirectUrl: String, private val mainActivity: MainActivity) : GovBrWebViewClient(redirectUrl) {
        override fun onCodeRecuperado(code: String) {
            mainActivity.codeRetornadoTextView.text = "Código recuperado:\n$code"
        }
    }

}
