package ch.myriam.parktime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // references to button and others controls on the layout
    Button btn_add, btn_ViewAll, btn_GoToAddPark;
    EditText et_name, et_age, et_username, et_cusNbrEnfants, et_localite,
            et_mdp, et_mdp2;
    DataBaseParkTime db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add = findViewById(R.id.btn_add);
        btn_ViewAll = findViewById(R.id.btn_ViewAll);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_username = findViewById(R.id.et_username);
        et_cusNbrEnfants = findViewById(R.id.et_cusNbrEnfants);
        et_localite = findViewById(R.id.et_localite);
        et_mdp = findViewById(R.id.et_mdp);
        et_mdp2 = findViewById(R.id.et_mdp2);
        db = new DataBaseParkTime(this);
        // button listeners for the add and view all buttons

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = et_name.getText().toString();
                Integer age = Integer.parseInt(et_age.getText().toString());
                String username = et_username.getText().toString();
                Integer localite = Integer.parseInt(et_localite.getText().toString());
                Integer nbEnfants = Integer.parseInt(et_cusNbrEnfants.getText().toString());
                String mdp1 = et_mdp.getText().toString();
                String mdp2 = et_mdp2.getText().toString();


                if (name.equals("") || age.equals("") || username.equals("") || localite.equals("") || nbEnfants.equals("") || mdp1.equals("") || mdp2.equals("")) {
                    Toast.makeText(MainActivity.this, "Remplissez tous les champs svp", Toast.LENGTH_SHORT).show();
                } else {
                    if (mdp1.equals(mdp2)) {
                        Boolean checkMdp = db.checkUsername(username);
                        if (!checkMdp) {
                            CustomerModel customerModel = new CustomerModel(-1, name, age, username, localite, nbEnfants, mdp1);
                            Boolean insert = db.addOne(customerModel);
                            if (insert) {
                                Toast.makeText(MainActivity.this, "Création du compte effectuée", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity_Home.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Création de compte échouée", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Le nom d'utilisateur est délà existant", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Les mots de passes ne correspondent pas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

