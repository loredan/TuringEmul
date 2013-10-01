package com.turingemul;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by loredan on 01.10.13.
 */
public class AddSymbolFragment extends DialogFragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        inflater.inflate(R.layout.add_symbol_dialog, null);
        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        }
        SpanStylesAdapter adapter = new SpanStylesAdapter(getActivity().getApplicationContext());
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
