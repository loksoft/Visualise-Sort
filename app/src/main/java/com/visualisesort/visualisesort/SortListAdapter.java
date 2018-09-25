package com.visualisesort.visualisesort;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SortListAdapter extends RecyclerView.Adapter<SortListAdapter.SortViewHolder> {
    private Context context;
    private List<Integer> list;

    public SortListAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SortListAdapter.SortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_view_items,parent,false);
        return new SortViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SortListAdapter.SortViewHolder holder, int position) {
        Integer dataElement = list.get(position);
        holder.listItem.setText(dataElement.toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SortViewHolder extends RecyclerView.ViewHolder {
        TextView listItem;
        public SortViewHolder(View itemView) {
            super(itemView);
            listItem = itemView.findViewById(R.id.cus_list_item);
        }
    }
}
