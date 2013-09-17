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
    ArrayAdapter<String> adapter;

    public static ListingFragment newInstance()
    {
        ListingFragment fragment = new ListingFragment();
        //fragment.adapter = new ArrayAdapter<String>(fragment.getActivity(), android.R.layout.simple_list_item_1);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle)
    {
        super.onCreateView(inflater, group, bundle);
        //ListView view = (ListView) inflater.inflate(R.layout.listing, null);

        //view.setAdapter(adapter);
        return inflater.inflate(R.layout.listing, null);
    }
}