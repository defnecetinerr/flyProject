package com.daphne.flyclassac;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentBirinci extends Fragment {

    private EditText editTextDate1;
    private EditText editTextNumber1;
    private Button satis1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_first_go,container,false);
        satis1 = rootView.findViewById(R.id.satis2);
        editTextDate1 = rootView.findViewById(R.id.editTextDate2);
        editTextNumber1 = rootView.findViewById(R.id.editTextNumber2);


        satis1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"SATIŞ BAŞARIYLA TAMAMLANDI.",Toast.LENGTH_LONG).show();
            }
        });


        return rootView;





    }
}
