package com.example.charles_bezerra.descrypt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela_main extends AppCompatActivity {
    private Button btn_c;
    private Button btn_d;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_main);
        btn_c = findViewById(R.id.btn_c);
        btn_d = findViewById(R.id.btn_d);

        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_main.this, Criptador.class);
                startActivity(intent);
            }
        });

        btn_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_main.this, Descriptor.class);
                startActivity(intent);
            }
        });

    }
}
