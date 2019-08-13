//15-08-2019
//Muhammad Ilyas
//10116339
//IF -8

package com.example.datakontak.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datakontak.preferences.UserPreferences;
import com.example.datakontak.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText mViewUser, mViewPassword, mViewRepassword;
    private TextView loginses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mViewUser =findViewById(R.id.user_signup);
        mViewPassword =findViewById(R.id.password_signup);
        mViewRepassword =findViewById(R.id.confirm_signup);
        loginses = findViewById(R.id.loginses);

        loginses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mViewRepassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    cek();
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.btnregister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cek();
            }
        });
    }


    private void cek(){

        mViewUser.setError(null);
        mViewPassword.setError(null);
        mViewRepassword.setError(null);
        View fokus = null;
        boolean cancel = false;


        String repassword = mViewRepassword.getText().toString();
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();


        if (TextUtils.isEmpty(user)){
            mViewUser.setError("Username tidak boleh kosong");
            fokus = mViewUser;
            cancel = true;
        }
        else if (mViewUser.length() < 4){
            mViewUser.setError("User tidak boleh kurang dari 4 karakter");
            fokus = mViewUser;
            cancel = true;
        }
        else if(cekUser(user)){
            mViewUser.setError("Username sudah digunakan");
            fokus = mViewUser;
            cancel = true;
        }


        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("Password tidak boleh kosong");
            fokus = mViewPassword;
            cancel = true;

        }
        else if (mViewPassword.length() < 8){
            mViewPassword.setError("Password tidak boleh kurang dari 8 karakter");
            fokus = mViewPassword;
            cancel = true;
        }
        else if (!cekPassword(password,repassword)){
            mViewRepassword.setError("Password tidak sesuai");
            fokus = mViewRepassword;
            cancel = true;
        }


        if (cancel){
            fokus.requestFocus();
        }else{
            UserPreferences.setRegisteredUser(getBaseContext(),user);
            UserPreferences.setRegisteredPass(getBaseContext(),password);
            Toast.makeText(RegisterActivity.this, "Akun Berhasil Dibuat", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    private boolean cekPassword(String password, String repassword){
        return password.equals(repassword);
    }


    private boolean cekUser(String user){
        return user.equals(UserPreferences.getRegisteredUser(getBaseContext()));
    }
}
