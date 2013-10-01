package com.turingemul;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by loredan on 24.09.13.
 */
public class AlphabetFragment extends Fragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle)
    {
        super.onCreateView(inflater, group, bundle);
        inflater.inflate(R.layout.alphabet, null).setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                return false;
            }
        });
        return inflater.inflate(R.layout.alphabet, null);
    }

    public void setOnClickAction(View.OnClickListener listener)
    {

    }
}
