package ch.myriam.parktime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import ch.myriam.parktime.fragments.FragmentHome;

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
            //injecter le fragment dans notre boite (fragment_container)
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentHome,null);
            fragmentTransaction.commit();
        }

    }
}