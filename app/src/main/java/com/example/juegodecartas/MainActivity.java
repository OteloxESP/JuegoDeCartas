package com.example.juegodecartas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int imgCartas [] = new int[]{R.drawable.bulbasur,
            R.drawable.charmeleon,
            R.drawable.charizard,
            R.drawable.pikachu,
            R.drawable.wartortle,
            R.drawable.blastoise
    };

    int[] n1 = new int[]{0,1,2,3,4,5};
    int[] n2 = new int[]{0,1,2,3,4,5};
    int comprobante = 0;
    int [] imgID = new int[]{0,0};
    int [] btnPulsado = new int[]{0,0};
    int parejasEncontradas = 0;
    int totalParejas = 0;
    int vidasRestantes = 7;

    ImageButton btn_img1,btn_img2,btn_img3,btn_img4,btn_img5,btn_img6,
            btn_img7,btn_img8,btn_img9,btn_img10,btn_img11,btn_img12;

    ImageButton imgBtns [] = new ImageButton[]{
            btn_img1,btn_img2,btn_img3,btn_img4,btn_img5,btn_img6,
            btn_img7, btn_img8, btn_img9, btn_img10, btn_img11, btn_img12
    };
    TextView txtVidasRestantes,txtTotalParejas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String usuario = bundle.getString("usuario");
        TextView txtUsuario = findViewById(R.id.txtUsuario);
        txtUsuario.setText(usuario);

        imgBtns [0] = findViewById(R.id.imgBtn1);
        imgBtns [1] = findViewById(R.id.imgBtn2);
        imgBtns [2] = findViewById(R.id.imgBtn3);
        imgBtns [3] = findViewById(R.id.imgBtn4);
        imgBtns [4] = findViewById(R.id.imgBtn5);
        imgBtns [5] = findViewById(R.id.imgBtn6);
        imgBtns [6] = findViewById(R.id.imgBtn7);
        imgBtns [7] = findViewById(R.id.imgBtn8);
        imgBtns [8] = findViewById(R.id.imgBtn9);
        imgBtns [9] = findViewById(R.id.imgBtn10);
        imgBtns [10] = findViewById(R.id.imgBtn11);
        imgBtns [11] = findViewById(R.id.imgBtn12);
        txtVidasRestantes = findViewById(R.id.txtVidasResta);
        txtTotalParejas = findViewById(R.id.txtPuntuacion);

        crearTablero();
    }

    public void crearTablero(){
        for (int i = 0; i < imgBtns.length; i++){
            imgBtns [i].setImageResource(R.drawable.cartadetras);
        }
        asignarCartas1();
        asignarCartas2();
    }

    public void asignarCartas1(){ //baraja las imagenes y se las asigna a los botones PARES cuando este es pulsado
        Random r = new Random();
        for (int i=0; i<n1.length; i++) {
            int posAleatoria = r.nextInt(n1.length);
            int temp = n1[i];
            n1[i] = n1[posAleatoria];
            n1[posAleatoria] = temp;
        }
        int x = 0;
        for(int p = 0; p < 12; p=p+2){
            int finalP = p;
            int t = n1[x];
            x++;
            imgBtns[p].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgBtns[finalP].setImageResource(imgCartas[t]);
                    comprobacion(t,finalP);
                }
            });
        }
    }
    public void asignarCartas2(){ //baraja las imagenes y se las asigna a los botones IMPARES cuando este es pulsado
        Random r = new Random();
        for (int i=0; i<n2.length; i++) {
            int posAleatoria = r.nextInt(n2.length);
            int temp = n2[i];
            n2[i] = n2[posAleatoria];
            n2[posAleatoria] = temp;
        }
        int x = 0;
        for(int p = 1; p < 12; p=p+2){
            int finalP = p;
            int t = n2[x];
            x++;
            imgBtns[p].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgBtns[finalP].setImageResource(imgCartas[t]);
                    comprobacion(t,finalP);
                }
            });
        }
    }

    public void comprobacion(int imgAplicado, int btn){
        imgID[comprobante] = imgAplicado; //guarda la imagen y el boton volteado
        btnPulsado[comprobante] = btn;
        imgBtns[btnPulsado[comprobante]].setEnabled(false);

        if(comprobante == 1){
            if(imgID[0] != imgID[1]){
                for (int m = 0; m < imgBtns.length; m++){ //Vuelve a activar todos los botones
                    imgBtns[m].setEnabled(false);
                }
                new CountDownTimer(800, 800) {
                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        noEsPareja(true);
                        for (int m = 0; m < imgBtns.length; m++){ //Vuelve a activar todos los botones
                            imgBtns[m].setEnabled(true);
                        }
                    }
                }.start();

            }else{
                parejasEncontradas++;
                totalParejas++;
                actualizarContadorParejas();
                if(parejasEncontradas == 6){
                    new CountDownTimer(800, 800) {
                        public void onTick(long millisUntilFinished) {

                        }

                        public void onFinish() {
                            crearTablero(); //Vuelve a crear el tablero
                            for (int m = 0; m < imgBtns.length; m++){ //Vuelve a activar todos los botones
                                imgBtns[m].setEnabled(true);
                                parejasEncontradas=0;
                            }
                        }
                    }.start();

                }else{
                    imgBtns[btnPulsado[0]].setEnabled(false); //Desactiva los dos botones que son pareja
                    imgBtns[btnPulsado[1]].setEnabled(false);
                }
            }
            comprobante = 0;
        }else{
            comprobante++;
        }
    }

    public void noEsPareja(boolean v){ //Volteara las dos cartas si no son parejas
        if(v){
            imgBtns[btnPulsado[0]].setImageResource(R.drawable.cartadetras);
            imgBtns[btnPulsado[1]].setImageResource(R.drawable.cartadetras);
            vidasRestantes--;
            if (vidasRestantes==0){
                Toast.makeText(this, "Has perdido :(", Toast.LENGTH_LONG).show();
                crearTablero();
                vidasRestantes = 7;
                totalParejas = 0;
                actualizarContadorParejas();
            }
            actualizarContadorVidas();
        }
    }

    public void actualizarContadorParejas(){
        String texto = getResources().getString(R.string.txtParejas);
        txtTotalParejas.setText(texto+" "+totalParejas);
    }

    public void actualizarContadorVidas(){
        String texto = getResources().getString(R.string.txtVidas);
        txtVidasRestantes.setText(texto+" "+vidasRestantes);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mItem1:
                vidasRestantes = 7;
                totalParejas = 0;
                actualizarContadorParejas();
                actualizarContadorVidas();
                crearTablero();
                comprobante = 0;
                for (int m = 0; m < imgBtns.length; m++){
                    imgBtns[m].setEnabled(true);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}