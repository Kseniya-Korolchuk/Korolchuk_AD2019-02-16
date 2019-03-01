package by.korolchuk.dz2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import by.korolchuk.Dz1_Activity;
import by.korolchuk.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button1:
                startDz1_Activity();
                break;
            case R.id.button2:
                startFlagsActivity();
                break;
        }
    }

    private void startDz1_Activity() {
        Intent intent = new Intent(this, Dz1_Activity.class);
        startActivity(intent);
    }

    private void startFlagsActivity() {
        Intent intent = new Intent(this, FlagsActivity.class);
        startActivity(intent);
    }

}
