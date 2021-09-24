package com.example.juber.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    EditText name;
    Button cnfm;
    TextView show;
    LinearLayout lyt;
    String surname;
    MessDatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        myDb = new MessDatabaseHelper(this);

        name = findViewById(R.id.details_surname_id);
        cnfm = findViewById(R.id.details_btn_id);
        show = findViewById(R.id.showText);
        lyt = findViewById(R.id.details_linLyt);

        lyt.setVisibility(View.GONE);

        cnfm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surname=name.getText().toString();

                showdetails();
            }
        });
    }

    private void showdetails() {
        int meal=myDb.getMeal(surname);
        int totalmeal = myDb.getAllMeal(1);
        double totalexpense= myDb.getExpense(1);

        double permealrate = totalexpense/totalmeal ;
        double expense = permealrate * meal;

        int deposit = myDb.getDeposite(surname);

        String name= myDb.getName(surname);
        StringBuilder s=new StringBuilder();
        s.append("Name : "+name+"\nTotal meal : "+meal+"\nTotal Amount: "+deposit+"\nExpense : "+expense);

        if(deposit>=expense)
        {
            s.append("\n\nYou will get "+(deposit-expense)+" taka");
        }
        else
        {
            s.append("\n\nYou need to give "+(expense-deposit)+" taka");
        }
        lyt.setVisibility(View.VISIBLE);
        show.setText(s.toString());
    }
}
