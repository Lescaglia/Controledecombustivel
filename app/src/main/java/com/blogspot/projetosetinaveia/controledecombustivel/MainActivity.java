package com.blogspot.projetosetinaveia.controledecombustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.BreakIterator;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Criação das variáveis dos botões, textos e edits
    Button btnCalcular;
    TextView tvwProporcao, tvwResultado;
    EditText edtAlcool, edtGasolina;
    // Shift+F6 ajuda na localização das variáveis no código

    //Abaixo declaração de nova instância para implementação de formatação do campo double com 2 casas decimais - Pattern não foi digitado, aparece sozinho
    DecimalFormat df = new DecimalFormat( "#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Agora o momento de lincar as variárives com os elementos de tela
        btnCalcular = findViewById(R.id.btnCalcular);
        tvwProporcao = findViewById(R.id.tvwProporcao);
        tvwResultado = findViewById(R.id.tvwResultado);
        edtAlcool = findViewById(R.id.edtAlcool);
        edtGasolina = findViewById(R.id.edtGasolina);
        // Fim do processo de link das variáveis com os elementos de tela

        //Determinar o evento do clique do Botão
        //Chamada do objeto através da classe - instância
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Variáveis locais ao clicar no botão CALCULAR
                double dblAlcool;
                double dblGasolina;

                //Condicional que verifica se o conteúdo do TextView é ou não nulo antes de iniciar qualquer cálculo ou conversão
                if ((edtAlcool.getText().toString().trim().equals("")) || (edtGasolina.getText().toString().trim().equals(""))){
                    tvwProporcao.setText("O Valor não pode ser nulo. Preencha novamente ;) ");
                    tvwResultado.setText("");
                } else {
                    //método da classe edtAlcool que captura os valores/textos do TextView preço do alcool e gasolina
                    //O tipo nativo do getText não é String, por isto deve ser convertido para Tal .toString()
                    String strAlcool = edtAlcool.getText().toString();
                    String strGasolina = edtGasolina.getText().toString();

                    //Função que Envelopa=Converte a String em Double
                    dblAlcool = Double.parseDouble(strAlcool); //ERRO AO DEIXAR O VALOR EM BRANCO
                    dblGasolina = Double.parseDouble(strGasolina);

                    //Cálculos contidos no acionamento do botão btnCalcular -> btnCalcular.setOnClickListener
                   double dblResultado = (dblAlcool / dblGasolina) * 100;
                   System.out.println("Valor do Álcool Cents: " + dblAlcool + " Valor da Gasolina Cents: " + dblGasolina);

                   //Se gasolina estiver acima de 70%, compensa gasolina
                       if (dblResultado > 70) {
                           tvwResultado.setText("Abasteça com Gasolina");
                       }else{
                           tvwResultado.setText("Abasteça com Álcool");
                       }

                    //Abaixo em df.format a implementação do método para formatação de casas decimais
                   tvwProporcao.setText("Proporção: " + df.format(dblResultado) + "%");

                    //Mostra o valor das Strings
                    System.out.println("Valor do Álcool: " + strAlcool + " Valor da Gasolina: " + strGasolina);
                }
            }
        });


    }
}