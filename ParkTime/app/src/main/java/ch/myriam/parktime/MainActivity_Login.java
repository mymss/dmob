package ch.myriam.parktime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity_Login extends AppCompatActivity {
EditText username, password;
Button btnLogin ;
DataBaseParkTime db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        username = (EditText) findViewById(R.id.username);
        password =(EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        db = new DataBaseParkTime(this);

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                String user = username.getText().toString();
                String mdp = password.getText().toString();

                if(user.equals("")||mdp.equals("")){
                    Toast.makeText(MainActivity_Login.this,"Remplissez tous les champs svp",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass = db.checkUsernamepassword(user,mdp);
                    if(user.equals("admin") && checkuserpass){
                        Toast.makeText(MainActivity_Login.this,"Welcome "+ user,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity_AdminView.class);
                        startActivity(intent);}

                    else if (checkuserpass && !user.equals("admin")){
                        Toast.makeText(MainActivity_Login.this,"Welcome "+ user,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity_Home.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity_Login.this,"Mot de passe ou Username invalide", Toast.LENGTH_SHORT).show();
                    }
                }
           }
       });
    }
    public void goResister(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}