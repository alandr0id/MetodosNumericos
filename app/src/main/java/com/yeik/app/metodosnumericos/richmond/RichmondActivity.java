package com.yeik.app.metodosnumericos.richmond;

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
import com.yeik.app.metodosnumericos.metodos.Richmond;
import com.yeik.app.metodosnumericos.metodos.Evaluator;

import java.util.Stack;

public class RichmondActivity extends AppCompatActivity {
    private EditText fxEditText, fdxEditText, fddxEditText, x0EditText, iterEditText, errorEditText;
    private Button evalButton;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richmond);

        fxEditText = (EditText) findViewById(R.id.richmond_fx_edit_text);
        fdxEditText = (EditText) findViewById(R.id.richmond_fdx_edit_text);
        fddxEditText = (EditText) findViewById(R.id.richmond_fddx_edit_text);
        x0EditText = (EditText) findViewById(R.id.richmond_x0_edit_text);
        errorEditText = (EditText) findViewById(R.id.richmond_error_edit_text);
        iterEditText = (EditText) findViewById(R.id.richmond_iter_edit_text);
        evalButton = (Button) findViewById(R.id.richmond_eval_button);
        recycler = (RecyclerView) findViewById(R.id.richmond_recycler);

        setTitle("Richmond");

        evalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unfocusViews();

                if (isValid()) {
                    String fx = fxEditText.getText().toString();
                    String fdx = fdxEditText.getText().toString();
                    String fddx = fddxEditText.getText().toString();
                    double x0 = Double.parseDouble(x0EditText.getText().toString());
                    double error = Double.parseDouble(errorEditText.getText().toString());
                    int iter = Integer.parseInt(iterEditText.getText().toString());

                    try {
                        Evaluator evaluator = new Evaluator(error, iter);
                        Stack<Richmond> result = evaluator.evaluate(new Richmond(fx, fdx, fddx, x0));
                        LinearLayoutManager layoutManager = new LinearLayoutManager(RichmondActivity
                                .this);
                        recycler.setAdapter(new RichmondAdapter(result));
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
        fdxEditText.clearFocus();
        fddxEditText.clearFocus();
        x0EditText.clearFocus();
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
        if (!isDouble(errorEditText)) valid = false;

        if (isVoid(fxEditText)) valid = false;
        if (isVoid(fdxEditText)) valid = false;
        if (isVoid(fddxEditText)) valid = false;
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
