package ch.myriam.parktime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DataBaseParkTime extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COL_CUS_NAME = "CUS_NAME";
    public static final String COL_CUS_AGE = "CUS_AGE";
    public static final String COL_CUS_ID = "CUS_ID";

    public DataBaseParkTime(@Nullable Context context) {
        super(context, "parktime.db", null, 1);
    }

    //this is called the first time a database is accessed. There should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COL_CUS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CUS_NAME + " TEXT, " + COL_CUS_AGE + " INT)";

        sqLiteDatabase.execSQL(createTableStatement);
    }
    // this is called if the database version number changed. It prevents previous users apps for breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addOne(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_CUS_NAME,customerModel.getName());
        cv.put(COL_CUS_AGE, customerModel.getAge());
        long insert = db.insert(CUSTOMER_TABLE,null,cv);
        if(insert ==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public List<CustomerModel> getEveryone() {
        List<CustomerModel> returnList = new ArrayList<>();
        String queryCustomer = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(queryCustomer,null);
        if(cursor.moveToFirst()){
            //loop through the cursor(result set) and create new customer objects. Put them into the returne list.
            do{
                int customerId = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);

                CustomerModel newcustomer = new CustomerModel(customerId,customerName,customerAge);
                returnList.add(newcustomer);
            } while(cursor.moveToNext());
        }

        else{
            // failure do not add anything in the database
        }
        // close the cursor and the db when done.
        cursor.close();
        db.close();
        return returnList;
    }
}
