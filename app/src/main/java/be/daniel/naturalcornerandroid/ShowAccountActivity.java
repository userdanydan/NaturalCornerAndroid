package be.daniel.naturalcornerandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import be.daniel.naturalcornerandroid.model.User;
import be.daniel.naturalcornerandroid.naturalcornerapplication.NaturalCornerApplication;

public class ShowAccountActivity extends AppCompatActivity {
    private TextView tvShowAccountName, tvShowAccountEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_account);

        tvShowAccountName = (TextView)findViewById(R.id.account_screen_tv_name);
        tvShowAccountEmail= (TextView)findViewById(R.id.account_screen_tv_email);



        NaturalCornerApplication NCapp = (NaturalCornerApplication) getApplication();

        if(NCapp.getUser()!=null){
            tvShowAccountName.setText(tvShowAccountName.getText().toString().substring(0,
                    tvShowAccountName.getText().toString().indexOf(":")+1) + " " +NCapp.getUser().getName());
            tvShowAccountEmail.setText(tvShowAccountEmail.getText().toString().substring(0,
                    tvShowAccountEmail.getText().toString().indexOf(":")+1) + " " +NCapp.getUser().getEmail());
        }else{
            tvShowAccountName.setText(R.string.nothing);
        }



    }
}
