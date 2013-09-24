package com.turingemul;

import android.text.Spannable;
import android.text.SpannedString;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;

/**
 * Created by Loredan on 02.09.13.
 */
public class Program
{
    public ArrayList<Symbol> inAB;
    public ArrayList<Symbol> outAB;
    public Map<String, String> commands;

    public Program()
    {
        Symbol temp;
        inAB = new ArrayList<Symbol>();
        outAB = new ArrayList<Symbol>();

        temp = new Symbol("\u03a9"); //omega
        inAB.add(0, temp);

        temp = new Symbol("S0");
        temp.addSpan(Symbol.SUBSCRIPT, 1, 2);
        //temp.setSpan(new SubscriptSpan(), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //temp.setSpan(new RelativeSizeSpan((float) 0.75), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        inAB.add(1, temp);

        temp = new Symbol("\u03bb"); //lambda
        outAB.add(0, temp);

        temp = new Symbol("\u2202"); //delta
        outAB.add(1, temp);

        commands = new LinkedHashMap<String, String>();
        commands.put("11", "1R1");
    }

    public ArrayList<SpannedString> listing()
    {
        ArrayList<SpannedString> list = new ArrayList<SpannedString>();
        for (String key : commands.keySet())
        {
            String value = commands.get(key);
            SpannedString line = (SpannedString) TextUtils.concat(
                    inAB.get(Integer.parseInt(key.substring(0, 1))).getSymbol(),
                    outAB.get(Integer.parseInt(key.substring(1, 2))).getSymbol(), "\u2192",
                    outAB.get(Integer.parseInt(value.substring(0, 1))).getSymbol(),
                    value.substring(1, 2),
                    inAB.get(Integer.parseInt(value.substring(2, 3))).getSymbol());
            list.add(line);
        }
        return list;
    }

    public SpannedString get(int position) throws IndexOutOfBoundsException
    {
        if(commands.size()<=position)
            throw new IndexOutOfBoundsException();
        String key = (String) commands.keySet().toArray()[0];
        String value;
        for (String _key: commands.keySet())
        {
            if (position==0)
            {
                key = _key;
                break;
            }
            position--;
        }
        value = commands.get(key);
        SpannedString line = (SpannedString) TextUtils.concat(
                inAB.get(Integer.parseInt(key.substring(0, 1))).getSymbol(),
                outAB.get(Integer.parseInt(key.substring(1, 2))).getSymbol(), "\u2192",
                outAB.get(Integer.parseInt(value.substring(0, 1))).getSymbol(),
                value.substring(1, 2),
                inAB.get(Integer.parseInt(value.substring(2, 3))).getSymbol());
        return line;
    }

    public void add(String state, String action)
    {
        commands.put(state, action);
    }
}