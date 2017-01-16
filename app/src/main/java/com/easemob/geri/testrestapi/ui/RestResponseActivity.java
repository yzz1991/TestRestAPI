package com.easemob.geri.testrestapi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.easemob.geri.testrestapi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Geri on 2017/1/16.
 */

public class RestResponseActivity extends AppCompatActivity {

    @BindView(R.id.tv_response)
    TextView mResponse;
    @BindView(R.id.back)
    TextView mBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        ButterKnife.bind(this);

        String result = getIntent().getStringExtra("result");
        mResponse.setText(result);
        mResponse.setMovementMethod(ScrollingMovementMethod.getInstance());
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
