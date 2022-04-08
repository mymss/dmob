package ch.myriam.parktime;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageParkAdapter extends RecyclerView.Adapter<ImageParkAdapter.ImageParkHolder> {

    private List<ImagesModel> imagesList;
    private ViewPager2 viewPager2;
    private KenBurnsView kenBurnsView;

    public ImageParkAdapter(List<ImagesModel> imagesList, ViewPager2 viewPager2){
        this.imagesList = imagesList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public ImageParkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal_parks,parent,false);
        return new ImageParkHolder(view);
    }
    public Bitmap getBitmap(byte[] decodedString){
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedBitmap;
    }
    @Override
    public void onBindViewHolder(@NonNull ImageParkHolder holder, int position) {
        holder.imageView.setImageBitmap(getBitmap(imagesList.get(position).getImg()));
        holder.textView.setText(imagesList.get(position).getName());
        holder.textlocation.setText(imagesList.get(position).getLocalite()+ " " + imagesList.get(position).getRue());
    }
    @Override
    public int getItemCount() {
        return imagesList.size() ;
    }

    public class ImageParkHolder extends RecyclerView.ViewHolder{
        KenBurnsView imageView;
        TextView textView, textlocation;
        public ImageParkHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textViewImg);
            textlocation = itemView.findViewById(R.id.textLocation);
        }
    }
}
