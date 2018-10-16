package com.example.charles_bezerra.descrypt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Descriptor extends AppCompatActivity {
    private int n = 187;
    private int d = 173;
    public Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriptor);

        this.btn1 = findViewById(R.id.btn_descrypt);
        this.btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                descrypt();
            }
        });
    }
    protected void descrypt()
    {
        StringBuilder msg_d = new StringBuilder("");

        EditText msg = findViewById(R.id.msg_encrypted_editText);
        String msg_c = msg.getText().toString();

        if(msg_c != "" || msg_c != " " || msg_c != null)
        {
            ArrayList<Integer> nums = new ArrayList<Integer>();
            for (String num : msg_c.split(";")) {
                nums.add(Integer.parseInt(num));
            }
            for (int i = 0; i < nums.size(); i++) {
                char aux = (char) decode(nums.get(i));
                msg_d.append(aux);
            }
            showResul(msg_d.toString());
        }
        else
        {
            showResul("Por favor digitar a mensagem corretamente!");
        }
    }
    protected void showResul(String msg)
    {
        TextView text = findViewById(R.id.msg_text_result);
        text.setText(msg);
    }
    public int decode(int x)
    {
        BigDecimal aux = new BigDecimal("1");
        BigDecimal aux1 =  new BigDecimal( n );
        BigDecimal aux2 = new BigDecimal( x );
        for(int i = 0; i < d; i++)
        {
            aux = aux.multiply(aux2);
        }
        BigDecimal decodado = aux.remainder(aux1);
        return decodado.intValue();
    }
}
