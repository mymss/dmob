package ch.myriam.parktime;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class MainActivityAddImages2 extends AppCompatActivity {
    EditText editName,
            editDesc,
            editLoca,
            editRue;
    ImageView ImagetoAdd;
    Button buttonAdd;
    //elements de la db
    DataBaseParkTime db;
    String nameDB, descBD, locaDB, rueDB;
    Bitmap imageDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_images2);

        editName = (EditText) findViewById(R.id.editpark_name);
        editDesc =  (EditText) findViewById(R.id.editpark_desc);
        editLoca =  (EditText) findViewById(R.id.editpark_localisation);
        editRue = (EditText) findViewById(R.id.editpark_rue);
        ImagetoAdd = (ImageView) findViewById(R.id.imageToAdd);
        buttonAdd = (Button) findViewById(R.id.addParkButton);
        db = new DataBaseParkTime(this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String desc = editDesc.getText().toString();
                String loca = editLoca.getText().toString();
                String rue = editRue.getText().toString();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.franchises3seve);
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] img = byteArray.toByteArray();

                ImagesModel imagesModel = new ImagesModel(-1,name, desc,rue,loca,img);

                boolean insert = db.insertImages(imagesModel);
                if(insert){
                    Toast.makeText(MainActivityAddImages2.this, "Park saved", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivityAddImages2.this, "Park not saved", Toast.LENGTH_SHORT).show();
                }
                imageDB = db.getImage(name);
                nameDB =  db.getNameImage(name);
                ImagetoAdd.setImageBitmap(imageDB);
            }
        });
    }
}