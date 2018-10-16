package com.example.charles_bezerra.descrypt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.lang.String;

public class Criptador extends AppCompatActivity {
    private int n = 187;
    private int e = 37;
    public Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criptador);
        btn1 = findViewById(R.id.btn_process);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encrypt();
            }
        });
    }
    protected void encrypt()
    {
        StringBuilder msg_output = new StringBuilder("");

        EditText msg = findViewById(R.id.input_msg);
        String msg_input = msg.getText().toString();

        if(msg_input != "" || msg_input != " " || msg_input != null)
        {
            ArrayList<Integer> letras = new ArrayList();

            for(int letra = 0;letra<msg_input.length(); letra++)
            {
                char aux = msg_input.charAt(letra);
                int aux2 = (int) aux;
                letras.add(aux2);
            }
            for(int i = 0; i < letras.size(); i++)
            {
                msg_output.append( encode(letras.get(i)) );
                msg_output.append(";");
            }
            showResul(msg_output.toString());
        }
        else
        {
            showResul("Por favor digitar a mensagem corretamente!");
        }
    }
    protected void showResul(String msg)
    {
        EditText text = findViewById(R.id.output_msg);
        text.setText(msg);
    }
    public int encode(int x)
    {
        BigDecimal aux = new BigDecimal("1");
        BigDecimal aux1 =  new BigDecimal( this.n );
        BigDecimal aux2 = new BigDecimal( x );
        for(int i = 0; i < this.e; i++)
        {
            aux = aux.multiply(aux2);
        }
        BigDecimal encodado = aux.remainder(aux1);
        return encodado.intValue();
    }
}
