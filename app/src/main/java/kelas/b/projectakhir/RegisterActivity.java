package kelas.b.projectakhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    EditText edtNama, edtNIK, edtPassword, edtRepass;

    Button fab;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_register);

        edtNama = findViewById(R.id.edNama);
        edtNIK = findViewById(R.id.edNIK);
        edtPassword = findViewById(R.id.edPass);
        edtRepass = findViewById(R.id.edRepass);

        fab = findViewById(R.id.fabregist);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNama.getText().toString().isEmpty() ||
                    edtNIK.getText().toString().isEmpty() ||
                    edtPassword.getText().toString().isEmpty() ||
                    edtRepass.getText().toString().isEmpty())
                {
                    Snackbar.make(view,"Semua data harus di isi...", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    if (edtPassword.getText().toString().equals(edtRepass.getText().toString()))
                    {
                        Toast.makeText(getApplicationContext(), "Register berhasil...",Toast.LENGTH_LONG).show();

                        Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Snackbar.make(view, "Password dan Repassword harus sama...", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
