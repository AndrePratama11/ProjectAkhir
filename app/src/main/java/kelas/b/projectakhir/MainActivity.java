package kelas.b.projectakhir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnLogin, btnRegister;
    EditText ednik, edpassword;
    String nik,password;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin=findViewById(R.id.btsignin);
        btnRegister=findViewById(R.id.btnRegister);

        ednik = findViewById(R.id.edNIK);
        edpassword=findViewById(R.id.edpassword);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nik = ednik.getText().toString();
                password = edpassword.getText().toString();

                String name = "112233";
                String pass = "123";

                if (nik.isEmpty() || password.isEmpty()) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "NIK dan Password wajib diisi!!!",
                            Toast.LENGTH_LONG);
                } else {
                    if (nik.equals(name) && password.equals(pass)) {
                        Toast t = Toast.makeText(getApplicationContext(),
                                "Login Sukses",
                                Toast.LENGTH_LONG);
                        t.show();

                        Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                        startActivity(i);

                    } else {
                        if (nik.isEmpty() || password.equals(pass)) {
                            Toast t = Toast.makeText(getApplicationContext(),
                                    "NIK salah",
                                    Toast.LENGTH_LONG);
                            t.show();
                        } else {
                            if (nik.equals(name)) {
                                Toast t = Toast.makeText(getApplicationContext(),
                                        "Password salah",
                                        Toast.LENGTH_LONG);
                                t.show();
                            }
                        }


                    }
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}