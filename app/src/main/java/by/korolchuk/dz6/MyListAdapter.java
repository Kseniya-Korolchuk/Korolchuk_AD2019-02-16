package by.korolchuk.dz6;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import by.korolchuk.R;

public class MyListAdapter extends RecyclerView.Adapter<MyListViewHolder> {


    private List<Student> list = new ArrayList<>();

    /*public void setListener(AdapterView.OnItemClickListener) {

    }*/

    public void setList(List<Student> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_my_list, viewGroup, false);

       final MyListViewHolder holder = new MyListViewHolder(view);
       /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if(listener != null) {
                    listener.onClick(list.get(position), position);
                }
            }
        });*/
        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListViewHolder holder, int position) {
        holder.bind(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

  /* public interface OnItemClickListener{

   }*/
}
