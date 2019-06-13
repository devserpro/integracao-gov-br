package br.serpro.cidadao.govbr;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.*;

import java.math.BigInteger;
import java.security.SecureRandom;

public class GovBrWebView extends WebView {

    Context context;

    public static final String DESENV = "http://sso.staging.acesso.gov.br";
    public static final String HOMOLOGACAO = "http://sso.validacao.acesso.gov.br";
    public static final String PRODUCAO = "http://sso.acesso.gov.br";

    public GovBrWebView(Context context) {
        super(context);
        initView(context);
    }

    public GovBrWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    /**
     * @param ambiente
     */
    public void loginNoGovBr(String ambiente, GovBrWebViewClient client) {
        setWebViewClient(client);

        loadUrl(this.getUrlProvider(ambiente));
    }

    /**
     * @param context
     */
    private void initView(Context context) {
        this.context = context;
        getSettings().setAppCacheEnabled(false);
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        getSettings().setSaveFormData(false);
        clearCache(true);
        clearHistory();
        clearFormData();
        getSettings().setJavaScriptEnabled(true);

        setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                //setProgress(progress * 100);
            }
        });
    }

    private String getUrlProvider(String urlProvider) {
        return urlProvider +
                "/authorize?response_type=code&client_id=" + context.getString(R.string.gov_br_client_id) +
                "&scope=" + context.getString(R.string.gov_br_scopes) +
                "&redirect_uri=" + context.getString(R.string.gov_br_redirect_url) +
                "&nonce=" + createRandomNumber() +
                "&state=" + createRandomNumber();
    }

    private static String createRandomNumber() {
        return new BigInteger(50, new SecureRandom()).toString(16);
    }

    public void onDestroy() {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
    }

}
