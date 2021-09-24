package com.example.juber.myapplication;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddData extends AppCompatActivity {


    MessDatabaseHelper myDb;

    EditText editName,editSurname,editMarks,editID;
    Button btnAddData,btnviewAll,btnUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        myDb = new MessDatabaseHelper(this);

        editName = findViewById(R.id.editName_ID);
        editSurname = findViewById(R.id.editSurName_ID);
        editMarks = findViewById(R.id.editMarks_ID);
        editID = findViewById(R.id.editId_ID);


        btnAddData = findViewById(R.id.button_ID);
     /*   btnviewAll = findViewById(R.id.AllViewButton_ID);
        btnUpdate = findViewById(R.id.UpdateButton_ID);
        btnDelete = findViewById(R.id.DeleteButton_ID);*/
        //-----------------Methods call-------------------------
        // ----------------------------------------------------
        AddData();
     //   ViewAll();
       // UpdateData();
        //DeleteData();
    }
   /* public void DeleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = myDb.deleteData(editID.getText().toString());
                if(deletedRows > 0)
                    Toast.makeText(AddData.this,"Data Deleted",Toast.LENGTH_LONG).show();

                else
                    Toast.makeText(AddData.this,"Data not Deleted",Toast.LENGTH_LONG).show();
            }

        });
    }*/
    /*public void UpdateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = myDb.updateData(editID.getText().toString(),
                        editName.getText().toString(),editSurname.getText().toString(),
                        editMarks.getText().toString());
                if(isUpdated == true){
                    Toast.makeText(AddData.this,"Data Updated",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(AddData.this,"Data Not Updated",Toast.LENGTH_LONG).show();
            }
        });
    }*/

    public void AddData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=myDb.insertData(editID.getText().toString(),editName.getText().toString(),
                        editSurname.getText().toString(),
                        editMarks.getText().toString());

                Toast.makeText(AddData.this,"Data Inserted "+a,Toast.LENGTH_LONG).show();
                finish();
            }

        });
    }

/*    public void ViewAll(){
        btnviewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0){
                    //show message
                    showMessage("Error","No data found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id:"+res.getString(0)+"\n");
                    buffer.append("Name:"+res.getString(1)+"\n");
                    buffer.append("Surname:"+res.getString(2)+"\n");
                    buffer.append("Marks:"+res.getString(3)+"\n\n");

                }
                //show all data
                showMessage("Data",buffer.toString());
            }
        });
    }*/
    public void showMessage(String title ,String Meassage){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Meassage);
        builder.show();
    }

}

