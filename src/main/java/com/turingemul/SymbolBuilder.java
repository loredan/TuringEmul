package com.turingemul;

import java.util.ArrayList;

/**
 * Created by loredan on 04.10.13.
 */
public class SymbolBuilder
{
    private class Letter
    {
        String base;
        boolean[] spans;

        public Letter(String _base)
        {
            base = _base;
            spans = new boolean[SpanStyles.spans.size()];
            for (int i = 0; i < SpanStyles.spans.size(); i++)
                spans[i] = false;
        }

        public void setSpan(int span, boolean state)
        {
            spans[span] = state;
        }

        public void setSpans(boolean[] _spans)
        {
            spans = _spans;
        }

        public boolean[] getSpans()
        {
            return spans;
        }
    }

    ArrayList<Letter> symbol;

    public SymbolBuilder()
    {
        symbol = new ArrayList<Letter>();
    }

    public SymbolBuilder(Symbol source)
    {
        String base = source.getBase();
        ArrayList<Symbol.SpanElement> spans = source.getSpans();
        for (int i = 0; i < source.getBase().length(); i++)
            symbol.add(i, new Letter(base.substring(i, i + 1)));
        for (Symbol.SpanElement span : spans)
            for (int i = span.getBegin(); i < span.getEnd(); i++)
                symbol.get(i).setSpan(span.getStyle(), true);
    }

    public void addLetter(int index, String base, boolean[] spans)
    {
        Letter letter = new Letter(base);
        letter.setSpans(spans);
        symbol.add(index, letter);
    }

    public void removeLetter(int index)
    {
        symbol.remove(index);
    }

    public String getBase()
    {
        String generator = "";
        for (Letter letter : symbol)
            generator = generator.concat(letter.base);
        return generator;
    }

    public boolean[] getLetterSpans(int index)
    {
        return symbol.get(index).getSpans();
    }

    public Symbol getSymbol()
    {

        Symbol generator = new Symbol(getBase());
        ArrayList<Symbol.SpanElement> spans = new ArrayList<Symbol.SpanElement>(
                SpanStyles.spans.size());
        for (Letter letter : symbol)
        {
            for (int style = 0; style < SpanStyles.spans.size(); style++)
            {
                Symbol.SpanElement span = spans.get(style);
                if (span != null)
                {
                    if (letter.getSpans()[style])
                        span.setEnd(span.getEnd() + 1);
                    else
                    {
                        generator.addSpan(span);
                        spans.set(style, null);
                    }
                }
                else if (letter.getSpans()[style])
                    spans.set(style, new Symbol.SpanElement(style, symbol.indexOf(letter),
                                                            symbol.indexOf(letter) + 1));
            }
        }
        for (Symbol.SpanElement span : spans)
        {
            generator.addSpan(span);
        }
        return generator;
    }

    public void changeSpan(int index, int span, boolean state)
    {
        symbol.get(index).setSpan(span, state);
    }
}
