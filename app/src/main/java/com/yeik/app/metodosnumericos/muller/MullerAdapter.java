package com.yeik.app.metodosnumericos.muller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeik.app.metodosnumericos.R;
import com.yeik.app.metodosnumericos.metodos.Muller;

import java.util.Stack;

/**
 * Created by Yeik on 9/27/2017.
 */

public class MullerAdapter extends RecyclerView.Adapter<MullerAdapter.MullerHolder> {
    private Stack<Muller> data;

    public MullerAdapter(Stack<Muller> data) {
        this.data = data;
    }

    @Override
    public MullerAdapter.MullerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.muller_row
                , parent, false);
        return new MullerAdapter.MullerHolder(row);
    }

    @Override
    public void onBindViewHolder(MullerAdapter.MullerHolder holder, int position) {
        holder.getIterTextView().setText((position + 1) + ".\t");
        holder.getX0TextView().setText("x" + position + ": " + data.get(position).getX0());
        holder.getX1TextView().setText("x" + (position + 1) + ": " + data.get(position).getX1());
        holder.getX2TextView().setText("x" + (position + 2) + ": " + data.get(position).getX2());
        holder.getErrorTextView().setText("Error: " + data.get(position).getError());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MullerHolder extends RecyclerView.ViewHolder {
        private TextView iterTextView;
        private TextView x2TextView;
        private TextView x1TextView;
        private TextView x0TextView;
        private TextView errorTextView;

        public MullerHolder(View itemView) {
            super(itemView);

            iterTextView = itemView.findViewById(R.id.muller_iter_text);
            x0TextView = itemView.findViewById(R.id.muller_x0_text);
            x1TextView = itemView.findViewById(R.id.muller_x1_text);
            x2TextView = itemView.findViewById(R.id.muller_x2_text);
            errorTextView = itemView.findViewById(R.id.muller_error_text);
        }

        public TextView getIterTextView() {
            return iterTextView;
        }

        public TextView getErrorTextView() {
            return errorTextView;
        }

        public TextView getX2TextView() {
            return x2TextView;
        }

        public TextView getX1TextView() {
            return x1TextView;
        }

        public TextView getX0TextView() {
            return x0TextView;
        }
    }
}
