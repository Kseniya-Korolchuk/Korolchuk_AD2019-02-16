package by.korolchuk.dz4;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import by.korolchuk.R;

public class OwlActivity extends AppCompatActivity {

    private AnimationDrawable mAnimationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owl);

        ImageView owlImageView = (ImageView) findViewById(R.id.owlImageView);
        owlImageView.setBackgroundResource(R.drawable.owlanimation);

        mAnimationDrawable = (AnimationDrawable) owlImageView.getBackground();

        mAnimationDrawable.start();
    }
}