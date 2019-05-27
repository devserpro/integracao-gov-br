package br.serpro.cidadao.govbr;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GovBrWebViewClientTest {

    private static GovBrWebViewClient client;
    private static String codeRecuperado;

    @BeforeClass
    public static void setUp() {
        client = new GovBrWebViewClient() {

            @Override
            public void onCodeRecuperado(String code) {

                codeRecuperado = code;

            }
        };
    }

    @Test
    public void testOnPageFinishedCodeInicio() {
        String url = "https://staging.acesso.gov.br/acesso?code=2112&bablah=true&dddd=112&dadaf=1123";

        client.onPageFinished(null, url);

        assertEquals("2112", codeRecuperado);
    }

    @Test
    public void testOnPageFinishedCodeMeio() {
        String url = "https://staging.acesso.gov.br/acesso?bablah=true&dddd=112&code=1123&dadaf=darg";

        client.onPageFinished(null, url);

        assertEquals("1123", codeRecuperado);
    }

    @Test
    public void testOnPageFinishedCodeFinal() {
        String url = "https://staging.acesso.gov.br/acesso?bablah=true&dddd=112&dadaf=1123&code=1231111";

        client.onPageFinished(null, url);

        assertEquals("1231111", codeRecuperado);
    }



}
