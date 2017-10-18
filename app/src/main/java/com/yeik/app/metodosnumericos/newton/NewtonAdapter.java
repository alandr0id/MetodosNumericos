package com.yeik.app.metodosnumericos.newton;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeik.app.metodosnumericos.R;
import com.yeik.app.metodosnumericos.metodos.Newton;

import java.util.Stack;

/**
 * Created by Yeik on 9/27/2017.
 */

public class NewtonAdapter extends RecyclerView.Adapter<NewtonAdapter.NewtonHolder> {
    private Stack<Newton> data;

    public NewtonAdapter(Stack<Newton> data) {
        this.data = data;
    }

    @Override
    public NewtonAdapter.NewtonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.newton_row
                , parent, false);
        return new NewtonAdapter.NewtonHolder(row);
    }

    @Override
    public void onBindViewHolder(NewtonAdapter.NewtonHolder holder, int position) {
        holder.getIterTextView().setText((position + 1) + ".\t");
        holder.getxTextView().setText("x: " + data.get(position).getX0());
        holder.getErrorTextView().setText("Error: " + data.get(position).getErr());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NewtonHolder extends RecyclerView.ViewHolder {
        private TextView iterTextView;
        private TextView xTextView;
        private TextView errorTextView;

        public NewtonHolder(View itemView) {
            super(itemView);

            iterTextView = itemView.findViewById(R.id.newton_iter_text);
            xTextView = itemView.findViewById(R.id.newton_x_text);
            errorTextView = itemView.findViewById(R.id.newton_error_text);
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
