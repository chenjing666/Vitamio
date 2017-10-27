package io.vov.vitamio;

import android.content.Intent;

import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModule;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;

public class VitamioPlayer extends UZModule {


    public VitamioPlayer(UZWebView webView) {
        super(webView);
    }

    public void jsmethod_startVitamio(UZModuleContext moduleContext) {
        Intent intent = new Intent(getContext(), VitamioPlayerActivity.class);
        intent.putExtra("appParam", moduleContext.optString("appParam"));
        startActivity(intent);
    }
}
