package com.example.hoshiko.quanlythuchi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class EditorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mNoidungText;
    private EditText mSotienText;
    private CheckBox mHinhthucCheckbox;
    private RadioButton btnThu;
    private RadioButton btnChi;
    private Button mThem;
    private Button mNhaplai;
    private Button mHienthi;

    private String mNoidung;
    private String mSotien;
    private int mHinhthuc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);


        // Initialize Views
        mNoidungText = findViewById(R.id.edt_noidung);
        mSotienText = findViewById(R.id.edt_sotien);
        btnThu = findViewById(R.id.radio_thu);
        btnChi = findViewById(R.id.radio_chi);

        mThem = findViewById(R.id.button_them);
        mThem.setOnClickListener(this);

        mHienthi = findViewById(R.id.button_hienthi);
        mHienthi.setOnClickListener(this);

        mNhaplai = findViewById(R.id.button_nhaplai);
        mNhaplai.setOnClickListener(this);


    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_chi:
                if (checked) {
                    mHinhthuc = 0;
                }

                break;
            case R.id.radio_thu:
                if (checked) {
                    mHinhthuc = 1;
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_them:
                addMoneyInfo();
                break;

            case R.id.button_nhaplai:
                mNoidungText.getText().clear();
                mSotienText.getText().clear();

                break;

            case R.id.button_hienthi:
                Toast.makeText(getApplicationContext(), "Quay trở về màn hình chủ",
                        Toast.LENGTH_SHORT).show();
                onBackPressed();
        }

    }


    private void addMoneyInfo() {
        if (mNoidungText.getText().toString().length() == 0
                || mSotienText.getText().toString().length() == 0
                || (btnThu.isChecked() == false && btnChi.isChecked() == false))

            Toast.makeText(this, "Không để trống các trường", Toast.LENGTH_SHORT).show();
        else {

            DatabaseAccess databaseAccess = new DatabaseAccess(this);
            // Creating Reminder
            mNoidung = mNoidungText.getText().toString();
            mSotien = mSotienText.getText().toString();
            int ID = databaseAccess.addMoneyInfo(new MoneyInfo(mNoidung, mSotien, mHinhthuc));

            // Set up again for edit text
            mNoidungText.getText().clear();
            mSotienText.getText().clear();

            // Create toast to confirm add new thuchi successful
            Toast.makeText(this, "Thêm thành công bản ghi id " + ID, Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }
}
