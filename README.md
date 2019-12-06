# Integracao GOVBR

[![](https://jitpack.io/v/devserpro/integracao-gov-br.svg)](https://jitpack.io/#devserpro/integracao-gov-br)

## Como usar o componente em seu app Android

Adicionar em seu `build.gradle` (app):

```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

e:

```gradle
dependencies {
    compile 'com.github.jitpack:android-example:{latest version}'
}
```

Inclua em seu arquivo `AndroidManifest.xml` o seguinte:
```xml
<application
        ...
        android:usesCleartextTraffic="true"
        ...>
    ...
</application>
```

Em seu arquivo de strings (`strings.xml`) incluir o seguinte:

```xml
<string name="gov_br_client_id">seu_client_id</string>
<string name="gov_br_scopes">scopes_que_ira_usar</string>
<string name="gov_br_redirect_url">sua_redirect_url</string>
```

ou se possui `flavors`, pode declarar em seu `build.gradle`:

```gradle
flavor {
    resValue "string", "gov_br_client_id", "seu_client_id"
    resValue "string", "gov_br_scopes", "scopes_que_ira_usar"
    resValue "string", "gov_br_redirect_url", "sua_redirect_url"
}
```

# Exemplo de Activity

```kotlin
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
```
