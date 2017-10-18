package com.yeik.app.metodosnumericos.puntofijo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeik.app.metodosnumericos.R;
import com.yeik.app.metodosnumericos.metodos.PuntoFijo;

import java.util.Stack;

/**
 * Created by Yeik on 9/24/2017.
 */

public class PuntoFijoAdapter extends RecyclerView.Adapter<PuntoFijoAdapter.PuntoFijoHolder> {
    private Stack<PuntoFijo> data;

    public PuntoFijoAdapter(Stack<PuntoFijo> data) {
        this.data = data;
    }

    @Override
    public PuntoFijoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.punto_fijo_row
                , parent, false);
        return new PuntoFijoHolder(row);
    }

    @Override
    public void onBindViewHolder(PuntoFijoHolder holder, int position) {
        holder.getIterTextView().setText((position + 1) + ".\t");
        holder.getxTextView().setText("x: " + data.get(position).getX0());
        holder.getErrorTextView().setText("Error: " + data.get(position).getError());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PuntoFijoHolder extends RecyclerView.ViewHolder {
        private TextView iterTextView;
        private TextView xTextView;
        private TextView errorTextView;

        public PuntoFijoHolder(View itemView) {
            super(itemView);

            iterTextView = itemView.findViewById(R.id.pf_iter_text);
            xTextView = itemView.findViewById(R.id.pf_x_text);
            errorTextView = itemView.findViewById(R.id.pf_error_text);
        }

        public TextView getIterTextView() {
            return iterTextView;
        }

        public TextView getxTextView() {
            return xTextView;
        }

        public TextView getErrorTextView() {
            return errorTextView;
        }
    }
}
