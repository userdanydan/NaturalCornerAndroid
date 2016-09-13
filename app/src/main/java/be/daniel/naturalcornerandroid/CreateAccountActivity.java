package be.daniel.naturalcornerandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Intent intent = getIntent();
        if(intent!=null){
            switch(intent.getStringExtra("from")){
                case LoginActivity.FROM_CREATE_ACCOUNT :
                    Toast.makeText(CreateAccountActivity.this, "COUCOU", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
