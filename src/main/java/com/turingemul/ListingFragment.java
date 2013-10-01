package com.turingemul;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Loredan on 02.09.13.
 */
public class ListingFragment extends Fragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle)
    {
        super.onCreateView(inflater, group, bundle);
        return inflater.inflate(R.layout.listing, null);
    }
}