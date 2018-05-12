package com.an.constraintlayout.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.an.constraintlayout.R;
import com.an.constraintlayout.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MainActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.btnWithConstraint.setOnClickListener(this);
        binding.btnWithoutConstraint.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == binding.btnWithConstraint) {
            startActivity(new Intent(this, DetailWithConstraintActivity.class));
        } else if(view == binding.btnWithoutConstraint) {
            startActivity(new Intent(this, DetailWithoutConstraintActivity.class));
        }
    }
}
