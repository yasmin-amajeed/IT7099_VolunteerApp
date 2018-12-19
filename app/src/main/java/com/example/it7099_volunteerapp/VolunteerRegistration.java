package com.example.it7099_volunteerapp;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.it7099_volunteerapp.Database.DbHelper;
import com.example.it7099_volunteerapp.Helper.InputValidation;
import com.example.it7099_volunteerapp.JavaClasses.User;

public class VolunteerRegistration extends AppCompatActivity implements View.OnClickListener {

    private NestedScrollView nestedScrollView;
    ;
    private final AppCompatActivity activity = VolunteerRegistration.this;

    //INPUT LAYOUT
    private TextInputLayout textInputLayoutFirstName;
    private TextInputLayout textInputLayoutLastName;
    private RadioGroup radioGroupGender;
    private TextInputLayout textInputLayoutDOB;
    private TextInputLayout textInputLayoutEducation;
    private TextInputLayout textInputLayoutSkills;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    // EDIT INPUT

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextFirstName;
    private TextInputEditText textInputEditTextLastName;
    private RadioButton radioBtnGender;
    private EditText textInputEditDOB;
    private TextInputEditText textInputEditTextEducation;
    private TextInputEditText textInputEditTextSkills;
    private TextInputEditText textInputEditTextConfirmPassword;

    private Button btnRegisterVolunteer;


    private InputValidation inputValidation;
    private DbHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_registration);

        initViews();
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public void initViews() {
        textInputLayoutFirstName = (TextInputLayout) findViewById(R.id.textInputLayoutFirstName);
        textInputLayoutLastName = (TextInputLayout) findViewById(R.id.textInputLayoutLastName);

        textInputLayoutDOB = (TextInputLayout) findViewById(R.id.textInputLayoutDOB);
        textInputLayoutEducation = (TextInputLayout) findViewById(R.id.textInputLayoutEducation);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutSkills = (TextInputLayout) findViewById(R.id.textInputLayoutSkills);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextFirstName = (TextInputEditText) findViewById(R.id.textInputEditTextFirstName);
        textInputEditTextLastName = (TextInputEditText) findViewById(R.id.textInputEditTextLastName);
        textInputEditDOB = (EditText) findViewById(R.id.textInputEditDOB);
        radioBtnGender = findViewById(R.id.radioBtnFemale);
        radioBtnGender = findViewById(R.id.radioButtonMale);
        textInputEditTextEducation = (TextInputEditText) findViewById(R.id.textInputEditTextEducation);
        textInputEditTextSkills = (TextInputEditText) findViewById(R.id.textInputEditTextSkills);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        btnRegisterVolunteer = (Button) findViewById(R.id.btnRegister);


    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        btnRegisterVolunteer.setOnClickListener((View.OnClickListener) this);
        //   appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DbHelper(activity);
        user = new User();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnRegister:
                postDataToSQLite();
                break;

            //    case R.id.appCompatTextViewLoginLink:
            //      finish();
            //    break;
        }
    }


    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextFirstName, textInputLayoutFirstName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            user.setFirstName(textInputEditTextFirstName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }

    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextFirstName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}
