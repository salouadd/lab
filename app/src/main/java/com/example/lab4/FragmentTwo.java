package com.example.lab4;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.materialswitch.MaterialSwitch;

public class FragmentTwo extends Fragment {

    private TextView tvValue;
    private TextView tvUserGreeting;
    private SeekBar seek;
    private MaterialSwitch switchStatus;
    private int progress = 0;
    private static final String KEY_PROGRESS = "progress";
    private SharedViewModel viewModel;

    public FragmentTwo() {
        super(R.layout.fragment_two);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        tvValue = view.findViewById(R.id.tvValue);
        tvUserGreeting = view.findViewById(R.id.tvUserGreeting);
        seek = view.findViewById(R.id.seekBar);
        switchStatus = view.findViewById(R.id.switchStatus);

        // Observer le changement de nom depuis Fragment 1
        viewModel.getUserName().observe(getViewLifecycleOwner(), name -> {
            if (name != null && !name.isEmpty()) {
                tvUserGreeting.setText("Ravi de vous revoir, " + name + " !");
                tvUserGreeting.setVisibility(View.VISIBLE);
            } else {
                tvUserGreeting.setVisibility(View.GONE);
            }
        });

        if (savedInstanceState != null) {
            progress = savedInstanceState.getInt(KEY_PROGRESS, 0);
            seek.setProgress(progress);
            tvValue.setText("Valeur : " + progress);
        }

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar s, int p, boolean fromUser) {
                progress = p;
                tvValue.setText("Valeur : " + p);
            }
            @Override public void onStartTrackingTouch(SeekBar s) {}
            @Override public void onStopTrackingTouch(SeekBar s) {}
        });

        switchStatus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Option activée" : "Option désactivée";
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PROGRESS, progress);
    }
}