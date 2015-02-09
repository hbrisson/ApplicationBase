package com.hbrisson.applicationbase;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.hbrisson.applicationbase.database.model.KeyDAO;
import com.hbrisson.applicationbase.database.model.UserDAO;
import com.hbrisson.applicationbase.entites.User;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

@EActivity(R.layout.activity_authentification)
public class LoginActivity extends Activity {

    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private KeyDAO keyDAO;
    private String keyRemember = "remember";

    @ViewById(R.id.progressBar)
    NumberProgressBar progressBar;
    @ViewById(R.id.mailEdit)
    EditText mailEdit;
    @ViewById(R.id.passwordEdit)
    EditText passwordEdit;



    @AfterInject
    void create() {

    }

    @AfterViews
    void configure() {
        YoYo.with(Techniques.FadeInRight).duration(2000).playOn(findViewById(R.id.form));
        progressBar.setProgress(0);
        progressBar.setMax(100);
       /* User user = new User(0, "Brisson", "Hugo", "huguette", "hu.brisson@gmail.com", "yen a pas");
        UserDAO dao = new UserDAO(this);
        dao.insertUser(user);*/

    }

    @Click(R.id.connectButton)
    void click() {
        UserDAO userDAO = new UserDAO(this);
        User user = userDAO.getUser(mailEdit.getText().toString(), passwordEdit.getText().toString());
        if (user != null) {
            initProgressBar();
            HomeActivity.currentUser = user;

        } else {
            Crouton.makeText(this, "le mail et/ou le mot de passe sont incorrects", Style.ALERT).show();
        }
    }

    private void initProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < 100) {

                    // process some tasks
                    progressBarStatus += 1;

                    // your computer is too fast, sleep 1 second
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }

                // ok, file is downloaded,
                if (progressBarStatus >= 100) {
                    startActivity(new Intent(LoginActivity.this, HomeActivity_.class));
                    finish();
                    // close the progress bar dialog
                }
            }
        }).start();
    }
}
