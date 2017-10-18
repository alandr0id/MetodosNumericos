package com.yeik.app.metodosnumericos.falsaposicion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeik.app.metodosnumericos.R;
import com.yeik.app.metodosnumericos.metodos.FalsaPosicion;

import java.util.Stack;

/**
 * Created by Yeik on 9/27/2017.
 */

public class FalsaAdapter extends RecyclerView.Adapter<FalsaAdapter.FalsaHolder> {
    private Stack<FalsaPosicion> data;

    public FalsaAdapter(Stack<FalsaPosicion> data) {
        this.data = data;
    }

    @Override
    public FalsaAdapter.FalsaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.falsa_row
                , parent, false);
        return new FalsaAdapter.FalsaHolder(row);
    }

    @Override
    public void onBindViewHolder(FalsaAdapter.FalsaHolder holder, int position) {
        holder.getIterTextView().setText((position + 1) + ".\t");
        holder.getXrTextView().setText("xr: " + data.get(position).getXr());
        holder.getX0TextView().setText("x" + position + ": " + data.get(position).getXi());
        holder.getX1TextView().setText("x" + (position + 1) + ": " + data.get(position).getXu());
        holder.getErrorTextView().setText("Error: " + data.get(position).getError());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class FalsaHolder extends RecyclerView.ViewHolder {
        private TextView iterTextView;
        private TextView xrTextView;
        private TextView x1TextView;
        private TextView x0TextView;
        private TextView errorTextView;

        public FalsaHolder(View itemView) {
            super(itemView);

            iterTextView = itemView.findViewById(R.id.fp_iter_text);
            xrTextView = itemView.findViewById(R.id.fp_xr_text);
            x0TextView = itemView.findViewById(R.id.fp_x0_text);
            x1TextView = itemView.findViewById(R.id.fp_x1_text);
            errorTextView = itemView.findViewById(R.id.fp_error_text);
        }

        public TextView getIterTextView() {
            return iterTextView;
        }

        public TextView getXrTextView() {
            return xrTextView;
        }

        public TextView getErrorTextView() {
            return errorTextView;
        }

        public TextView getX1TextView() {
            return x1TextView;
        }

        public TextView getX0TextView() {
            return x0TextView;
        }
    }
}
