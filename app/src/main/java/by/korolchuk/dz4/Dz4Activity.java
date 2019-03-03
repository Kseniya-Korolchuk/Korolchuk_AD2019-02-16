package by.korolchuk.dz4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import by.korolchuk.R;

public class Dz4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz4);

        Manager.getInstance().setDz4Activity(this);

    }

    public void finish(){

    }
}
