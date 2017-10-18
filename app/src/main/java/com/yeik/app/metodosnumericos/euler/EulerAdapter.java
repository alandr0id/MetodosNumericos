package com.yeik.app.metodosnumericos.euler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeik.app.metodosnumericos.R;
import com.yeik.app.metodosnumericos.metodos.Euler;

import java.util.Stack;

/**
 * Created by Yeik on 9/27/2017.
 */

public class EulerAdapter extends RecyclerView.Adapter<EulerAdapter.EulerHolder> {
    private Stack<Euler> data;

    public EulerAdapter(Stack<Euler> data) {
        this.data = data;
    }

    @Override
    public EulerAdapter.EulerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.euler_row
                , parent, false);
        return new EulerAdapter.EulerHolder(row);
    }

    @Override
    public void onBindViewHolder(EulerAdapter.EulerHolder holder, int position) {
        holder.getIterTextView().setText((position + 1) + ".\t");
        holder.getxTextView().setText("x: " + data.get(position).getX());
        holder.getErrorTextView().setText("Error: " + data.get(position).getError());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class EulerHolder extends RecyclerView.ViewHolder {
        private TextView iterTextView;
        private TextView xTextView;
        private TextView errorTextView;

        public EulerHolder(View itemView) {
            super(itemView);

            iterTextView = itemView.findViewById(R.id.euler_iter_text);
            xTextView = itemView.findViewById(R.id.euler_x_text);
            errorTextView = itemView.findViewById(R.id.euler_error_text);
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
