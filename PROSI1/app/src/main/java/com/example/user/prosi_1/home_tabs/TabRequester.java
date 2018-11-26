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

public class TabRequester extends Fragment{

    protected Button postReq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_requester, container, false);
        this.postReq = (Button) view.findViewById(R.id.btn_post_req);
        postReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PostBarang.class);
                getActivity().startActivity(intent);
            }
        });
        return view;

    }

}
