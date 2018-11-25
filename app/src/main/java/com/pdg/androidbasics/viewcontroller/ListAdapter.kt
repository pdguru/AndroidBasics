package com.pdg.androidbasics.viewcontroller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.pdg.androidbasics.R
import com.pdg.androidbasics.model.Item

import java.util.ArrayList

class ListAdapter(private val context: Context, private val values: ArrayList<Item>) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return values[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return values.size
    }

    override fun hasStableIds(): Boolean {
        return super.hasStableIds()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val inflater = LayoutInflater.from(context)
        val rowView = inflater.inflate(R.layout.list_item_row, parent, false)

        val nameTV = rowView.findViewById<TextView>(R.id.rowNameLabel)
        nameTV.text = values[position].name

        val subtitleTV = rowView.findViewById<TextView>(R.id.rowSubtitleLabel)
        subtitleTV.text = values[position].subtitle

        val priceTV = rowView.findViewById<TextView>(R.id.rowPriceLabel)
        priceTV.text = "" + values[position].price

        return rowView
    }
}