package by.korolchuk.dz6;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import by.korolchuk.R;

public class MyListViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textView;

    public MyListViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.item_list_image_view);
        textView = itemView.findViewById(R.id.item_list_text_view);

    }

    public void bind(Student item, int position) {
        if (!TextUtils.isEmpty((item.getUrl()))) {
            ImageLoaderUtil.loadImage(imageView, item.getUrl());
        }
        else {
            imageView.setImageDrawable(null);
        }
        if(item.getName() != null) {
            textView.setText(item.getName());
        }
        else {
            textView.setText("");
        }
    }

}
