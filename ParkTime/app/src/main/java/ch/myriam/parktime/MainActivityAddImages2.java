package ch.myriam.parktime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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
    Button buttonAdd, buttonChooser;
    //elements de la db
    DataBaseParkTime db;
    String nameDB, descBD, locaDB, rueDB;
    Bitmap imageDB;
    // Permission
    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1001;

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
        buttonChooser = (Button) findViewById(R.id.btn_chooseImage);
        db = new DataBaseParkTime(this);

        buttonChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check runtime permission
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED) {
                        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_CODE);
                    }
                    else{
                        pickImageFromGallery();

                    }
                }
                else{
                    pickImageFromGallery();
                }
            }
        });

        onActivityResult(IMAGE_PICK_CODE,PERMISSION_CODE,null);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String desc = editDesc.getText().toString();
                String loca = editLoca.getText().toString();
                String rue = editRue.getText().toString();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.franchises3seve);
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                imageDB.compress(Bitmap.CompressFormat.PNG,100,byteArray);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    //permission was denied
                    Toast.makeText(MainActivityAddImages2.this,"Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //set image to image view
            ImagetoAdd.setImageURI(data.getData());
            Uri selectedImage = data.getData();
            String[] filePathColum = { MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,filePathColum,null,null,null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColum[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            imageDB = BitmapFactory.decodeFile(filePath);

        }
    }
}