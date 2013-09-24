package com.turingemul;

import android.content.Context;
import android.text.SpannedString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by loredan on 20.09.13.
 */
public class ListingAdapter extends BaseAdapter
{
    Program program;
    Context context;
    LayoutInflater lInflater;

    public ListingAdapter(Context _context, Program _program)
    {
        program = _program;
        context = _context;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return program.commands.size();
    }

    @Override
    public Object getItem(int i)
    {
        return program.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if (view==null)
            view = lInflater.inflate(R.layout.listing_element, parent, false);
        SpannedString command = program.get(i);
        ((TextView) view.findViewById(R.id.instruction)).setText(command);
        return view;
    }
}
