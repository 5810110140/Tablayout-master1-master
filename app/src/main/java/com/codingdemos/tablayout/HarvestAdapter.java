package com.codingdemos.tablayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HarvestAdapter extends RecyclerView.Adapter <HarvestAdapter.HarvestViewHolder>{

    Context mCtx;
    List<harvast_value> HarvestList;

    public  static  final String TAG = "HarvestAdapter";

    public HarvestAdapter(Context mCtx,List<harvast_value> productList){
        this.mCtx=mCtx;
        this.HarvestList=productList;
    }

    @Override
    public HarvestViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        View view = LayoutInflater.from(mCtx).inflate(R.layout.harvest_adapter,parent,false);
        HarvestViewHolder productViewHolder = new HarvestViewHolder(view);


        return  productViewHolder;
    }

    @Override
    public void onBindViewHolder(HarvestViewHolder holder,int position) {
        harvast_value dataflow = HarvestList.get(position);

        holder.textdate.setText(dataflow.getDate());
        holder.textvalue.setText(dataflow.getValue());
        holder.texttype.setText(dataflow.getType());
        holder.weight.setText(dataflow.getWeight());

    }

    @Override
    public int getItemCount() {
        return HarvestList.size();
    }

    public class HarvestViewHolder extends RecyclerView.ViewHolder{
        public TextView texttype,textvalue;
        public TextView textdate;
        public TextView weight;

        public  HarvestViewHolder(View itemView){
            super(itemView);

            texttype=itemView.findViewById(R.id.T_harvest_type);
            textvalue=itemView.findViewById(R.id.T_harvest_value);
            textdate=itemView.findViewById(R.id.T_harvest_date);
            weight=itemView.findViewById(R.id.T_harvest_weight);
        }
    }
}
