package com.yeik.app.metodosnumericos.puntofijo;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
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
import com.yeik.app.metodosnumericos.metodos.PuntoFijo;

import java.util.Stack;

public class PuntoFijoActivity extends AppCompatActivity {
    private EditText gxEditText, x0EditText, iterEditText, errorEditText;
    private Button evalButton;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punto_fijo);

        gxEditText = (EditText) findViewById(R.id.gx_edit_text);
        x0EditText = (EditText) findViewById(R.id.x0_edit_text);
        errorEditText = (EditText) findViewById(R.id.error_edit_text);
        iterEditText = (EditText) findViewById(R.id.iter_edit_text);
        evalButton = (Button) findViewById(R.id.eval_button);
        recycler = (RecyclerView) findViewById(R.id.punto_fijo_recycler);

        setTitle("Punto Fijo");

        evalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unfocusViews();

                if (isValid()) {
                    String gx = gxEditText.getText().toString();
                    double x0 = Double.parseDouble(x0EditText.getText().toString());
                    double error = Double.parseDouble(errorEditText.getText().toString());
                    int iter = Integer.parseInt(iterEditText.getText().toString());

                    try {
                        Evaluator evaluator = new Evaluator(error, iter);
                        Stack<PuntoFijo> result = evaluator.evaluate(new PuntoFijo(gx, gx, x0, 1));

                        LinearLayoutManager layoutManager = new LinearLayoutManager(PuntoFijoActivity.this);
                        recycler.setAdapter(new PuntoFijoAdapter(result));
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
        gxEditText.clearFocus();
        x0EditText.clearFocus();
        errorEditText.clearFocus();
        iterEditText.clearFocus();
        recycler.requestFocus();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(gxEditText.getWindowToken(), 0);
    }

    private boolean isValid() {
        boolean valid = true;

        if (!isInteger(iterEditText)) valid = false;

        if (!isDouble(x0EditText)) valid = false;
        if (!isDouble(errorEditText)) valid = false;

        if (isVoid(gxEditText)) valid = false;
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
