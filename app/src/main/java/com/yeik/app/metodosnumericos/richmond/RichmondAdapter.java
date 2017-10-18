package com.yeik.app.metodosnumericos.richmond;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeik.app.metodosnumericos.R;
import com.yeik.app.metodosnumericos.metodos.Richmond;

import java.util.Stack;

/**
 * Created by Yeik on 9/27/2017.
 */

public class RichmondAdapter extends RecyclerView.Adapter<RichmondAdapter.RichmondHolder>{
    private Stack<Richmond> data;

    public RichmondAdapter(Stack<Richmond> data) {
        this.data=data;
    }

    @Override
    public RichmondAdapter.RichmondHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.richmond_row
        ,parent,false);
        return new RichmondAdapter.RichmondHolder(row);
    }

    @Override
    public void onBindViewHolder(RichmondAdapter.RichmondHolder holder,int position){
        holder.getIterTextView().setText((position+1)+".\t");
        holder.getxTextView().setText("x: "+data.get(position).getX());
        holder.getErrorTextView().setText("Error: "+data.get(position).getError());
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

    class RichmondHolder extends RecyclerView.ViewHolder {
        private TextView iterTextView;
        private TextView xTextView;
        private TextView errorTextView;

        public RichmondHolder(View itemView) {
            super(itemView);

            iterTextView = itemView.findViewById(R.id.richmond_iter_text);
            xTextView = itemView.findViewById(R.id.richmond_x_text);
            errorTextView = itemView.findViewById(R.id.richmond_error_text);
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
