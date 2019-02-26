package by.korolchuk.dz2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
            case  R.id.button2: startDz2MenuActivity();
        }

    }

   private void startDz2MenuActivity(){
        Intent intent = new Intent(this, Dz2MenuActivity.class);
        startActivity(intent);
    }
}
