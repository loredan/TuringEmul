package com.turingemul;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by loredan on 01.10.13.
 */
public class AddSymbolFragment extends DialogFragment
{
    public Symbol symbol;
    SpanStylesAdapter adapter;
    TextView display;
    TextField edit;

    private class TextField extends EditText
    {
        public SymbolBuilder builder;

        public TextField(Context context)
        {
            super(context);
            builder = new SymbolBuilder();
        }

        public TextField(Context context, Symbol source)
        {
            super(context);
            builder = new SymbolBuilder(source);
        }

        @Override
        protected void onTextChanged(CharSequence text, int start, int lengthBefore,
                                     int lengthAfter)
        {
            super.onTextChanged(text, start, lengthBefore, lengthAfter);
            if (lengthAfter < lengthBefore)
            {
                for (int index = start + lengthBefore - 1; index >= start + lengthAfter; index--)
                {
                    builder.removeLetter(index);
                }
            }
            else
            {
                for (int index = start + lengthBefore; index < start + lengthAfter; index++)
                {
                    builder.addLetter(index, String.valueOf(text.charAt(index)), getSpans());
                }
            }
            updateDisplay();
        }

        @Override
        protected void onSelectionChanged(int selStart, int selEnd)
        {
            super.onSelectionChanged(selStart, selEnd);
            setSpans(builder.getLetterSpans(selStart));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        symbol = new Symbol("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.add_symbol_dialog, null);
        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(edit.getSelectionEnd()-edit.getSelectionStart()>0)
                    for (int letter = edit.getSelectionStart(); letter < edit.getSelectionEnd();
                         letter++)
                        edit.builder.changeSpan(letter, adapter.buttons.indexOf(view),
                                                ((ToggleButton) view).isChecked());
            }
        };
        adapter = new SpanStylesAdapter(getActivity().getApplicationContext(), listener);
        display = (TextView) view.findViewById(R.id.display);
        edit = (TextField) view.findViewById(R.id.edit);
        ((GridView) view.findViewById(R.id.spans)).setAdapter(adapter);
        return view;
    }

    private boolean[] getSpans()
    {
        boolean[] spans = new boolean[adapter.buttons.size()];
        for(ToggleButton button : adapter.buttons)
            spans[adapter.buttons.indexOf(button)] = button.isChecked();
        return spans;
    }

    private void setSpans(boolean[] spans)
    {
        for(ToggleButton button : adapter.buttons)
            button.setChecked(spans[adapter.buttons.indexOf(button)]);
    }

    private void updateDisplay()
    {
        display.setText(edit.builder.getSymbol().getSymbol());
    }
}
