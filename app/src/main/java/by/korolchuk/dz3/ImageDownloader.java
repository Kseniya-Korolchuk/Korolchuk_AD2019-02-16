package by.korolchuk.dz3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import by.korolchuk.R;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;


public class ImageDownloader extends Activity implements View.OnClickListener {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_downloader);

        Button button = findViewById(R.id.downloader_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        downloadImageFromUrl();
    }

    private void downloadImageFromUrl() {

        EditText editText = findViewById(R.id.downloader_edit_text);
        String path = editText.getText().toString();
        imageView = findViewById(R.id.downloader_image_view);

        Picasso
                .get()
                .load(path)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.error_android)
                .transform(new CropCircleTransformation())
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        imageView.getLayoutParams().width = imageView.getWidth() * 5;
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }
}