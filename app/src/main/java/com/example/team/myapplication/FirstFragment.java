package com.example.juber.myapplication;


import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment  {

    TextView surname;
    EditText msgText;
    Button sendbtn;
    Cursor cursor;
    MessDatabaseHelper myDb;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        surname = v.findViewById(R.id.fstFragment_nickName_ID);
        msgText = v.findViewById(R.id.fstFragment_editmsg_ID);
        sendbtn = v.findViewById(R.id.fstFragment_sendBtn_ID);

        myDb = new MessDatabaseHelper(getActivity());

       // msgText.setOnClickListener(this);
       // sendbtn.setOnClickListener(this);

        sendMessage();
        return v;
    }
    public void sendMessage(){
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = myDb.getNumber(surname.getText().toString());
                String sms = msgText.getText().toString();

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo,null,sms,null,null);
                    Toast.makeText(getActivity(),"sms sent! to"+phoneNo,Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getActivity(),"SMS faild, please try again later", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }
        });
    }

    /*@Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.fstFragment_editmsg_ID:

                //Toast.makeText(getActivity(),"Button 1 is clicked ",Toast.LENGTH_LONG).show();
                break;
            case R.id.fstFragment_sendBtn_ID:
               // Toast.makeText(getActivity(),"Button 2 is clicked ", Toast.LENGTH_LONG).show();
                break;
        }

    }*/
}
