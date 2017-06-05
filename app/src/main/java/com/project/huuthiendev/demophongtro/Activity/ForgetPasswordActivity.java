package com.project.huuthiendev.demophongtro.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.project.huuthiendev.demophongtro.R;

public class ForgetPasswordActivity extends AppCompatActivity {
    private Button btnLayLaiMatKhau;
    private EditText edtEmail;
    private FirebaseAuth auth;
    private ProgressBar pgBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        auth = FirebaseAuth.getInstance();

        btnLayLaiMatKhau = (Button) findViewById(R.id.btnLayLaiMatKhau);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        pgBar = (ProgressBar) findViewById(R.id.progressBarLogin);

        btnLayLaiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgBar.setVisibility(View.VISIBLE);

                auth.sendPasswordResetEmail(edtEmail.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pgBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgetPasswordActivity.this, "Email đã được gửi vui lòng kiểm tra hộp thư.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgetPasswordActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ForgetPasswordActivity.this, "Email không hợp lệ.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
