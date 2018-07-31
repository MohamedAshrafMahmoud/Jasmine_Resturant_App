package com.example.butcher.jasmine_resturant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuAdapter_levelFour extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;

    HashMap<String, String> resultp = new HashMap<String, String>();

    public MenuAdapter_levelFour(Context context, ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView title, desc;
        ImageView imglink;


        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.menu_levelfour_item, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        title = (TextView) itemView.findViewById(R.id.textView16);
        desc = (TextView) itemView.findViewById(R.id.textView17);

        // Locate the ImageView in listview_item.xml
        imglink = (ImageView) itemView.findViewById(R.id.imageView5);

        // Capture position and set results to the TextViews
        title.setText(resultp.get(Menu_levelFour.NAME));
        desc.setText(resultp.get(Menu_levelFour.DESCRIPTION));


        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
         Glide.with(context).load("http://54.191.156.65/restaurant/uploadedFiles/"+resultp.get(MenuParent.IMG)).into(imglink);


         return itemView;
    }

}
