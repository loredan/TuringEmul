package com.turingemul;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Activity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.RelativeSizeSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProgramActivity extends Activity
{
    public Program program;
    public ListingFragment listingFragment;
    private FragmentTransaction ftrans;
    final String COMMAND_LINE = "command_line";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        program = new Program();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program);
//        listingFragment = ListingFragment.newInstance();
//        ftrans = getFragmentManager().beginTransaction();
//        ftrans.add(R.id.parent, listingFragment);
//        ftrans.commit();
        ArrayList<SpannedString> listing = program.listing();
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(listing.size());
        for(int i = 0; i < listing.size(); i++)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(COMMAND_LINE, listing.get(i));
            data.add(i, map);
        }
        //SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.listing_element, new String[]{COMMAND_LINE}, new int[]{R.id.instruction});
        ListingAdapter adapter = new ListingAdapter(this, program);
        Fragment frag = getFragmentManager().findFragmentById(R.id.listing);
        ListView view = (ListView) frag.getView().findViewById(R.id.commandlist);
        //((TextView)findViewById(R.id.tv_test)).setText(listing.get(0));
        view.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.program, menu);
        return true;
    }

}