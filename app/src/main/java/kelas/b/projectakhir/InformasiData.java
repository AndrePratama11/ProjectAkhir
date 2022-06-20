package kelas.b.projectakhir;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class InformasiData extends AppCompatActivity {

    private TextView nik, nama, jadwal;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informasi_data);

        nik = findViewById(R.id.tvNIK);
        nama = findViewById(R.id.tvNama);
        jadwal = findViewById(R.id.tvJadwal);

        progressDialog = new ProgressDialog(InformasiData.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");

        Intent intent = getIntent();
        if (intent != null){
            nik.setText(intent.getStringExtra("nik"));
            nama.setText(intent.getStringExtra("nama"));
            jadwal.setText(intent.getStringExtra("jadwal"));
        }
    }
}