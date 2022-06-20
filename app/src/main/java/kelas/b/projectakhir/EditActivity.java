package kelas.b.projectakhir;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    private EditText edtNIK, edtName, editDay;
    private Button btnSave;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edtNIK = findViewById(R.id.nik);
        edtName = findViewById(R.id.nama);
        editDay = findViewById(R.id.day);
        btnSave = findViewById(R.id.btn_save);

        progressDialog = new ProgressDialog(EditActivity.this);
        progressDialog.setTitle("Menunggu");
        progressDialog.setMessage("Menyimpan Data...");

        btnSave.setOnClickListener(v->{
            if (edtNIK.getText().length() > 0 && edtName.getText().length() > 0 && editDay.getText().length() > 0){

                saveData(edtNIK.getText().toString(), edtName.getText().toString(), editDay.getText().toString());
            }else {
                Toast.makeText(this, "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        if (intent!= null){
            edtNIK.setText(intent.getStringExtra("nik"));
            edtName.setText(intent.getStringExtra("nama"));
            editDay.setText(intent.getStringExtra("jadwal"));
        }
    }

    private void saveData(String nik, String name, String day) {
        Map<String, Object> user = new HashMap<>();
        user.put("nik", nik);
        user.put("nama", name);
        user.put("jadwal", day);

        progressDialog.show();

        if (nik != null){

            db.collection("users").document(nik)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(EditActivity.this, "Data berhasil di Edit..", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(EditActivity.this, "Gagal Mengedit data..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {

            db.collection("users")
                    .add(user)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(EditActivity.this, "Berhasil di simpan", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }
}
