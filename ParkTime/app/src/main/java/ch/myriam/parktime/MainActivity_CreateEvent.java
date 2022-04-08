package ch.myriam.parktime;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_CreateEvent extends AppCompatActivity {
    Spinner spinnerPark;
    EditText event_desc,event_date;
    Button add;
    DataBaseParkTime db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create_event);

        spinnerPark = findViewById(R.id.spinnerPark);
        event_desc = findViewById(R.id.createEvent_desc);
        event_date = findViewById(R.id.createEvent_date);
        add = findViewById(R.id.addEventButton);
        db = new DataBaseParkTime(this);
        ArrayList<ImagesModel> array= (ArrayList<ImagesModel>) db.getAllParks();
        getNameOfParks();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String desc = event_desc.getText().toString();
                String v =  spinnerPark.getSelectedItem().toString();
                String event = event_date.getText().toString();
                db.getWritableDatabase();
                db.getSelectedItemId(v);
                EventModel eventModel = new EventModel(-1,parseInt(v),desc,event);
                Boolean insert = db.createEvent(eventModel);
                if(insert){
                    Toast.makeText(MainActivity_CreateEvent.this, "Création de l'event effectuée", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity_AdminView.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity_CreateEvent.this, "Création de l'évènement échouée", Toast.LENGTH_SHORT).show();
                }
            }
        });
    };
    public ArrayList<ImagesModel> getNameOfParks(){
        ArrayList<ImagesModel> names = new ArrayList<>();
        List liste = db.getAllParks();

        for (int i = 0; i < liste.size(); i++) {
            names.add((ImagesModel) liste.get(i));
        }
        System.out.println(names);
        return names;
    }
    }
