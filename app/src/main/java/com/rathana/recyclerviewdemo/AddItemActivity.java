package com.rathana.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rathana.recyclerviewdemo.model.Contact;

public class AddItemActivity extends AppCompatActivity {

    Button btnSave;
    EditText name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        btnSave=findViewById(R.id.btnSave);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);

        btnSave.setOnClickListener(v->{
            Contact contact=new Contact(
                    R.drawable.panda_cub,
                    name.getText().toString(),
                    email.getText().toString());

            Intent intent=new Intent();
            intent.putExtra("contact",contact);
            setResult(RESULT_OK,intent);
            finish();
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
        });

    }
}
