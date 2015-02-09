package com.hbrisson.applicationbase;

import android.app.Activity;

import com.hbrisson.applicationbase.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EActivity;

/**
 * Created by hbrisson on 03/02/2015.
 */
@EActivity
public abstract class AbstractAnimationActivity extends Activity {

    @AfterInject
    void create(){
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
