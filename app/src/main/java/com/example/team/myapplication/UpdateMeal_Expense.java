package com.example.juber.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class UpdateMeal_Expense extends AppCompatActivity {
    LinearLayout mlLyt,expLyt;
    EditText surname,meal,expense;
    Button next,submit;
    int let=0,todaymeal=0;
    long cnt;
    MessDatabaseHelper myDb;
    String surnameStr,mealStr,expenseStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_meal__expense);

        myDb = new MessDatabaseHelper(this);

        surname = findViewById(R.id.update_meal_surname);
        meal = findViewById(R.id.mealID);
        expense = findViewById(R.id.expense);
        next = findViewById(R.id.nextBtn);
        submit = findViewById(R.id.submitBtn);
        mlLyt = findViewById(R.id.mealLayout);
        expLyt = findViewById(R.id.expnseLayout);

        cnt = myDb.getCount();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surnameStr = surname.getText().toString();
                mealStr = meal.getText().toString();
                if(!TextUtils.isEmpty(surnameStr) && !TextUtils.isEmpty(mealStr)) {
                    surname.setText(null);
                    meal.setText(null);
                    let++;
                    int m = Integer.parseInt(mealStr);
                    todaymeal=todaymeal+m;
                    myDb.updateMeal(surnameStr,m);
                    if (let == cnt) {
                        mlLyt.setVisibility(View.GONE);
                        expLyt.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expenseStr= expense.getText().toString();
                if(!TextUtils.isEmpty(expenseStr))
                {
                    double d = Double.parseDouble(expenseStr);
                    myDb.updateExpense(d);
                    myDb.updateAllMeal(todaymeal);
                    finish();
                }
            }
        });

    }
}
