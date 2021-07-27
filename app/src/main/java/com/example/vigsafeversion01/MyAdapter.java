package com.example.vigsafeversion01;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<FoodList> dataholder;

    public MyAdapter(ArrayList<FoodList> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.imageID.setImageBitmap(dataholder.get(position).getImageId());
        holder.productType.setText(dataholder.get(position).getProductType());
        holder.productDescription.setText(dataholder.get(position).getProductDescription());
        //dataholder.get(position).getTemperature();

        holder.temperature.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataholder.get(position).temperature = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder

    {
        de.hdodenhof.circleimageview.CircleImageView imageID;
        TextView productType, productDescription;
        EditText temperature;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageID = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.displayimageId);
            productType = (TextView) itemView.findViewById(R.id.displayproductType);
            productDescription = (TextView) itemView.findViewById(R.id.displayproductDescription);
            temperature = (EditText) itemView.findViewById(R.id.displayEditText);
        }
    }

}
