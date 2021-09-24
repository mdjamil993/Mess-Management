package com.example.juber.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class MainFragment extends Fragment implements View.OnClickListener{
    private ImageView imgaddData,imgmemberlist,imgdepositeUpdt,imgupdateMeal,details;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View fragmenLayour = inflater.inflate(R.layout.fragment_main, container, false);
        imgaddData = fragmenLayour.findViewById(R.id.mainfgm_addmember_id);
        imgmemberlist = fragmenLayour.findViewById(R.id.mainfgm_memberlist_id);
        imgdepositeUpdt =fragmenLayour.findViewById(R.id.mainfgm_depositeUpdt_id);
        imgupdateMeal =fragmenLayour.findViewById(R.id.mainfgm_addmeal_id);
        details =fragmenLayour.findViewById(R.id.frgm_details_id);
        imgaddData.setOnClickListener(this);
        imgmemberlist.setOnClickListener(this);
        imgdepositeUpdt.setOnClickListener(this);
        imgupdateMeal.setOnClickListener(this);
        details.setOnClickListener(this);
        return fragmenLayour;

    }

    @Override
    public void onClick(View view) {

        Intent addDataIntent, memberListIntent,depositeIntent,mealIntent;
        if(view.getId() == R.id.mainfgm_addmember_id) {

            addDataIntent = new Intent(this.getActivity(), AddData.class);
            startActivity(addDataIntent);
        }else if(view.getId() == R.id.mainfgm_memberlist_id) {


            memberListIntent = new Intent(this.getActivity(), MemberList.class);
            startActivity(memberListIntent);
        }else if(view.getId() == R.id.mainfgm_depositeUpdt_id){
            depositeIntent = new Intent(this.getActivity(),UpdateAmmount.class);
            startActivity(depositeIntent);
        }else if(view.getId() == R.id.mainfgm_addmeal_id){
            mealIntent = new Intent(this.getActivity(),UpdateMeal_Expense.class);
            startActivity(mealIntent);
        }
        else if(view.getId() == R.id.frgm_details_id){
            mealIntent = new Intent(this.getActivity(),DetailsActivity.class);
            startActivity(mealIntent);
        }
    }
}
