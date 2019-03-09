package by.korolchuk.dz4;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import by.korolchuk.R;

public class OwlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owl);

        ImageView owlImageView = findViewById(R.id.owlImageView);
        owlImageView.setBackgroundResource(R.drawable.owlanimation);

        AnimationDrawable mAnimationDrawable = (AnimationDrawable) owlImageView.getBackground();
        mAnimationDrawable.start();

        Button button = findViewById(R.id.transition_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwlActivity.this, Dz4Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}