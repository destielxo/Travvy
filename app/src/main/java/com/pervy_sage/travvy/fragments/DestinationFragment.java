package com.pervy_sage.travvy.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pervy_sage.travvy.DestinationActivity;
import com.pervy_sage.travvy.DestinationLatLong;
import com.pervy_sage.travvy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DestinationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DestinationFragment extends Fragment  implements View.OnClickListener{
    TextView tvSK,tvJPN,tvFRA,tvMAL,tvMLD,tvSWL,tvMKN;

    public DestinationFragment() {
        // Required empty public constructor
    }

    public static DestinationFragment newInstance() {
        DestinationFragment fragment = new DestinationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_destination, container, false);
        tvFRA=(TextView)view.findViewById(R.id.tvFRA);
        tvJPN=(TextView)view.findViewById(R.id.tvJPN);
        tvSK=(TextView)view.findViewById(R.id.tvSK);
        tvMAL=(TextView)view.findViewById(R.id.tvMAL);
        tvMLD=(TextView)view.findViewById(R.id.tvMLD);
        tvSWL=(TextView)view.findViewById(R.id.tvSWL);
        tvMKN=(TextView)view.findViewById(R.id.tvMKN);

        tvMKN.setOnClickListener(this);
        tvJPN.setOnClickListener(this);
        tvSK.setOnClickListener(this);
        tvSWL.setOnClickListener(this);
        tvMAL.setOnClickListener(this);
        tvMLD.setOnClickListener(this);
        tvFRA.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getContext(), DestinationActivity.class);
        switch (v.getId()){
            case R.id.tvFRA:
                i.putExtra("DestPlace", DestinationLatLong.FRANCE);
                break;
            case R.id.tvJPN:
                i.putExtra("DestPlace", DestinationLatLong.JAPAN);
                break;
            case R.id.tvSK:
                i.putExtra("DestPlace", DestinationLatLong.SKOREA);
                break;
            case R.id.tvSWL:
                i.putExtra("DestPlace", DestinationLatLong.SWITZERLAND);
                break;
            case R.id.tvMAL:
                i.putExtra("DestPlace", DestinationLatLong.MALAYASIA);
                break;
            case R.id.tvMLD:
                i.putExtra("DestPlace", DestinationLatLong.MALDIVES);
                break;
            case R.id.tvMKN:
                i.putExtra("DestPlace", DestinationLatLong.MYKONOS);
                break;
        }
        startActivity(i);
        

    }
}
