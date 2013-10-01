package com.turingemul;

import java.util.ArrayList;

/**
 * Created by loredan on 01.10.13.
 */
public class SpanStyles
{
    public static final int SUPERSCRIPT = 0;
    public static final int SUBSCRIPT = 1;
    public static final int BOLD = 2;
    public static final int ITALIC = 3;
    public static final int UNDERLINE = 4;
    public static ArrayList<Symbol> spans;

    public static void init()
    {
        Symbol symbol;

        symbol = new Symbol("sp");
        symbol.addSpan(SUPERSCRIPT, 0, 2);
        spans.add(symbol);

        symbol = new Symbol("sb");
        symbol.addSpan(SUBSCRIPT, 0, 2);
        spans.add(symbol);

        symbol = new Symbol("B");
        symbol.addSpan(BOLD, 0, 1);
        spans.add(symbol);

        symbol = new Symbol("I");
        symbol.addSpan(ITALIC, 0, 1);
        spans.add(symbol);

        symbol = new Symbol("U");
        symbol.addSpan(UNDERLINE, 0, 1);
        spans.add(symbol);
    }
}
