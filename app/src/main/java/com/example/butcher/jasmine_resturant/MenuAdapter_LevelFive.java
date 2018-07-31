package com.example.butcher.jasmine_resturant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuAdapter_LevelFive extends BaseAdapter {


    Context context;

    ArrayList<Contacts> data;
    DatabaseHandler db;


    public MenuAdapter_LevelFive(Context context, ArrayList<Contacts> arraylist) {
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

        final TextView id, title, desc, pric, quan, totpric;
        Button button;
        ImageView imglink;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.menu_levelfive_item, parent, false);


        title = (TextView) itemView.findViewById(R.id.textView7);
        id = (TextView) itemView.findViewById(R.id.ids);
        desc = (TextView) itemView.findViewById(R.id.textView3);
        pric = (TextView) itemView.findViewById(R.id.textView10);
        quan = (TextView) itemView.findViewById(R.id.textView12);
        totpric = (TextView) itemView.findViewById(R.id.tot);
        button = (Button) itemView.findViewById(R.id.textView4);

        imglink = (ImageView) itemView.findViewById(R.id.imageView5);

        db = new DatabaseHandler(context);

        title.setText(data.get(position).getTitle());
        id.setText(String.valueOf(data.get(position).getID()));
        desc.setText(data.get(position).getDesc());
        quan.setText(data.get(position).getQuantity());
        pric.setText(data.get(position).getPrice());
        totpric.setText(data.get(position).getTotal());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contacts c = new Contacts(Integer.parseInt(id.getText().toString()), title.getText().toString(), desc.getText().toString(), quan.getText().toString(), pric.getText().toString(), totpric.getText().toString());
                db.deleteContact(c);
                MenuAdapter_LevelFive.this.removeobject(position);
                MenuAdapter_LevelFive.this.notifyDataSetChanged();
            }
        });
        return itemView;


    }


    public void removeobject(int pos) {
        this.data.remove(pos);
    }

}
