package com.example.myapp;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import db.MyNotesDB;

public class notification extends AppCompatActivity {
    EditText et;
    MyNotesDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btn_add =findViewById(R.id.btn_add);
        Button btn2 =findViewById(R.id.btn_show);
        db = new MyNotesDB(this);

        //  btn_add is on click
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog =new Dialog(notification.this);
                dialog.setCancelable(false);
                dialog.setTitle("add new Name");
                dialog.setContentView(R.layout.add_dialog);
                et =dialog.findViewById(R.id.et_name);
                Button bt = dialog.findViewById(R.id.btn_save);

                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name =et.getText().toString();
                        if (db.insertNote(name)==true){
                            Toast.makeText(notification.this, "Add Successfully", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(notification.this, "Not Add Successfully", Toast.LENGTH_LONG).show();

                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
              //  Intent i = new Intent(notification.this, MainActivity2.class);
               // startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(notification.this, showNotes.class);
                startActivity(i);
            }
        });
    }
}