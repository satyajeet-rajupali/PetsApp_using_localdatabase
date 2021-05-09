package com.satyajeet.pets.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.satyajeet.pets.R;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetHolder> {

    private ArrayList<PetData> data;
    private Context mContext;

    public PetAdapter(Context context, ArrayList<PetData> data) {
        this.data = data;
        mContext = context;
    }

    @NonNull
    @Override
    public PetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_row,parent,false);
        return new PetHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PetHolder holder, int position) {

        holder.petName.setText(data.get(position).getPetName());
        holder.petBreed.setText(data.get(position).getPetBreed());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PetHolder extends RecyclerView.ViewHolder{

        private TextView petName;
        private TextView petBreed;
        public PetHolder(@NonNull View itemView) {
            super(itemView);

            petName = (TextView)itemView.findViewById(R.id.petName);
            petBreed = (TextView)itemView.findViewById(R.id.petBreed);

        }
    }
}
