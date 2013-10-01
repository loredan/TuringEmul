package com.turingemul;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loredan on 18.09.13.
 */
public class Symbol
{
    private String base;
    private List<SpanElement> spans;
    private static class SpanElement
    {
        private int style;
        private int begin;
        private int end;
        public SpanElement(int _style, int _begin, int _end)
        {
            style = _style;
            begin = _begin;
            end = _end;
        }
        public int getStyle()
        {
            return style;
        }
        public int getBegin()
        {
            return begin;
        }
        public int getEnd()
        {
            return end;
        }
    }
    public Symbol(String _base)
    {
        base = _base;
        spans = new ArrayList<SpanElement>();
    }
    public void addSpan(int style, int begin, int end)
    {
        SpanElement span = new SpanElement(style, begin, end);
        spans.add(span);
    }
    public Spannable getSymbol()
    {
        Spannable symbol = new SpannableString(base);
        for(SpanElement span: spans)
        {
            CharacterStyle style;
            switch (span.getStyle())
            {
                case SpanStyles.SUPERSCRIPT:
                    symbol.setSpan(new RelativeSizeSpan((float) 0.5), span.getBegin(), span.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    style = new SuperscriptSpan();
                    break;
                case SpanStyles.SUBSCRIPT:
                    symbol.setSpan(new RelativeSizeSpan((float) 0.5), span.getBegin(), span.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    style = new SubscriptSpan();
                    break;
                case SpanStyles.BOLD:
                    style = new StyleSpan(Typeface.BOLD);
                    break;
                case SpanStyles.ITALIC:
                    style = new StyleSpan(Typeface.ITALIC);
                    break;
                case SpanStyles.UNDERLINE:
                    style = new UnderlineSpan();
                    break;
                default:
                    continue;
            }
            symbol.setSpan(style, span.getBegin(), span.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return symbol;
    }
}
