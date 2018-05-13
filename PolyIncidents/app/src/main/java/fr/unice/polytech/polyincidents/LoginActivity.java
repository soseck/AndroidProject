package fr.unice.polytech.polyincidents;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * A login screen that offers login via identifier/password for the first connexion
 * or after an explicit deconnexion.
 */
public class LoginActivity extends AppCompatActivity{
    public static final String USER_PREF_NAME = "currentUser";
    public static final String USERNAME_PREF_KEY = "username";
    public static final String PASSWORD_PREF_KEY = "password";
    public static final String SURNAME_PREF_KEY = "surname";
    public static final String NAME_PREF_KEY = "name";

    private EditText userIDField, passwordField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userIDField = (EditText)findViewById(R.id.userID);
        passwordField = (EditText)findViewById(R.id.password);
    }

    public void onLogin(View view){
        String userID = userIDField.getText().toString();
        String password = passwordField.getText().toString();

        BackgroundLoginManager loginManager = new BackgroundLoginManager(this);
        User connectingUser = new User();
        connectingUser.setUserID(userID);
        connectingUser.setPassword(password);

        loginManager.execute(connectingUser);

    }

    public void setConnectedUser(User connectedUser){
        SharedPreferences preferences = getSharedPreferences(USER_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERNAME_PREF_KEY, connectedUser.getUserID());
        editor.putString(PASSWORD_PREF_KEY, connectedUser.getPassword());
        editor.putString(NAME_PREF_KEY, connectedUser.getName());
        editor.putString(SURNAME_PREF_KEY, connectedUser.getSurname());
        editor.apply();
    }

    public void showMenuPage(){
        startActivity(new Intent(this, LoginActivity.class));
    }
}

