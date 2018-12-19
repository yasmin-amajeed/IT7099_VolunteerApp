package com.example.it7099_volunteerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button volunteerButton = (Button) findViewById(R.id.volunteerPortalBtn);
        Button OrgButton = findViewById(R.id.ButtonLoginOrganization);

        OrgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, OrganizationLogin.class);
                startActivity(intent2);

            }
        });


        volunteerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VolunteerLogin.class
                );
                startActivity(intent);
            }
        });




    }
}
