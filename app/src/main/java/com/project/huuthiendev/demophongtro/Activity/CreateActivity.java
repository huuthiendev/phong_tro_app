package com.project.huuthiendev.demophongtro.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.project.huuthiendev.demophongtro.Model.DiaChi;
import com.project.huuthiendev.demophongtro.Model.PhongTro;
import com.project.huuthiendev.demophongtro.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

public class CreateActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference mStorage;
    private ArrayList<String> filePaths;
    private ImageButton btnChonHinh;
    private TextView tvSoHinh;
    private EditText edtChiTietDiaChi, edtGiaPhong, edtDienTichPhong, edtMoTa;
    private Spinner spTinhThanh, spQuanHuyen;
    private Button btnDangTinThuePhong;
    private ArrayList<String> linkHinh;
    private String[] mangTach;
    private int Count = 0, posTinhThanh, posQuanHuyen;
    private String[] mangTinhThanh, mangHaNoi, mangHCM, mangBinhDuong, mangCanTho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        AnhXa();
        ThemMang();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mStorage = storage.getReferenceFromUrl("gs://app-phong-tro.appspot.com");
        auth = FirebaseAuth.getInstance();
        mangTach = auth.getCurrentUser().getDisplayName().split("\\|");

        btnChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filePaths.clear();
                FilePickerBuilder.getInstance().setMaxCount(5)
                        .setSelectedFiles(filePaths)
                        .setActivityTheme(R.style.AppTheme)
                        .pickPhoto(CreateActivity.this);
            }
        });

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mangTinhThanh);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spTinhThanh.setAdapter(adapter);

        spTinhThanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    posTinhThanh = position;
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(CreateActivity.this, android.R.layout.simple_spinner_item, mangHCM);
                    adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spQuanHuyen.setAdapter(adapter2);
                } else if (position == 1) {
                    posTinhThanh = position;
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(CreateActivity.this, android.R.layout.simple_spinner_item, mangHaNoi);
                    adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spQuanHuyen.setAdapter(adapter2);
                } else if (position == 2) {
                    posTinhThanh = position;
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(CreateActivity.this, android.R.layout.simple_spinner_item, mangBinhDuong);
                    adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spQuanHuyen.setAdapter(adapter2);
                } else if (position == 3) {
                    posTinhThanh = position;
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(CreateActivity.this, android.R.layout.simple_spinner_item, mangCanTho);
                    adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spQuanHuyen.setAdapter(adapter2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spQuanHuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posQuanHuyen = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnDangTinThuePhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (String path : filePaths) {
                    Uri file = Uri.fromFile(new File(path));
                    StorageReference filePath = mStorage.child("Photos").child(file.getLastPathSegment());
                    UploadTask uploadTask = filePath.putFile(file);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CreateActivity.this, "Upload hình thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            linkHinh.add(taskSnapshot.getDownloadUrl().toString());
                            Count++;
                        }
                    });
                }

                if (TextUtils.isEmpty(edtChiTietDiaChi.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ chi tiết địa chỉ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edtDienTichPhong.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền diện tích phòng", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edtGiaPhong.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền giá phòng", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edtMoTa.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền mô tả phòng", Toast.LENGTH_SHORT).show();
                    return;
                }

                LatLng latLng = RetrieveLatlangFromAddress(edtChiTietDiaChi.getText().toString() + "," + LayQuanHuyen(posTinhThanh, posQuanHuyen), CreateActivity.this);
                if (Count == filePaths.size()) {
                    mDatabase.child("PhongTro").push().setValue(new PhongTro(
                            mangTach[0],
                            latLng.latitude,
                            latLng.longitude,
                            new DiaChi(mangTinhThanh[posTinhThanh], LayQuanHuyen(posTinhThanh, posQuanHuyen), edtChiTietDiaChi.getText().toString()),
                            Integer.parseInt(edtDienTichPhong.getText().toString()), Double.parseDouble(edtGiaPhong.getText().toString()), mangTach[1],
                            edtMoTa.getText().toString(), new Date(System.currentTimeMillis()), linkHinh))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(CreateActivity.this, "Đăng tin thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    private LatLng RetrieveLatlangFromAddress(String myAddress, Context context){
        LatLng latLng=null;

        Geocoder gc=new Geocoder(context);
        List<Address> addressList=null;
        if(gc.isPresent()){
            try {
                addressList=gc.getFromLocationName(myAddress,1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address=addressList.get(0);
            latLng=new LatLng(address.getLatitude(),address.getLongitude());
        }
        return latLng;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE_PHOTO:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    filePaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS));
                    tvSoHinh.setText("Số hình bạn đã chọn: " + filePaths.size());
                }
        }
    }

    private void AnhXa() {
        filePaths = new ArrayList<>();
        linkHinh = new ArrayList<>();

        tvSoHinh = (TextView) findViewById(R.id.tvSoHinh);
        btnDangTinThuePhong = (Button) findViewById(R.id.btnDangTinThuePhong);
        btnChonHinh = (ImageButton) findViewById(R.id.btnChonHinh);
        spTinhThanh = (Spinner) findViewById(R.id.spTinhThanh);
        spQuanHuyen = (Spinner) findViewById(R.id.spQuanHuyen);
        edtChiTietDiaChi = (EditText) findViewById(R.id.edtChiTietDiaChi);
        edtGiaPhong = (EditText) findViewById(R.id.edtGiaPhong);
        edtDienTichPhong = (EditText) findViewById(R.id.edtDienTichPhong);
        edtMoTa = (EditText) findViewById(R.id.edtMoTa);
    }

    private void ThemMang() {
        mangTinhThanh = new String[]{"Hồ Chí Minh", "Hà Nội", "Bình Dương", "Cần Thơ"};
        mangHaNoi = new String[]{"Ba Đình", "Hoàn Kiếm", "Hai Bà Trưng", "Đống Đa", "Tây Hồ", "Cầu Giấy", "Thanh Xuân", "Hoàng Mai", "Long Biên", "Từ Liêm",
            "Thanh Trì", "Gia Lâm", "Đông Anh", "Sóc Sơn"};
        mangHCM = new String[]{"Quận 1", "Quận 12", "Quận Thủ Đức", "Quận 9", "Quận Gò Vấp", "Quận Bình Thạnh", "Quận Tân Bình", "Quận Tân Phú",
            "Quận Phú Nhuận", "Quận 2", "Quận 3", "Quận 10", "Quận 11", "Quận 4", "Quận 5", "Quận 6", "Quận 8", "Quận Bình Tân", "Quận 7", "Huyện Củ Chi",
            "Huyện Hóc Môn", "Huyện Bình Chánh", "Huyện Nhà Bè", "Huyện Cần Giờ"};
        mangCanTho = new String[]{"Quận Ninh Kiều", "Quận Bình Thuỷ", "Quận Cái Răng", "Quận Ô Môn", "Huyện Phong Điền", "Huyện Cờ Đỏ", "Huyện Vĩnh Thạnh",
            "Huyện Thốt Nốt"};
        mangBinhDuong = new String[]{"Thị xã Thủ Dầu Một", "Huyện Bến Cát", "Huyện Tân Uyên", "Huyện Thuận An", "Huyện Dĩ An", "Huyện Phú Giáo", "Huyện Dầu Tiếng"};
    }

    private String LayQuanHuyen(int posTinhThanh, int posQuanHuyen) {
        if (posTinhThanh == 0) {
            return mangHCM[posQuanHuyen];
        } else if (posTinhThanh == 1) {
            return mangHaNoi[posQuanHuyen];
        } else if (posTinhThanh == 2) {
            return mangBinhDuong[posQuanHuyen];
        } else if (posTinhThanh == 3) {
            return mangCanTho[posQuanHuyen];
        }
        return null;
    }
}
