package com.turingemul;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by loredan on 24.09.13.
 */
public class AlphabetAdapter extends BaseAdapter
{
    public static final int TYPE_INNER = 0;
    public static final int TYPE_OUTER = 1;

    private ArrayList<Symbol> alphabet;
    private Context context;
    private LayoutInflater lInflater;

    public AlphabetAdapter(Context _context, Program program, int type)
    {
        alphabet = type==TYPE_INNER ? program.inAB : program.outAB;
        context = _context;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return alphabet.size();
    }

    @Override
    public Object getItem(int i)
    {
        return alphabet.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent)
    {
        if (view==null)
            view = lInflater.inflate(R.layout.alphabet_element, parent, false);
        ((Button) view.findViewById(R.id.symbol)).setText(alphabet.get(i).getSymbol());
        return view;
    }
}
