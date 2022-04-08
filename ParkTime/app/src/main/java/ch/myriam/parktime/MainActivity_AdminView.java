package ch.myriam.parktime;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_AdminView extends AppCompatActivity {
    Button btn_add, btn_goHome;
    TextView nbreParticipantOutput;
    DataBaseParkTime db;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin_view);
        btn_add = findViewById(R.id.btn_GoToAddPark);
        btn_goHome = findViewById(R.id.btn_GotoActivityHome);
        nbreParticipantOutput = findViewById(R.id.sortieNbre);
        db = new DataBaseParkTime(this);
        int queryNbreParticipants = db.getEveryone().size();
        nbreParticipantOutput.setText(queryNbreParticipants + " utilisateurs de l'application ! ");
    }

    public void GoToAddPark(View v) {
        Intent i = new Intent(this, MainActivityAddImages2.class);
        startActivity(i);
    }

    public void backHome(View v) {
        Intent i = new Intent(this, MainActivity_Home.class);
        startActivity(i);
    }

    public void GoToCreateEvent(View v){
        Intent i = new Intent(this, MainActivity_CreateEvent.class);
        startActivity(i);
    }
}