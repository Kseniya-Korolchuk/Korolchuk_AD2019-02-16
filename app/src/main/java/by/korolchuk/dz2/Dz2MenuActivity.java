package by.korolchuk.dz2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import by.korolchuk.R;

public class Dz2MenuActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz2_menu);
        final Button dz2MenuButton1 = findViewById(R.id.dz2Button1);
        final Button dz2MenuButton2 = findViewById(R.id.dz2Button2);

        dz2MenuButton1.setOnClickListener(this);
        dz2MenuButton2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.dz2Button1: startFlagsActivity();
        }
    }

    private void startFlagsActivity() {
        Intent intent = new Intent(this, FlagsActivity.class);
        startActivity(intent);
    }
}
