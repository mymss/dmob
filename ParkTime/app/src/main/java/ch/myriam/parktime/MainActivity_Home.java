package ch.myriam.parktime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null) {
                return;
            }
            FragmentHome fragmentHome = new FragmentHome();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fragmentHome,null);
            fragmentTransaction.commit();
        }
    }
}