package iTube.App;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import iTube.App.data.DBHelper;
import iTube.App.util.Util;

public class UrlScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_screen);
        DBHelper db = new DBHelper(this);
        EditText URL = findViewById(R.id.ETEnterURL);
        Button Play = findViewById(R.id.PlayButton);
        Button Add = findViewById(R.id.AddPlaylist);
        Button ViewPlaylist = findViewById(R.id.myPlaylist);

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Jump = new Intent(UrlScreen.this, VideoScreen.class);
                Jump.putExtra("Video_Link", URL.getText().toString().trim());
                startActivity(Jump);
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UrlScreen.this, "ADDED TO PLAYLIST", Toast.LENGTH_SHORT).show();
                int userid = getIntent().getIntExtra("userid", -1);
                db.insertVideoLink(userid, URL.getText().toString().trim());
            }
        });

        ViewPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Jump = new Intent(UrlScreen.this, PlaylistDisplay.class);
                startActivity(Jump);
            }
        });

    }
}