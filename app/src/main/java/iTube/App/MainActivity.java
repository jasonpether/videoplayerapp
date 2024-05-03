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

public class MainActivity extends AppCompatActivity {
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText usernameET = findViewById(R.id.ETUserName);
        EditText passwordET = findViewById(R.id.ETPassword);
        Button loginButton = findViewById(R.id.LoginButton);
        Button signUpButton = findViewById(R.id.SignUpButton);
        db = new DBHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = db.fetchUser(usernameET.getText().toString().trim(), passwordET.getText().toString().trim());
                if (result > 0) {
                    Toast.makeText(MainActivity.this, "SUCCESSFULLY LOGGED IN", Toast.LENGTH_SHORT).show();
                    Intent Jump = new Intent(MainActivity.this, UrlScreen.class);
                    Jump.putExtra("username", usernameET.getText().toString().trim());
                    Jump.putExtra("userid", result);
                    startActivity(Jump);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "INCORRECT LOGIN DETAILS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Jump = new Intent(MainActivity.this, SignUpPage.class);
                startActivity(Jump);
            }
        });
    }
}