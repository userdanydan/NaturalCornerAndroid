package be.daniel.naturalcornerandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.NetworkOnMainThreadException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static final String CREDENTIALS = "Credentials";
    public static final String FROM_SIGN_IN = "from sign in button";
    public static final String FROM_CREATE_ACCOUNT = "from create account";
    public static final String FROM_CONTINUE_WITHOUT_SIGNING_IN = "from continue without signin in";
    public static final String URL_STRING = "https://danielportfolio-1.appspot.com/?email=admin@admin.be";
    private EditText editTextEmailLogin, editTextPasswordLogin;
    private Button buttonSignInLogin, buttonCreateAccountLogin, buttonContinueWithoutSigningInLogin;
    private volatile boolean isIdentified;
    private volatile String jsonContent;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpWidgets();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setUpWidgets() {
        initializeWidgets();
        addListenersToWidgets();
    }

    private void initializeWidgets() {
        editTextEmailLogin = (EditText) findViewById(R.id.login_activity_et_nickname);
        editTextPasswordLogin = (EditText) findViewById(R.id.login_activity_et_edit_password);
        buttonContinueWithoutSigningInLogin = (Button) findViewById(R.id.login_activity_button_continue);
        buttonCreateAccountLogin = (Button) findViewById(R.id.login_activity_button_create_account);
        buttonSignInLogin = (Button) findViewById(R.id.login_activity_button_sign_in);
    }

    private void addListenersToWidgets() {
        buttonSignInLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = editTextEmailLogin.getText().toString();
                final String password = editTextPasswordLogin.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean isChecked = checkEmailAndPasswordValidity(email, password);
                        Log.i("CHECKED", String.valueOf(isChecked));
                        if (isChecked) {
                            saveEmailAndPasswordToSharedPreferences(email, password);
                            Intent intent = new Intent(LoginActivity.this, MainScreenActivity.class);
                            intent.putExtra("from", FROM_SIGN_IN);
                            startActivity(intent);
                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, "INCORRECT EMAIL AND/OR PASSWORD", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                }).start();

            }
        });
        buttonCreateAccountLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                intent.putExtra("from", FROM_CREATE_ACCOUNT);
                startActivity(intent);
            }
        });
        buttonContinueWithoutSigningInLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainScreenActivity.class);
                intent.putExtra("from", FROM_CONTINUE_WITHOUT_SIGNING_IN);
                startActivity(intent);
            }
        });
    }

    private void saveEmailAndPasswordToSharedPreferences(String email, String password) {
        SharedPreferences credentials = getSharedPreferences(CREDENTIALS, 0);
        SharedPreferences.Editor editor = credentials.edit();
        editor.putStringSet("credentials", new HashSet<>(Arrays.asList(new String[]{email, password})));
        editor.commit();
    }

    public boolean checkEmailAndPasswordValidity(final String email, final String password) {
        //TODO
        // get a connection to the server and ask if the user exists.


        ConnectivityManager cm =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork !=null && activeNetwork.isConnectedOrConnecting();
        if(isConnected) {
            BufferedInputStream bis = null;
            try {
                URL url = new URL(URL_STRING);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                bis = new BufferedInputStream(connection.getInputStream());
                if(bis!=null) {
                    byte[] bytesRaw = new byte[1024];
                    int bytesRead = 0;
                    while ((bytesRead = bis.read(bytesRaw)) != -1) {
                        jsonContent = new String(bytesRaw, 0, bytesRead);
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Not getting anything from the remote server", Toast.LENGTH_SHORT).show();
                    isIdentified=false;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null)
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            try {
                JSONArray jsonArray = new JSONArray(jsonContent);
                Log.i("JSON_ARRAY_RESULT", jsonArray.toString());
                JSONObject jsonObject = new JSONObject(jsonArray.getString(0));
                Log.i("JSON_RESULT", jsonObject.toString());
                String emailFromJson = jsonObject.getString("email");
                String passwordFromJson = jsonObject.getString("password");
                if(passwordFromJson.equals(password)){
                    isIdentified=true;
                }else {
                    Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    isIdentified=false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            Toast.makeText(LoginActivity.this, "No connexion to the remote server", Toast.LENGTH_SHORT).show();
            isIdentified=false;
        }
        return isIdentified;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://be.daniel.naturalcornerandroid/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://be.daniel.naturalcornerandroid/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
