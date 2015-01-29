package com.hbrisson.applicationbase;

import android.app.Activity;
import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EActivity;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

@EActivity(R.layout.activity_authentification)
public class AuthentificationActivity extends Activity {

    @AfterInject
    void configure(){
        Crouton.makeText(this,"erreur dans le code", Style.INFO).show();

    }
}
