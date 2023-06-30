package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataRVAdapter extends RecyclerView.Adapter<DataRVAdapter.ViewHolder> {

    private ArrayList<DataModel> userModalArrayList;
    private Context context;

    public DataRVAdapter(ArrayList<DataModel> userModalArrayList, Context context) {
        this.userModalArrayList = userModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DataModel dataModel = userModalArrayList.get(position);
        holder.dataTV.setText(dataModel.getData());
        holder.dateTV.setText(dataModel.getDatetime());
    }

    @Override
    public int getItemCount() {
        return userModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dataTV, dateTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dataTV = itemView.findViewById(R.id.idData);
            dateTV = itemView.findViewById(R.id.idDatetime);
        }
    }
}