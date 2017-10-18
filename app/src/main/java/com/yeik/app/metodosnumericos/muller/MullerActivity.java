package com.yeik.app.metodosnumericos.muller;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.yeik.app.metodosnumericos.R;
import com.yeik.app.metodosnumericos.metodos.Evaluator;
import com.yeik.app.metodosnumericos.metodos.Muller;

import java.util.Stack;

public class MullerActivity extends AppCompatActivity {
    private EditText fxEditText, x0EditText, x1EditText, x2EditText, errorEditText, iterEditText;
    private Button evalButton;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muller);

        fxEditText = (EditText) findViewById(R.id.muller_fx_edit_text);
        x0EditText = (EditText) findViewById(R.id.muller_x0_edit_text);
        x1EditText = (EditText) findViewById(R.id.muller_x1_edit_text);
        x2EditText = (EditText) findViewById(R.id.muller_x2_edit_text);
        errorEditText = (EditText) findViewById(R.id.muller_error_edit_text);
        iterEditText = (EditText) findViewById(R.id.muller_iter_edit_text);
        evalButton = (Button) findViewById(R.id.muller_eval_button);
        recycler = (RecyclerView) findViewById(R.id.muller_recycler);

        setTitle("Muller");

        evalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unfocusViews();

                if (isValid()) {
                    String fx = fxEditText.getText().toString();
                    double x0 = Double.parseDouble(x0EditText.getText().toString());
                    double x1 = Double.parseDouble(x1EditText.getText().toString());
                    double x2 = Double.parseDouble(x2EditText.getText().toString());
                    double error = Double.parseDouble(errorEditText.getText().toString());
                    int iter = Integer.parseInt(iterEditText.getText().toString());

                    try {
                        Evaluator evaluator = new Evaluator(error, iter);
                        Stack<Muller> result = evaluator.evaluate(new Muller(fx, x0, x1, x2));

                        LinearLayoutManager layoutManager = new LinearLayoutManager(MullerActivity.this);
                        recycler.setAdapter(new MullerAdapter(result));
                        recycler.setLayoutManager(layoutManager);
                        recycler.addItemDecoration(new DividerItemDecoration(recycler.getContext()
                                , layoutManager.getOrientation()));
                    } catch (Exception e) {
                        Snackbar.make(view, "Error", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void unfocusViews() {
        fxEditText.clearFocus();
        x0EditText.clearFocus();
        x1EditText.clearFocus();
        x2EditText.clearFocus();
        errorEditText.clearFocus();
        iterEditText.clearFocus();
        recycler.requestFocus();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(fxEditText.getWindowToken(), 0);
    }

    private boolean isValid() {
        boolean valid = true;

        if (!isInteger(iterEditText)) valid = false;

        if (!isDouble(x0EditText)) valid = false;
        if (!isDouble(x1EditText)) valid = false;
        if (!isDouble(errorEditText)) valid = false;

        if (isVoid(fxEditText)) valid = false;
        if (isVoid(x1EditText)) valid = false;
        if (isVoid(x0EditText)) valid = false;
        if (isVoid(iterEditText)) valid = false;
        if (isVoid(errorEditText)) valid = false;

        return valid;
    }

    private boolean isVoid(EditText view) {
        String eval = view.getText().toString();

        if (eval.trim().isEmpty()) {
            view.setError(getString(R.string.error_vacio));
            return true;
        } else {
            return false;
        }
    }

    private boolean isInteger(EditText view) {
        String eval = view.getText().toString();

        try {
            Integer.parseInt(eval);
            return true;
        } catch (Exception e) {
            view.setError(getString(R.string.error_no_valido));
            return false;
        }
    }

    private boolean isDouble(EditText view) {
        String eval = view.getText().toString();

        try {
            Double.parseDouble(eval);
            return true;
        } catch (Exception e) {
            view.setError(getString(R.string.error_no_valido));
            return false;
        }
    }
}
