package com.example.user.prosi_1.home_tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.prosi_1.PostBarang;
import com.example.user.prosi_1.PostTravel;
import com.example.user.prosi_1.R;

/**
 * Created by user on 12/09/2018.
 */

public class TabTraveller extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_traveller, container, false);

        Button postReq = (Button) view.findViewById(R.id.btn_post_trip);
        postReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PostTravel.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}
