package edu.utsa.cs3443.zqi674_lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button for USS Enterprise (NCC-1701-A)
        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StarshipActivity.class);
                intent.putExtra("starshipRegistration", "NCC-1701-A");
                startActivity(intent);
            }
        });

        // Button for USS Enterprise (NCC-1701-D)
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StarshipActivity.class);
                intent.putExtra("starshipRegistration", "NCC-1701-D");
                startActivity(intent);
            }
        });

        // Button for USS Voyager (NCC-74656)
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StarshipActivity.class);
                intent.putExtra("starshipRegistration", "NCC-74656");
                startActivity(intent);
            }
        });
    }
}
