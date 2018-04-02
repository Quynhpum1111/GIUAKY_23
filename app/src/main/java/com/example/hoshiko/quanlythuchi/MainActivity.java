package com.example.hoshiko.quanlythuchi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseAccess db;
    private ArrayList<MoneyInfo> moneyInfoArrayList;
    private TextView mNorecordTextView;
    private ListView listView;
    private MoneyInfoAdapter reminderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize reminder database
        db = new DatabaseAccess(getApplicationContext());
        mNorecordTextView = findViewById(R.id.no_record_text);
        listView = findViewById(R.id.thu_chi_list);



        // To check is there are saved reminders
        // If there are no reminders display a message asking the user to create reminders
        moneyInfoArrayList = db.getAllRecords();
        reminderAdapter = new MoneyInfoAdapter(this, moneyInfoArrayList );
        listView.setAdapter(reminderAdapter);

    }

    // Luôn cập nhật lại CSDL khi có bản ghi mới được thêm vào từ Editor Activity
    @Override
    public void onResume(){
        super.onResume();

        // To check is there are saved reminders
        // If there are no reminders display a message asking the user to create reminders
        moneyInfoArrayList = db.getAllRecords();

        if (moneyInfoArrayList.isEmpty()) {
            mNorecordTextView.setVisibility(View.VISIBLE);
        } else {
            mNorecordTextView.setVisibility(View.GONE);
        }

        reminderAdapter = new MoneyInfoAdapter(this, moneyInfoArrayList );
        listView.setAdapter(reminderAdapter);
    }


    // Thiết lập Menu option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to a click on the "Thêm mới" menu option
            case R.id.action_add:
                Intent intent = new Intent(this, EditorActivity.class);
                startActivity(intent);
                return true;
            // Respond to a click on the "Lưu" menu option
            case R.id.action_save:
                //Đoạn này tức là lưu dữ liệu vào database nhưng bên editor đã lưu rồi nên h éo biết lưu gì nữa
                // cô muốn thì sửa lại :3
                Toast.makeText(this, "Đã lưu thành công!", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_quit:
                finish();
                System.exit(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



