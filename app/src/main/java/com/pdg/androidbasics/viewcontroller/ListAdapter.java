package com.pdg.androidbasics.viewcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.pdg.androidbasics.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<String> values;

    public ListAdapter(Context context, ArrayList<String> values){

        this.context = context;
        this.values = values;
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public boolean hasStableIds() {
        return super.hasStableIds();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item_row, parent,false);

        TextView nameTV = rowView.findViewById(R.id.rowNameLabel);
        nameTV.setText(values.get(position));

        return rowView;
    }
}