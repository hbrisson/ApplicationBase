package com.hbrisson.applicationbase;

import android.app.Activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

@EActivity(R.layout.activity_authentification)
public class LoginActivity extends Activity {

    @AfterViews
    void configure(){
        Crouton.makeText(this,"erreur dans le code", Style.INFO).show();

    }
}
