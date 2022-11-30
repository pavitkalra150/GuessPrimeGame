package com.example.guessprimegame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView number;
    Button primeBtn;
    Button nonPrimeBtn;
    TextView timer;
    TextView lives;
    ImageView img;


    Timer myTimer;
    int seconds = 5;
    final Random myRandom = new Random();

    int chances = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        number = findViewById(R.id.number);
        primeBtn = findViewById(R.id.primeBtn);
        nonPrimeBtn = findViewById(R.id.nonprimeBtn);
        timer = findViewById(R.id.timer);
        lives = findViewById(R.id.lives);
        img = findViewById(R.id.image);

        reverseTimer(5, timer);
        img.setImageResource(android.R.color.transparent);
        lives.setText("Lives: " + chances);
        primeBtn.setOnClickListener(v -> {
            checkPrime();
            loadRandomNumber();
        });

        nonPrimeBtn.setOnClickListener(v -> {
            checkNonPrime();
            loadRandomNumber();
        });
    }

    void checkPrime(){
        int num = Integer.parseInt(number.getText().toString());
        if(isPrime(num)) {
            System.out.println("Prime");
            img.setImageResource(R.drawable.correct);
            reverseTimer(5,timer);
        }
        else {
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            System.out.println("Wrong Answer");
            img.setImageResource(R.drawable.wrongpng);
            chances--;
            lives.setText("Lives: " + chances);
            reverseTimer(5,timer);
            if(chances == 0){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Game Over");
                builder.setPositiveButton("Start Again" , null);
                builder.show();
                chances = 5;
                lives.setText("Lives: " + chances);
                img.setImageResource(android.R.color.transparent);
                reverseTimer(5,timer);
            }
        }
    }

    void checkNonPrime(){
        int num = Integer.parseInt(number.getText().toString());
        if(!isPrime(num)) {
            System.out.println("Non Prime");
            img.setImageResource(R.drawable.correct);
            reverseTimer(5,timer);
        }
        else {
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            System.out.println("Wrong Answer");
            img.setImageResource(R.drawable.wrongpng);
            chances--;
            lives.setText("Lives: " + chances);
            reverseTimer(5,timer);
            if(chances == 0){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Game Over");
                builder.setPositiveButton("Start Again" , null);
                builder.show();
                chances = 5;
                lives.setText("Lives: " + chances);
                img.setImageResource(android.R.color.transparent);
                reverseTimer(5,timer);
            }
        }
    }


    void loadRandomNumber(){
        number.setText(String.valueOf(myRandom.nextInt(100)));
    }

    boolean isPrime(int number){
        if(number <= 1) {return false;}
        if (number <= 3) {return true;}
        int i = 2;

        while(i * i <= number) {
            if(number % i == 0) {return false;}
            i += 1;
        }
        return true;
    }

    public void reverseTimer(int seconds,final TextView timer){

        new CountDownTimer(seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                seconds = seconds % 60;
                timer.setText("Time : " + String.format("%1d", seconds));
            }

            public void onFinish() {
                chances--;
                lives.setText("Lives = " + chances);
                int seconds = 5;
                timer.setText("Time : " + String.format("%1d", seconds));
                reverseTimer(5,timer);

                if(chances == 0){
                    Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                    chances = 5;
                    lives.setText("Lives: " + chances);
                    img.setImageResource(android.R.color.transparent);
                    loadRandomNumber();
                }
            }
        }.start();
    }



}