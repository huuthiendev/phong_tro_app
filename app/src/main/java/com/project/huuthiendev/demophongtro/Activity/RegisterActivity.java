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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.project.huuthiendev.demophongtro.R;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText edtEmailDK, edtMatKhauDK, edtHoTenDK, edtSDTDK;
    private Button btnDangKy;
    private ProgressBar pgBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtEmailDK = (EditText) findViewById(R.id.edtEmailDK);
        edtMatKhauDK = (EditText) findViewById(R.id.edtMatKhauDK);
        edtHoTenDK = (EditText) findViewById(R.id.edtHoTenDK);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        edtSDTDK = (EditText) findViewById(R.id.edtSDTDK);
        pgBar = (ProgressBar) findViewById(R.id.progressBarRegister);

        auth = FirebaseAuth.getInstance();

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String email = edtEmailDK.getText().toString().trim();
            String matkhau = edtMatKhauDK.getText().toString().trim();

            if (TextUtils.isEmpty(edtHoTenDK.getText().toString().trim())) {
                Toast.makeText(getApplicationContext(), "Vui lòng điền vào họ và tên!!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Vui lòng điền vào địa chỉ email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(matkhau)) {
                Toast.makeText(getApplicationContext(), "Vui lòng điền vào mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }

            if (matkhau.length() < 6) {
                Toast.makeText(getApplicationContext(), "Mật khẩu quá ngắn, tối thiểu 6 kí tự", Toast.LENGTH_SHORT).show();
                return;
            }

            pgBar.setVisibility(View.VISIBLE);

            auth.createUserWithEmailAndPassword(email, matkhau).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(
                                edtHoTenDK.getText().toString().trim() + "|" + edtSDTDK.getText().toString().trim()).build();
                        task.getResult().getUser().updateProfile(profileUpdates);

                        Toast.makeText(RegisterActivity.this, "Chúc mừng bạn đã đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                        pgBar.setVisibility(View.GONE);
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Đăng ký thất bại" + task.getException(), Toast.LENGTH_SHORT).show();
                        pgBar.setVisibility(View.GONE);
                    }
                }
            });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pgBar.setVisibility(View.GONE);
    }
}
