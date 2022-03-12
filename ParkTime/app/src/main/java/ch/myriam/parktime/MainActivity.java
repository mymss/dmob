package ch.myriam.parktime;

import androidx.appcompat.app.AppCompatActivity;


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
    Button btn_add, btn_ViewAll;
    EditText et_name, et_age;
    ListView lv_customerListe;

  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add = findViewById(R.id.btn_add);
        btn_ViewAll = findViewById(R.id.btn_ViewAll);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        lv_customerListe = findViewById(R.id.lv_listView_AllUsers);
        // button listeners for the add and view all buttons
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    CustomerModel customerModel;
                try{
                    customerModel = new CustomerModel(-1,et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()));
                    Toast.makeText(MainActivity.this,customerModel.toString(),Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this,"Error creating customer, the age must be a number",Toast.LENGTH_SHORT).show();
                    customerModel= new CustomerModel(-1,"error",0);
                }
                DataBaseParkTime dataBaseParkTime = new DataBaseParkTime(MainActivity.this);
                boolean success = dataBaseParkTime.addOne(customerModel);
                Toast.makeText(MainActivity.this,"Success ="+ success,Toast.LENGTH_SHORT).show();
            }
        });

        btn_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseParkTime dataBaseParkTime = new DataBaseParkTime(MainActivity.this);
                List<CustomerModel> everyone = dataBaseParkTime.getEveryone();
                ArrayAdapter customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1,everyone);
                lv_customerListe.setAdapter(customerArrayAdapter);
            }

        });
    }
}
