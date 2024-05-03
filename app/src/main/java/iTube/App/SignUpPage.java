package iTube.App;

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
import iTube.App.model.User;

public class SignUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        DBHelper db = new DBHelper(this);
        EditText signupETUsername = findViewById(R.id.Signupusername);
        EditText signupETPassword = findViewById(R.id.ETSUpassword);
        EditText signupETConfirmPassword = findViewById(R.id.ETSUconfirmpassword);
        Button createAccount = findViewById(R.id.CreateAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = signupETUsername.getText().toString().trim();
                String password = signupETPassword.getText().toString().trim();
                String confirmPassword = signupETConfirmPassword.getText().toString().trim();

                if (password.equals(confirmPassword))
                {
                    long result = db.insertUser(new User(username, password));
                    if (result > 0){
                        Toast.makeText(SignUpPage.this, "User Created", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(SignUpPage.this, "FAILED", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(SignUpPage.this, "FAILED, passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}