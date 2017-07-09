package com.pervy_sage.travvy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pervy_sage.travvy.models.CurrLoactionParams;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrLoactionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrLoactionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ADRESSLINE = "AdressLine";
    private static final String ARG_CITY = "City";
    private static final String ARG_POSTALCODE = "PostalCode";
    private static final String ARG_COUNTRY = "Country";


    // TODO: Rename and change types of parameters
    private String mAddressLine;
    private String mCity;
    private String mPostralCode;
    private String mCountry;

    private TextView tvCurrAddressline,tvCurrCity,tvCurrPostalCode,tvCurrCountry;

    public CurrLoactionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment CurrLoactionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrLoactionFragment newInstance(CurrLoactionParams currLoactionParams) {
        CurrLoactionFragment fragment = new CurrLoactionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ADRESSLINE, currLoactionParams.getAddressLine());
        args.putString(ARG_CITY, currLoactionParams.getCity());
        args.putString(ARG_POSTALCODE,currLoactionParams.getPostalCode());
        args.putString(ARG_COUNTRY,currLoactionParams.getCountry());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAddressLine = getArguments().getString(ARG_ADRESSLINE);
            mCity = getArguments().getString(ARG_CITY);
            mPostralCode=getArguments().getString(ARG_POSTALCODE);
            mCountry=getArguments().getString(ARG_COUNTRY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View itemView= inflater.inflate(R.layout.fragment_curr_loaction, container, false);
        tvCurrAddressline=(TextView)itemView.findViewById(R.id.tvCurrAdressLine);
        tvCurrCity=(TextView)itemView.findViewById(R.id.tvCurrCity);
        tvCurrPostalCode=(TextView)itemView.findViewById(R.id.tvCurrPostalCode);
        tvCurrCountry=(TextView)itemView.findViewById(R.id.tvCurrCountry);

        tvCurrAddressline.setText(mAddressLine);
        tvCurrCity.setText(mCity);
        tvCurrPostalCode.setText(mPostralCode);
        tvCurrCountry.setText(mCountry);

        return  itemView;
    }


}
