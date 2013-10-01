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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProgramActivity extends Activity
{
    public DisplayMetrics metrics;
    public Program program;

    public float prevX = 0;
    public float prevY = 0;
    public static final int NONE = 0;
    public static final int INNER = 1;
    public static final int OUTER = 2;
    public int selected = NONE;
    private float relativeX;
    private float relativeY;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        SpanStyles.init();
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        program = new Program();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program);
        ListingAdapter listingAdapter = new ListingAdapter(this, program);
        Fragment frag = getFragmentManager().findFragmentById(R.id.listing);
        findViewById(R.id.inner_alphabet).setTranslationX((float) metrics.widthPixels / 2);
        ListView listView = (ListView) frag.getView().findViewById(R.id.commandlist);
        listView.setAdapter(listingAdapter);
        AlphabetAdapter innerAlphabetAdapter = new AlphabetAdapter(this, program,
                                                                   AlphabetAdapter.TYPE_INNER);
        frag = getFragmentManager().findFragmentById(R.id.inner_alphabet);
        GridView gridView = (GridView) frag.getView().findViewById(R.id.alphabet);
        gridView.setAdapter(innerAlphabetAdapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d("MotionEvent", "ACTION_DOWN");
                if (isInside(event.getX(), event.getY(), findViewById(R.id.inner_alphabet)))
                {
                    selected = INNER;
                    relativeX = event.getX() - findViewById(R.id.inner_alphabet).getLeft();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("MotionEvent", "ACTION_MOVE");
                moveFragment(selected, event, relativeX);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("MotionEvent", "ACTION_UP");
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.program, menu);
        return true;
    }

    private boolean isInside(float x, float y, View view)
    {
        if (view.getLeft() <= x && x <= view.getRight())
            if (view.getTop() <= y && y <= view.getBottom())
            {
                Log.d("Point inside", "true");
                return true;
            }
        Log.d("Point inside", "false");
        return false;
    }

    private void moveFragment(int selected, MotionEvent event, float relativeX)
    {
        View fragment;
        switch (selected)
        {
            case INNER:
                fragment = findViewById(R.id.inner_alphabet);
                if (event.getX() - relativeX >= 0 &&
                    event.getX() - relativeX + R.dimen.sidebar <= metrics.widthPixels)
                {
                    fragment.setTranslationX(event.getX() - relativeX);
                }
                else
                {
                    relativeX = event.getX() - fragment.getLeft();
                }
        }
    }
}