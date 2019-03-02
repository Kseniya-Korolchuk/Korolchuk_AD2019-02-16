package by.korolchuk;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Dz1Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz1);

        final Button dz1InvertButton = findViewById(R.id.Dz1_Invert_Button);
        dz1InvertButton.setOnClickListener(this);
        TextView dzTV1 = findViewById(R.id.Dz1_TV_1);
        TextView dzTV2 = findViewById(R.id.Dz1_TV_2);

        dzTV1.setText(R.string.textview_1);
        dzTV2.setText(R.string.textview_2);


        dzTV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inversion();
            }
        });


        View.OnClickListener textView2ClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inversion();
            }
        };
        dzTV2.setOnClickListener(textView2ClickListener);
    }


    @Override
    public void onClick(View v) {
        inversion();
    }


    public void inversion() {
        TextView dzTV1 = findViewById(R.id.Dz1_TV_1);
        TextView dzTV2 = findViewById(R.id.Dz1_TV_2);

        String dzTV1Text = dzTV1.getText().toString();
        String dzTV2Text = dzTV2.getText().toString();

        Drawable color1 = dzTV1.getBackground();
        Drawable color2 = dzTV2.getBackground();

        dzTV1.setBackground(color2);
        dzTV2.setBackground(color1);
        dzTV1.setText((dzTV2Text));
        dzTV2.setText(dzTV1Text);
    }
}
