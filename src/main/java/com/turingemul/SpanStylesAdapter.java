package com.turingemul;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by loredan on 01.10.13.
 */
public class SpanStylesAdapter extends BaseAdapter
{
    Context context;
    ArrayList<ToggleButton> buttons;
    View.OnClickListener listener;

    public SpanStylesAdapter(Context _context, View.OnClickListener _listener)
    {
        context = _context;
        buttons = new ArrayList<ToggleButton>();
        listener = _listener;
    }

    @Override
    public int getCount()
    {
        return SpanStyles.spans.size();
    }

    @Override
    public Object getItem(int i)
    {
        return buttons.get(i);
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
            view = new ToggleButton(context);
        view.setOnClickListener(listener);
        view.setLayoutParams(new ViewGroup.LayoutParams(R.dimen.button, R.dimen.button));
        ((ToggleButton) view).setText(SpanStyles.spans.get(i).getSymbol());
        buttons.add(i, (ToggleButton) view);
        return null;
    }
}
