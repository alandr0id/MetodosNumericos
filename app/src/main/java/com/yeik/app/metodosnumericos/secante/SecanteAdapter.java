package com.yeik.app.metodosnumericos.secante;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeik.app.metodosnumericos.R;
import com.yeik.app.metodosnumericos.metodos.Secante;

import java.util.Stack;

/**
 * Created by Yeik on 9/27/2017.
 */

public class SecanteAdapter extends RecyclerView.Adapter<SecanteAdapter.SecanteHolder> {
    private Stack<Secante> data;

    public SecanteAdapter(Stack<Secante> data) {
        this.data = data;
    }

    @Override
    public SecanteAdapter.SecanteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.secante_row
                , parent, false);
        return new SecanteAdapter.SecanteHolder(row);
    }

    @Override
    public void onBindViewHolder(SecanteAdapter.SecanteHolder holder, int position) {
        holder.getIterTextView().setText((position + 1) + ".\t");
        holder.getX0TextView().setText("x" + position + ": " + data.get(position).getX0());
        holder.getX1TextView().setText("x" + (position + 1) + ": " + data.get(position).getX1());
        holder.getErrorTextView().setText("Error: " + data.get(position).getError());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SecanteHolder extends RecyclerView.ViewHolder {
        private TextView iterTextView;
        private TextView x1TextView;
        private TextView x0TextView;
        private TextView errorTextView;

        public SecanteHolder(View itemView) {
            super(itemView);

            iterTextView = itemView.findViewById(R.id.secante_iter_text);
            x0TextView = itemView.findViewById(R.id.secante_x0_text);
            x1TextView = itemView.findViewById(R.id.secante_x1_text);
            errorTextView = itemView.findViewById(R.id.secante_error_text);
        }

        public TextView getIterTextView() {
            return iterTextView;
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
