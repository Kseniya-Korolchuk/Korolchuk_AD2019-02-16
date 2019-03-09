package by.korolchuk.dz6;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import by.korolchuk.R;

public class Dz6Activity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String NAME_EXTRA = "NAME_EXTRA";

   @Override
    protected void onPause() {
        super.onPause();
        sharedPreferences.edit().putLong((NAME_EXTRA),
                System.currentTimeMillis()).apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        long date = sharedPreferences.getLong((NAME_EXTRA), 0);
        Toast.makeText((Dz6Activity.this), String.valueOf(date),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz6);

        final RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        //sharedPreferences = getSharedPreferences()

        //recyclerView.addItemDecoration(); ОТСТУПЫ

        List<Student> list = new ArrayList<>();
        list.add(new Student("Vanya", "https://pngimage.net/wp-content/uploads/2018/06/gambar-user-png-2.png"));
        list.add(new Student("Vova", "https://pngimage.net/wp-content/uploads/2018/06/gambar-user-png-2.png"));
        list.add(new Student("Katya", "https://alomgyar.hu/assets/images/authors/noimage.png"));
        list.add(new Student("Petya", "https://pngimage.net/wp-content/uploads/2018/06/gambar-user-png-2.png"));
        list.add(new Student("Anya", "https://alomgyar.hu/assets/images/authors/noimage.png"));
        list.add(new Student("Masha", "https://alomgyar.hu/assets/images/authors/noimage.png"));
        list.add(new Student("Dima", "https://pngimage.net/wp-content/uploads/2018/06/gambar-user-png-2.png"));
        list.add(new Student("Vanya", "https://pngimage.net/wp-content/uploads/2018/06/gambar-user-png-2.png"));
        list.add(new Student("Vova", "https://pngimage.net/wp-content/uploads/2018/06/gambar-user-png-2.png"));
        list.add(new Student("Katya", "https://alomgyar.hu/assets/images/authors/noimage.png"));
        list.add(new Student("Petya", "https://pngimage.net/wp-content/uploads/2018/06/gambar-user-png-2.png"));
        list.add(new Student("Anya", "https://alomgyar.hu/assets/images/authors/noimage.png"));
        list.add(new Student("Masha", "https://alomgyar.hu/assets/images/authors/noimage.png"));
        list.add(new Student("Dima", "https://pngimage.net/wp-content/uploads/2018/06/gambar-user-png-2.png"));

        MyListAdapter adapter = new MyListAdapter();
        adapter.setList(list);


        /*adapter.setListener(new MyListAdapter().onItemClickListener();){
            @Override
                    public void onClick(Student item, int position) {
                T
            }
        }
        recyclerView.setAdapter(adapter);
    }*/
}
}
