package com.example.juber.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAmmount extends AppCompatActivity{

    MessDatabaseHelper myDb;
    private EditText editSurname,editAmmount;
    private Button updatebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ammount);
        myDb = new MessDatabaseHelper(this);

        editSurname = findViewById(R.id.update_surname_id);
        editAmmount = findViewById(R.id.update_ammount_id);
        updatebtn = findViewById(R.id.update_btn_id);

        UpdateData();
    }
    public void UpdateData(){
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = myDb.updateData(editSurname.getText().toString(),
                        editAmmount.getText().toString());
                if(isUpdated == true){
                    Toast.makeText(UpdateAmmount.this,"Data Updated",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(UpdateAmmount.this,"Data Not Updated",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

}
