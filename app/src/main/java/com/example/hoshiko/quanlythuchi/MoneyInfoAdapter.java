package com.example.hoshiko.quanlythuchi;

import android.content.Context;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DUNG on 4/2/2018.
 */

public class MoneyInfoAdapter extends ArrayAdapter<MoneyInfo> {

    public MoneyInfoAdapter(Context context, ArrayList<MoneyInfo> moneyInfos) {
        super(context, 0, moneyInfos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }


        // Get the {@link Word} object located at this position in the list
       MoneyInfo currentMoneyInfo = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID .
        TextView titleTextView =  listItemView.findViewById(R.id.txt_noidung);
        titleTextView.setText(currentMoneyInfo.getNoidung());


        TextView amountTextView = listItemView.findViewById(R.id.txt_sotien);
        amountTextView.setText(currentMoneyInfo.getSotien());

        TextView classifyTextView = listItemView.findViewById(R.id.txt_hinhthuc);
        ImageView thumbnailImage = listItemView.findViewById(R.id.thumbnail_image);
        String hinhthuc = null;
        int resourceImage = 0;
        // Nếu khoản tiền đó là tiền chi thì set hình chi.png & settext = chi
        if(currentMoneyInfo.getHinhthuc() == 0 ){
            hinhthuc = "Chi";
            resourceImage = R.drawable.chi;

        }
        // Nếu khoản tiền đó là tiền thu thì set hình thu.png & settext = thu
        else {
            hinhthuc = "Thu";
            resourceImage = R.drawable.thu;
        }
        classifyTextView.setText(hinhthuc);
        thumbnailImage.setImageResource(resourceImage);


        return listItemView;
    }

}




