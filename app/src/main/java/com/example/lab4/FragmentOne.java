package com.example.lab4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FragmentOne extends Fragment {

    private TextView tv;
    private EditText etName;
    private Button btnHello;
    private SharedViewModel viewModel;

    public FragmentOne() {
        super(R.layout.fragment_one);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        tv = view.findViewById(R.id.textOne);
        etName = view.findViewById(R.id.etName);
        btnHello = view.findViewById(R.id.btnHello);

        etName.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setUserName(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        btnHello.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            tv.setText(name.isEmpty() ? "Bonjour !" : "Bonjour " + name + " !");
        });
    }
}