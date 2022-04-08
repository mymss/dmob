package ch.myriam.parktime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.List;


public class DataBaseParkTime extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COL_CUS_NAME = "CUS_NAME";
    public static final String COL_CUS_AGE = "CUS_AGE";
    public static final String COL_CUS_USERNAME = "CUS_USERNAME";
    public static final String COL_CUS_NbreEnfants = "CUS_NbreEnfants";
    public static final String COL_CUS_LOCALITE = "CUS_LOCALITE";
    public static final String COL_CUS_MDP= "CUS_MDP";
    public static final String COL_CUS_ID = "CUS_ID";

    public static final String IMAGES_TABLE = "IMAGES_TABLE";
    public static final String COL_IMG_ID = "IMG_ID";
    public static final String COL_IMG_NAME= "IMG_NAME";
    public static final String COL_IMG_DESC = "IMG_DESC";
    public static final String COL_IMG_LOCALITE = "IMG_LOCALITE";
    public static final String COL_IMG_RUE = "IMG_RUE";
    public static final String COL_IMG_BLOB = "IMG_BLOB";

    public static final String EVENT_TABLE = "EVENT_TABLE";
    public static final String EVENT_ID = "EVENT_ID";
    public static final String EVENT_PARK_ID = "EVENT_PARK_ID";
    public static final String EVENT_DESC= "EVENT_DESC";
    public static final String EVENT_DATE= "EVENT_DATE";


    SQLiteDatabase db = this.getWritableDatabase();

    public DataBaseParkTime(Context context) {
        super(context, "parktime2.db", null, 15);
    }
    //this is called the first time a database is accessed. There should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE+" (" + COL_CUS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CUS_NAME + " TEXT, " + COL_CUS_AGE + " INT, " + COL_CUS_USERNAME+ " TEXT, "+ COL_CUS_LOCALITE + " INT, "+ COL_CUS_NbreEnfants + " INT, "+ COL_CUS_MDP+ " TEXT)";
        String createTableImage = "CREATE TABLE "+ IMAGES_TABLE+"(" + COL_IMG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL_IMG_NAME+" TEXT, " + COL_IMG_DESC+" TEXT," +COL_IMG_LOCALITE+" INT, " +COL_IMG_RUE+" TEXT,"+COL_IMG_BLOB+" blob)";
        String createTableEvent = "CREATE TABLE "+ EVENT_TABLE+"("+ EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ EVENT_PARK_ID+" INTEGER, "+ EVENT_DESC +" TEXT,"+ EVENT_DATE +" TEXT)";

        sqLiteDatabase.execSQL(createTableImage);
        sqLiteDatabase.execSQL(createTableStatement);
        sqLiteDatabase.execSQL(createTableEvent);

    }
    // this is called if the database version number changed. It prevents previous users apps for breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("drop table if exists IMAGES_TABLE");
       sqLiteDatabase.execSQL("drop table if exists CUSTOMER_TABLE");
        sqLiteDatabase.execSQL("drop table if exists CUSTOMER_TABLE");
       onCreate(db);
    }
    public boolean insertImages(ImagesModel imagesModel){
        ContentValues contentValuesImages = new ContentValues();
        contentValuesImages.put(COL_IMG_NAME, imagesModel.getName());
        contentValuesImages.put(COL_IMG_DESC,imagesModel.getDesc());
        contentValuesImages.put(COL_IMG_LOCALITE,imagesModel.getLocalite());
        contentValuesImages.put(COL_IMG_RUE,imagesModel.getRue());
        contentValuesImages.put(COL_IMG_BLOB,imagesModel.getImg());
        long insert = db.insert(IMAGES_TABLE,null,contentValuesImages);
        if(insert ==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public String getNameImage(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + IMAGES_TABLE + " WHERE IMG_NAME = ? ", new String[]{name});
        cursor.moveToFirst();
        return cursor.getString(0);
    }
    public Bitmap getImage(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + IMAGES_TABLE + " WHERE IMG_NAME = ? ", new String[]{name});
        cursor.moveToFirst();
        byte[] bitmap = cursor.getBlob(1);
        Bitmap image = BitmapFactory.decodeByteArray(bitmap,0,bitmap.length);
        return image;
    }

    public List<ImagesModel> getAllParks(){
        List<ImagesModel> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String queryPark = "SELECT * FROM " + IMAGES_TABLE;
        Cursor cursor = db.rawQuery(queryPark,null);

        if(cursor.moveToFirst()){
            //loop through the cursor(result set) and create new customer objects. Put them into the returne list.
            do{
                int parkId = cursor.getInt(0);
                String parkName = cursor.getString(1);
                String parkDesc = cursor.getString(2);
                String parkLocalite = cursor.getString(3);
                String parkRue = cursor.getString(4);
                byte[] bitmap = cursor.getBlob(5);

                    while (bitmap.length > 500000) {
                        Bitmap bitmap1 = BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length);
                        Bitmap resized = Bitmap.createScaledBitmap(bitmap1, (int) (bitmap1.getWidth() * 0.8), (int) (bitmap1.getHeight() * 0.8), true);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        resized.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        bitmap = stream.toByteArray();
                    }
                ImagesModel newPark = new ImagesModel(parkId,parkName,parkDesc,parkLocalite,parkRue,bitmap);
                list.add(newPark);
            } while(cursor.moveToNext());
        }
        else{
            // failure do not add anything in the database
        }
        // close the cursor and the db when done.
        cursor.close();
        db.close();
        return list;
    }

    public boolean addOne(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_CUS_NAME,customerModel.getName());
        cv.put(COL_CUS_AGE, customerModel.getAge());
        cv.put(COL_CUS_USERNAME, customerModel.getUsername());
        cv.put(COL_CUS_LOCALITE, customerModel.getLocalite());
        cv.put(COL_CUS_NbreEnfants, customerModel.getNbreEnfants());
        cv.put(COL_CUS_MDP, customerModel.getMdp());

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
                String customerUsername = cursor.getString(3);
                int customerLocalite = cursor.getInt(4);
                int customerNbreEnfants = cursor.getInt(5);
                String mdp = cursor.getString(6);
                CustomerModel newcustomer = new CustomerModel(customerId,customerName,customerAge,customerUsername,customerLocalite,customerNbreEnfants,mdp);
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

    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM CUSTOMER_TABLE WHERE CUS_USERNAME = ? ", new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }

    public Boolean checkUsernamepassword(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("SELECT * FROM CUSTOMER_TABLE  WHERE CUS_USERNAME = ? AND CUS_MDP = ? ", new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public String getSelectedItemId(String value){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("SELECT IMG_ID FROM IMAGES_TABLE  WHERE IMG_NAME = ? ", new String[]{value});
        return value;
    }
    public boolean createEvent(EventModel eventModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EVENT_PARK_ID,eventModel.event_park_id);
        cv.put(EVENT_DESC, eventModel.event_desc);
        cv.put(EVENT_DATE, eventModel.event_date);

        long insert = db.insert(EVENT_TABLE,null,cv);
        if(insert ==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public List<EventModel> getAllEvents() {
        List<EventModel> returnList = new ArrayList<>();
        String queryCustomer = "SELECT * FROM " + EVENT_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(queryCustomer,null);

        if(cursor.moveToFirst()){
            do{
                int event_id = cursor.getInt(0);
                int park_event_id = cursor.getInt(1);
                String event_desc = cursor.getString(2);
                String event_date = cursor.getString(3);
                EventModel newEvent= new EventModel(event_id,park_event_id,event_desc,event_date);
                returnList.add(newEvent);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }




}
