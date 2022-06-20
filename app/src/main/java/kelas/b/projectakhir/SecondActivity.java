package kelas.b.projectakhir;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton btnAdd;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private List<User> list = new ArrayList<>();
    private UserAdapter userAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        recyclerView = findViewById(R.id.recycler_view);
        btnAdd = findViewById(R.id.btn_save);

        progressDialog = new ProgressDialog(SecondActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Memproses...");
        userAdapter = new UserAdapter(getApplicationContext(), list);
        userAdapter.setDialog(new UserAdapter.Dialog() {
            @Override
            public void onClick(int pos) {
                final CharSequence[] dialogItem = {"Lihat Data", "Edit", "Hapus"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(SecondActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent in = new Intent(getApplicationContext(), InformasiData.class);
                                in.putExtra("id", list.get(pos).getId());
                                in.putExtra("nik", list.get(pos).getNik());
                                in.putExtra("nama", list.get(pos).getNama());
                                in.putExtra("jadwal", list.get(pos).getDay());
                                startActivity(in);
                                break;
                            case 1:
                                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                                intent.putExtra("nik", list.get(pos).getNik());
                                intent.putExtra("nama", list.get(pos).getNama());
                                intent.putExtra("jadwal", list.get(pos).getDay());
                                startActivity(intent);
                                break;
                            case 2:
                                deleteData(list.get(pos).getNik());
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(userAdapter);

        btnAdd.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), EditActivity.class));
        });
    }

    /**
     * method untuk menampilkan data agar di tampilkan
     * pada saat aplikasi pertama kali di running
     */

    @Override
    protected void onStart(){
        super.onStart();
        getData();
    }


    private void getData() {
        progressDialog.show();
        /**
         * Mengambil data dari firestore
         */
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                if (task.isSuccessful()){
                    /**
                     * code ini mengambil data dari collection
                     */
                    for (QueryDocumentSnapshot document : task.getResult()){
                        /**
                         * Data apa saya yang ingin di ambil dari collection
                         */
                        User user = new User(document.getString("nik"), document.getString("nama"), document.getString("jadwal"));
                        user.setNik(document.getId());
                        list.add(user);
                    }
                    userAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(), "Data gagal di ambil!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    /**
     * method untuk menghapus data
     */
    private void deleteData(String id){
        progressDialog.show();
        db.collection("users").document(id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Data gagal di hapus!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                getData();
            }
        });
    }
}
