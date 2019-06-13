package br.serpro.govbr.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.serpro.cidadao.govbr.GovBrWebView
import br.serpro.cidadao.govbr.GovBrWebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webViewGovBr.loginNoGovBr(GovBrWebView.DESENV, Client())
    }

    override fun onDestroy() {
        webViewGovBr.onDestroy()
        super.onDestroy()
    }

    class Client : GovBrWebViewClient() {
        override fun onCodeRecuperado(code: String?) {
            Log.d("AutenticadorGovBr", "Código recuperado: $code")
        }
    }
}
