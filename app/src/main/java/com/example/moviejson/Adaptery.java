package com.example.moviejson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> implements Filterable {

    private Context mContext;
    private List<MovieModelClass> mData;
    private List<MovieModelClass> mDataFull;
    private OnNoteListener mOnNoteListener;

    public Adaptery(Context mContext, List<MovieModelClass> mData, OnNoteListener onNoteListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mOnNoteListener=onNoteListener;
        mDataFull = new ArrayList<>(mData);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.movie_item,parent,false);

        return new MyViewHolder(v, mOnNoteListener);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText("Dátum premiéry: "+mData.get(position).getPremiere());
        holder.name.setText(mData.get(position).getName());
        //Using glide libabry pre obrazok
        Glide.with(mContext)
                .load(mData.get(position).getImg()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return FilterMovie;
    }
    private Filter FilterMovie = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String searchText = constraint.toString().toLowerCase();
            List<MovieModelClass> tempList=new ArrayList<>();
            if(searchText.length()==0 || searchText.isEmpty())
            {
                tempList.addAll(mDataFull);
            }
            else
                {
                    for(MovieModelClass item:mDataFull){
                        if(item.getName().toLowerCase().contains(searchText)){
                            tempList.add(item);
                        }
                    }

            }
            FilterResults filterResults = new FilterResults();
            filterResults.values=tempList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData.clear();
            mData.addAll((Collection<? extends MovieModelClass>) results.values);
            notifyDataSetChanged();

        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id;
        TextView name;
        ImageView img;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            id = itemView.findViewById(R.id.id_txt);
            name = itemView.findViewById(R.id.name_txt);
            img = itemView.findViewById(R.id.imageView);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());

        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}
