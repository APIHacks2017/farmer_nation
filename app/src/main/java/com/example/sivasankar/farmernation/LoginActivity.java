package com.example.sivasankar.farmernation;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText edtPwd, edtUser;
    TextView txtSignup;
    private Button btnLogin;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUser = (TextInputEditText) findViewById(R.id.edtUser);
        edtPwd = (TextInputEditText) findViewById(R.id.edtPwd);
        txtSignup = (TextView) findViewById(R.id.txtSignup);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        btnLogin.setOnClickListener(this);
        txtSignup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnLogin:
                LoginValidation();
                break;
            case R.id.txtSignup:
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                break;
            default:
                break;

        }
    }

    private void LoginValidation() {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        String User = edtUser.getText().toString();
        String Pwd = edtPwd.getText().toString();
        if (User.length() == 0 || User.equals(" ")) {
            edtUser.setError("Please Enter User Name");
        } else if (Pwd.length() == 0 || Pwd.equals(" ")) {
            edtPwd.setError("Please Enter Password ");
        } else if (checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please Choose Your Type", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }


    }
}
