package ch.myriam.parktime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Home extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private List<ImagesModel> parkImages ;
    private ImageParkAdapter adapter;
    private static String TAG="Parks : " ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        DataBaseParkTime dataBaseParkTime = new DataBaseParkTime(this);
        viewPager2 = findViewById(R.id.viewPager2);
        parkImages = dataBaseParkTime.getAllParks();
        Log.d(TAG,"On create : "+ parkImages.toString());

/*
        parkImages.add(new ParkImages(R.drawable.franchises3seve));
        parkImages.add(new ParkImages(R.drawable.pateaugeoirebertrandcover));
        parkImages.add(new ParkImages(R.drawable.roseraie_parc_la_grange));
*/
        viewPager2.setPageTransformer(new ViewPager2Page());
        adapter = new ImageParkAdapter(parkImages, viewPager2);
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

    }


}
