package com.example.juegodecartas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    ImageButton btn_img1;
    ImageButton btn_img2;
    ImageButton btn_img3;
    ImageButton btn_img4;
    ImageButton btn_img5;
    ImageButton btn_img6;
    ImageButton btn_img7;
    ImageButton btn_img8;
    ImageButton btn_img9;
    ImageButton btn_img10;
    ImageButton btn_img11;
    ImageButton btn_img12;

    ImageButton imgBtns [] = new ImageButton[]{
            btn_img1,
            btn_img2,
            btn_img3,
            btn_img4,
            btn_img5,
            btn_img6,
            btn_img7,
            btn_img8,
            btn_img9,
            btn_img10,
            btn_img11,
            btn_img12
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        crearTablero();


    }



    public void crearTablero(){
        for (int i = 0; i < imgBtns.length; i++){
            imgBtns [i].setImageResource(R.drawable.cartadetras);
        }

        asignarCartas1();
        asignarCartas2();

    }

    public void asignarCartas1(){
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
                    Toast.makeText(getApplicationContext(),"Funciono perfectamente", Toast.LENGTH_SHORT).show();
                    imgBtns[finalP].setImageResource(imgCartas[t]);

                }
            });
        }
    }
    public void asignarCartas2(){
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
                    Toast.makeText(getApplicationContext(),"Funciono perfectamente", Toast.LENGTH_SHORT).show();
                    imgBtns[finalP].setImageResource(imgCartas[t]);

                }
            });
        }

    }
}