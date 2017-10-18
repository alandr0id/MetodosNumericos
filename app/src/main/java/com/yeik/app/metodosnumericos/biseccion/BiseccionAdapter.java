package com.yeik.app.metodosnumericos.biseccion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeik.app.metodosnumericos.R;
import com.yeik.app.metodosnumericos.metodos.Biseccion;

import java.util.Stack;

/**
 * Created by Yeik on 9/26/2017.
 */

public class BiseccionAdapter extends RecyclerView.Adapter<BiseccionAdapter.BiseccionHolder> {
    private Stack<Biseccion> data;

    public BiseccionAdapter(Stack<Biseccion> data) {
        this.data = data;
    }

    @Override
    public BiseccionAdapter.BiseccionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.biseccion_row
                , parent, false);
        return new BiseccionAdapter.BiseccionHolder(row);
    }

    @Override
    public void onBindViewHolder(BiseccionAdapter.BiseccionHolder holder, int position) {
        holder.getIterTextView().setText("Iteracion " + (position + 1));
        holder.getXmTextView().setText("xm: " + data.get(position).getXm());
        holder.getX0TextView().setText("x" + position + ": " + data.get(position).getX0());
        holder.getX1TextView().setText("x" + (position + 1) + ": " + data.get(position).getX1());
        holder.getFx1TextView().setText("fx" + (position) + ": " + data.get(position).getFx0());
        holder.getFxmTextView().setText("fxm" + ": " + data.get(position).getFxm());
        holder.getErrorTextView().setText("Error: " + data.get(position).getError());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BiseccionHolder extends RecyclerView.ViewHolder {
        private TextView iterTextView;
        private TextView xmTextView;
        private TextView x1TextView;
        private TextView x0TextView;
        private TextView fx1TextView;
        private TextView fxmTextView;
        private TextView errorTextView;

        public BiseccionHolder(View itemView) {
            super(itemView);

            iterTextView = itemView.findViewById(R.id.bi_iter_text);
            xmTextView = itemView.findViewById(R.id.bi_xm_text);
            x0TextView = itemView.findViewById(R.id.bi_x0_text);
            fx1TextView = itemView.findViewById(R.id.bi_fx1_text);
            fxmTextView = itemView.findViewById(R.id.bi_fxm_text);
            x1TextView = itemView.findViewById(R.id.bi_x1_text);
            errorTextView = itemView.findViewById(R.id.bi_error_text);
        }

        public TextView getIterTextView() {
            return iterTextView;
        }

        public TextView getXmTextView() {
            return xmTextView;
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

        public TextView getFx1TextView() {
            return fx1TextView;
        }

        public TextView getFxmTextView() {
            return fxmTextView;
        }
    }
}
