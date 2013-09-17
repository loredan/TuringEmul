package com.turingemul;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.SubscriptSpan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Loredan on 02.09.13.
 */
public class Program
{
    public ArrayList<SpannableString> inAB;
    public ArrayList<SpannableString> outAB;
    public Map<String, String> commands;

    public Program()
    {
        SpannableString temp;
        inAB = new ArrayList<SpannableString>();
        outAB = new ArrayList<SpannableString>();

        temp = new SpannableString("\u03a9"); //omega
        inAB.add(0, temp);

        temp = new SpannableString("S0");
        temp.setSpan(new SubscriptSpan(), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        temp.setSpan(new RelativeSizeSpan((float) 0.75), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        inAB.add(1, temp);

        temp = new SpannableString("\u03bb"); //lambda
        outAB.add(0, temp);

        temp = new SpannableString("\u2202"); //delta
        outAB.add(1, temp);

        commands = new HashMap<String, String>();
        commands.put("11", "1R1");
    }

    public ArrayList<SpannedString> listing()
    {
        ArrayList<SpannedString> list = new ArrayList<SpannedString>();
        for (String key : commands.keySet())
        {
            String value = commands.get(key);
            SpannedString line = (SpannedString) TextUtils.concat(inAB.get(Integer.parseInt(key.substring(0, 1))), outAB.get(Integer.parseInt(key.substring(1, 2))), "\u2192", outAB.get(Integer.parseInt(value.substring(0, 1))), value.substring(1, 2), inAB.get(Integer.parseInt(value.substring(2, 3))));
            list.add(line);
        }
        return list;
    }

    public void add(String state, String action)
    {
        commands.put(state, action);
    }
}