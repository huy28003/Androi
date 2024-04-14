package com.example.ghichu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class dangki extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dangki);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Anhxa();
        ControlButtonn();

    }
    EditText eduser,edpassword;
    Button btndangky,btndangnhap,btnthoat;
    String ten,mk;
    private void Anhxa(){
        eduser= (EditText)findViewById(R.id.edittextuser);
        edpassword = (EditText) findViewById(R.id.edittextpassword);
        btndangky = (Button) findViewById(R.id.btndangky);
        btndangnhap = (Button) findViewById(R.id.btndangnhap);
        btnthoat = (Button) findViewById(R.id.btnthoat);
    }
    private void ControlButtonn() {
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builer = new AlertDialog.Builder(dangki.this,android.R.style.Theme_DeviceDefault_Light_Dialog);
                builer.setTitle("Bạn có chắc muốn thoát khỏi app");
                builer.setMessage("Hãy lựa chọn bên dưới để xác nhận");
                builer.setIcon(android.R.drawable.ic_dialog_alert);
                builer.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builer.setNegativeButton("không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builer.show();

            }
        });
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(dangki.this);
                dialog.setTitle("Hộp thoại xử lý");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.customdialog);
                 EditText edttk = (EditText)dialog.findViewById(R.id.edttk);
                 EditText edtmk = (EditText)dialog.findViewById(R.id.edtmk);
                Button btnhuy = (Button)dialog.findViewById(R.id.btnhuy);
                Button btndongy = (Button)dialog.findViewById(R.id.btndongy);
                btndongy.setOnClickListener(new  View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       ten = edttk.getText().toString().trim();
                       mk = edtmk.getText().toString().trim();

                       eduser.setText(ten);
                       edpassword.setText(mk);
                       dialog.cancel();

                    }
                });

                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();


            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eduser.getText().length() !=0 && edpassword.getText().length()!=0){
                    if (eduser.getText().toString().equals(ten) && edpassword.getText().toString().equals(mk)) {

                        Toast.makeText(dangki.this,"Bạn đã đang nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(dangki.this,FirstView.class);
                        startActivity(intent);

                    }else if (eduser.getText().toString().equals("giahuy")&& edpassword.getText().toString().equals("123")){
                        Toast.makeText(dangki.this,"Bạn đã đang nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(dangki.this,FirstView.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(dangki.this,"Bạn đã đang nhập thất bại ",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(dangki.this,"Mời bạn nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}