package com.imah.smarttoko.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.imah.smarttoko.R;
import com.imah.smarttoko.SplashscreenActivity;
import com.imah.smarttoko.admin.AdminActivity;
import com.imah.smarttoko.customer.ui.CustomerActivity;
import com.imah.smarttoko.session.Preferences;

public class AuthActivity extends AppCompatActivity {

    Button btnlogin;
    EditText email,password ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        btnlogin = findViewById(R.id.btn_login);
        email = findViewById(R.id.username_input);
        password = findViewById(R.id.pass);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edt_email = email.getText().toString().trim() ;
                String  edt_password = password.getText().toString().trim();

                if (!TextUtils.isEmpty(edt_email) && !TextUtils.isEmpty(edt_password)){
                    if (edt_email.equals("Penjualan") && edt_password.equals("jual")){
                        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
                        Preferences.setLoggedInStatus(getBaseContext(),true);

                        Preferences.setIsLogin(getBaseContext(),"Penjualan");
                        Intent intent = new Intent(AuthActivity.this, AdminActivity.class);
                        startActivity(intent);
                        finish();
                    }else if (edt_email.equals("Pembelian") && edt_password.equals("beli")){
                        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
                        Preferences.setLoggedInStatus(getBaseContext(),true);

                        Preferences.setIsLogin(getBaseContext(),"Pembelian");
                        Intent intent = new Intent(AuthActivity.this, CustomerActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else {
                        Snackbar.make(v, R.string.passwor_salah, Snackbar.LENGTH_LONG).show();
                    }
                }else {
                    Snackbar.make(v, R.string.kolom_kosong, Snackbar.LENGTH_LONG).show();

                }


//                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
//                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getIsLogin(getBaseContext()).equals("Penjualan")){
            Intent intent = new Intent(AuthActivity.this, AdminActivity.class);
            startActivity(intent);
            finish();
        }else if (Preferences.getIsLogin(getBaseContext()).equals("Pembelian")){
            Intent intent = new Intent(AuthActivity.this, CustomerActivity.class);
            startActivity(intent);
            finish();
        }
    }
}