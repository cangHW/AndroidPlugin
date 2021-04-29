package com.chx.androidplugin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.proxy.service.api.plugin.DataByPlugin;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        if (view instanceof TextView) {
            List<String> list = DataByPlugin.getInstance().getClasses(new ArrayList<String>());
            if (list.size() > 0) {
                ((TextView) view).setText(list.get(0));
            } else {
                ((TextView) view).setText("null");
            }
        }

    }

}

