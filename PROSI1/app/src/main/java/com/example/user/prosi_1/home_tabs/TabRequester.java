package com.example.user.prosi_1.home_tabs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.prosi_1.Home;
import com.example.user.prosi_1.PostBarang;
import com.example.user.prosi_1.R;

/**
 * Created by user on 12/09/2018.
 */

public class TabRequester extends Fragment implements View.OnClickListener{

    private Button postReq;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_requester, container, false);
        postReq = (Button) view.findViewById(R.id.btn_post_req);
        postReq.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(postReq.getId() == view.getId()){
//            Intent intent1 = new Intent(this, PostBarang.class);
//            startActivity(intent1);
        }
    }
}
