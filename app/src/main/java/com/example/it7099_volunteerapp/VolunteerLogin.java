package com.example.it7099_volunteerapp;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.it7099_volunteerapp.Helper.InputValidation;
import com.example.it7099_volunteerapp.Database.DbHelper;
import com.example.it7099_volunteerapp.Utilities.PreferenceUtilities;

public class VolunteerLogin extends AppCompatActivity implements View.OnClickListener {
    private ScrollView ScrollView;

    private final AppCompatActivity activity = VolunteerLogin.this;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatButton textViewLinkRegister;

    private InputValidation inputValidation;
    private DbHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_login);

        initViews();
        initListeners();
        initObjects();


    }

    private void initViews() {

        ScrollView = (ScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        // appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        //  textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

        appCompatButtonLogin = findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = findViewById(R.id.appCompatButtonRegister);

        PreferenceUtilities utils = new PreferenceUtilities();

        if (utils.getEmail(this) != null ){
            Intent intent = new Intent(VolunteerLogin.this, VolunteerHomepage.class);
            startActivity(intent);
        }else{

        }

    }

    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DbHelper(activity);
        inputValidation = new InputValidation(activity);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.appCompatButtonRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), VolunteerRegistration.class);
                startActivity(intentRegister);
                break;
        }
    }


    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }
        String email = textInputEditTextEmail.getText().toString().trim();
        String password = textInputEditTextPassword.getText().toString().trim();

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {
            PreferenceUtilities.saveEmail(email, this);
            PreferenceUtilities.savePassword(password, this);
            Intent HomepageIntent = new Intent(activity, VolunteerHomepage.class);
            HomepageIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(HomepageIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            //Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
            Toast.makeText(VolunteerLogin.this,getString(R.string.error_valid_email_password),Toast.LENGTH_LONG).show();

        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }


    public void GoToRegisterActivity(View view) {
        Intent intent = new Intent(VolunteerLogin.this, VolunteerRegistration.class
        );
        startActivity(intent);

    }
}
