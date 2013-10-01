package com.turingemul;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by loredan on 01.10.13.
 */
public class AddCommandFragment extends DialogFragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.add_command_dialog, null);
        return view;
    }
}
