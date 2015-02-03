package com.hbrisson.applicationbase;

import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.widget.Button;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

@EActivity(R.layout.activity_authentification)
public class LoginActivity extends Activity {

    @AfterInject
    void create() {

    }

    @AfterViews
    void configure() {

    }

    @Click(R.id.connectButton)
    void click() {
        startActivity(new Intent(this, HomeActivity_.class));
        finish();
    }

}
