package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int active = 0;
    int[] state = {2,2,2,2,2,2,2,2,2};
    int[][] winState = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    boolean endGame = false;
    String winner = "No";
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tap = Integer.parseInt(counter.getTag().toString());
        Log.i("Tag",counter.getTag().toString());
        if(state[tap]==2 && !endGame){
            state[tap] = active;
            counter.animate().alpha(0);
            if(active==0){
                counter.setImageResource(R.drawable.gondor);
                active = 1;
            }else{
                counter.setImageResource(R.drawable.rohan);
                active = 0;
            }
            counter.animate().alphaBy(10).rotation(3600).setDuration(300);
            Button resetButton = findViewById(R.id.btnReset);
            TextView textView = findViewById(R.id.textView);
            for(int[] win : winState){
                if(state[win[0]] == state[win[1]] && state[win[1]]==state[win[2]] && state[win[0]]!=2){
                    if(active==1)
                        winner = "Gondor";
                    else
                        winner = "Rohan";
                    textView.setText(winner+" has won.");
                    textView.setVisibility(View.VISIBLE);
                    resetButton.setVisibility(View.VISIBLE);
                    endGame=true;
                }
            }

            boolean out = true;
            for(int i=0;i<state.length;i++){
                if(state[i]==2)
                    out = false;
            }
            if(winner=="No" && out==true) {
                textView.setText("Out of move");
                textView.setVisibility(View.VISIBLE);
                resetButton.setVisibility(View.VISIBLE);
            }

            Log.i("Note",winner+" "+out);

        }
    }

    public void reset(View view){
        Button resetButton = findViewById(R.id.btnReset);
        TextView textView = findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.INVISIBLE);
        winner = "No";
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i =0; i<gridLayout.getChildCount();i++){
            ImageView counter =  (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        active = 0;
        for(int i = 0; i<state.length;i++){
            state[i]=2;
        }
        endGame = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
