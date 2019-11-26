package avishkaar.com.firestoredemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailET,passwordET;
    Button login_signUp;
    FirebaseAuth mFirebaseAuth;
    private static final String TAG = "MainActivity";
    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            if(firebaseAuth.getCurrentUser()!=null)
            {
                Log.e(TAG, "onAuthStateChanged: " + mFirebaseAuth.getCurrentUser() );
                Intent intent = new Intent(MainActivity.this,AddDataScreen.class);
                startActivity(intent);
            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_signUp = findViewById(R.id.signUpOrLogin);
        emailET = findViewById(R.id.emailEditText);
        passwordET = findViewById(R.id.passwordEditText);
        login_signUp.setOnClickListener(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signUpOrLogin:
             signUpOrLogin(emailET.getText().toString(),passwordET.getText().toString());
        }
    }


    void signUpOrLogin(String email,String password)
    {
        if(mFirebaseAuth.getCurrentUser()==null)
        {
            mFirebaseAuth.createUserWithEmailAndPassword(email,password);
            Log.e(TAG, "signUpOrLogin: " + mFirebaseAuth.getCurrentUser() );
        }else
        {
            mFirebaseAuth.signInWithEmailAndPassword(email,password);

        }
    }
}
