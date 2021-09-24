package com.example.juber.myapplication;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MemberList extends AppCompatActivity {

    private ArrayList<String> results = new ArrayList<String>();
    private String tableName = MessDatabaseHelper.TABLE_NAME;
    private SQLiteDatabase newDB;
    Cursor cursor;
    ListView list;
    MessDatabaseHelper dbHelper;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        list = findViewById(R.id.myList);
        dbHelper = new MessDatabaseHelper(this);
       /* AppCompatCallback callback = new AppCompatCallback() {
            @Override
            public void onSupportActionModeStarted(ActionMode actionMode) {
            }

            @Override
            public void onSupportActionModeFinished(ActionMode actionMode) {
            }

            @Nullable
            @Override
            public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
                return null;
            }
        };

        AppCompatDelegate delegate = AppCompatDelegate.create(this,callback);

        delegate.onCreate(savedInstanceState);
        delegate.setContentView(R.layout.activity_member_list);

        Toolbar toolbar= (Toolbar) findViewById(R.id.my_awesome_toolbar);
        delegate.setSupportActionBar(toolbar);*/
        openAndQueryDatabase();

       // displayResultList();

    }
    private void displayResultList() {
      /*  TextView tView = new TextView(this);
        tView.setText("ALL MEMBERS NAME");
        getListView().addHeaderView(tView);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        getListView().setTextFilterEnabled(true);*/

    }
    private void openAndQueryDatabase() {
        try {
            MessDatabaseHelper dbHelper = new MessDatabaseHelper(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT NAME, DEPOSITE FROM " +
                    tableName, null);

            if (c != null ) {
                if (c.moveToFirst()) {
                    do {
                        String firstName = c.getString(c.getColumnIndex("NAME"));
                        int amount = c.getInt(c.getColumnIndex("DEPOSITE"));
                        results.add("Name: " + firstName+ " |   Taka:"+amount);
                    }while (c.moveToNext());
                    setAdapter();
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }/*finally {
            if (newDB != null)
                newDB.execSQL("DELETE FROM " + tableName);
            newDB.close();
        }*/

    }

    private void setAdapter() {
        list.setAdapter((new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
              //  Log.d(TAG, "onQueryTextSubmit ");

                cursor=dbHelper.getAllData(s);
                if (cursor==null){
                    Toast.makeText(MemberList.this,"No records found!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MemberList.this, cursor.getCount() + " records found!",Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //Log.d(TAG, "onQueryTextChange ");
                cursor=dbHelper.getAllData(s);
                results.clear();
                if (cursor != null ) {
                    if (cursor.moveToFirst()) {
                        do {
                            String firstName = cursor.getString(cursor.getColumnIndex("NAME"));
                            int amount = cursor.getInt(cursor.getColumnIndex("DEPOSITE"));
                            results.add("Name: " + firstName+ " |   Taka:"+amount);
                        }while (cursor.moveToNext());
                        setAdapter();
                    }
                }
                return false;
            }

        });

        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.search:
                break;
        }
        return true;
    }

}
