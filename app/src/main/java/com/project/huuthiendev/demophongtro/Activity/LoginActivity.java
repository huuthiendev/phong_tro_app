package com.project.huuthiendev.demophongtro.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.project.huuthiendev.demophongtro.R;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText edtEmail, edtMatKhau;
    private Button btnDangNhap, btnDenDangKy, btnQuenMatKhau;
    private ProgressBar pgBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDenDangKy = (Button) findViewById(R.id.btnDenDangKy);
        btnQuenMatKhau = (Button) findViewById(R.id.btnQuenMatKhau);
        pgBar = (ProgressBar) findViewById(R.id.progressBarLogin);

        auth = FirebaseAuth.getInstance();

        btnDenDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String matkhau = edtMatKhau.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền vào địa chỉ email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(matkhau)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền vào mật khẩu!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (matkhau.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu quá ngắn, tối thiểu 6 kí tự", Toast.LENGTH_SHORT).show();
                    return;
                }

                pgBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email, matkhau).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pgBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại: email hoặc mật khẩu không chính xác, vui lòng kiểm tra lại.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });

        btnQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
