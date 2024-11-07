package edu.utsa.cs3443.zqi674_lab4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.zqi674_lab4.model.Fleet;
import edu.utsa.cs3443.zqi674_lab4.model.Starship;
import edu.utsa.cs3443.zqi674_lab4.model.CrewMember;

public class StarshipActivity extends AppCompatActivity {
    private Fleet fleet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starship);

        fleet = new Fleet("Starfleet");
        fleet.loadStarships(this);

        Intent intent = getIntent();
        String starshipRegistration = intent.getStringExtra("starshipRegistration");

        Starship starship = null;
        for (Starship s : fleet.getStarships()) {
            if (s.getRegistration().equals(starshipRegistration)) {
                starship = s;
                break;
            }
        }

        if (starship != null) {
            // Set the starship logo and name
            ImageView starshipLogo = findViewById(R.id.starship_logo);
            starshipLogo.setImageResource(R.drawable.starfleet_command_logo);

            TextView starshipName = findViewById(R.id.starship_name);
            starshipName.setText(starship.getName() + " (" + starship.getRegistration() + ")");

            // Add crew members dynamically
            LinearLayout llHolder = findViewById(R.id.ll_holder);

            for (CrewMember crewMember : starship.getCrewMembers()) {
                LinearLayout crewMemberLayout = new LinearLayout(this);
                crewMemberLayout.setOrientation(LinearLayout.HORIZONTAL);
                crewMemberLayout.setPadding(20, 20, 20, 20); // Add padding

                ImageView imageView = new ImageView(this);

                // Get the last name or the single name if no last name exists
                String[] nameParts = crewMember.getName().split(" ");
                String lastName = nameParts.length > 1 ? nameParts[nameParts.length - 1] : nameParts[0];
                lastName = lastName.toLowerCase().replace(".", "").replace(" ", "_"); // Normalize the name

                int imageResource = getResources().getIdentifier(lastName, "drawable", getPackageName());

                if (imageResource == 0) {
                    Log.e("StarshipActivity", "Image resource not found for " + crewMember.getName() + " with normalized name " + lastName);
                } else {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageResource);
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true); // Increase image size
                    imageView.setImageBitmap(scaledBitmap);
                }

                imageView.setLayoutParams(new LinearLayout.LayoutParams(300, 300)); // Increase image size
                imageView.setPadding(20, 20, 20, 20); // Add padding

                LinearLayout textLayout = new LinearLayout(this);
                textLayout.setOrientation(LinearLayout.VERTICAL);
                textLayout.setPadding(20, 20, 20, 20); // Add padding

                TextView positionTextView = new TextView(this);
                positionTextView.setText(crewMember.getRole());
                positionTextView.setTextSize(24); // Increase text size
                positionTextView.setPadding(0, 0, 0, 10); // Add padding

                TextView nameTextView = new TextView(this);
                nameTextView.setText(crewMember.getName());
                nameTextView.setTextSize(24); // Increase text size

                textLayout.addView(positionTextView);
                textLayout.addView(nameTextView);

                crewMemberLayout.addView(imageView);
                crewMemberLayout.addView(textLayout);

                llHolder.addView(crewMemberLayout);
            }

        } else {
            Toast.makeText(this, "Starship not found", Toast.LENGTH_SHORT).show();
            Log.e("StarshipActivity", "Starship with registration " + starshipRegistration + " not found");
        }
    }
}
