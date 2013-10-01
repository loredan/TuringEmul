package com.turingemul;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

/**
 * Created by loredan on 01.10.13.
 */
public class SpanStylesAdapter extends BaseAdapter
{
    LayoutInflater lInflater;

    public SpanStylesAdapter(Context context)
    {
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return SpanStyles.spans.size();
    }

    @Override
    public Object getItem(int i)
    {
        return SpanStyles.spans.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view==null)
            view = lInflater.inflate(R.layout.alphabet_element, null);
        ((Button) view.findViewById(R.id.symbol)).setText(SpanStyles.spans.get(i).getSymbol());
        return null;
    }
}
