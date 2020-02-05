package com.codingdemos.tablayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class InsertAdapter extends RecyclerView.Adapter<InsertAdapter.InsertViewHolder> {

    Context mCtx;
    List<InsertV2> InsertList;

    public static final String TAG = "InsertAdapter";

    public InsertAdapter(Context mCtx,List <InsertV2> productList){
        this.mCtx =mCtx;
        this.InsertList = productList;
    }

    @Override
    public InsertViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.insert_adapter,parent, false);
        InsertViewHolder produrctViewHolder = new InsertViewHolder(view);
        return produrctViewHolder;
    }

    @Override
    public void onBindViewHolder(InsertViewHolder holder, int position) {
        InsertV2 dataflow = InsertList.get(position);

        holder.textdate.setText(dataflow.getDate());
        holder.texttime.setText(dataflow.getTime());
        holder.textvalue1.setText(dataflow.getIvm1());
        holder.texttype1.setText(dataflow.getType1());




    }

    @Override
    public int getItemCount() {
        return InsertList.size();
    }

    public  class  InsertViewHolder extends RecyclerView.ViewHolder{

        public TextView texttype1, texttype2, texttype3, textvalue1, textvalue2,textvalue3;
        public TextView textdate, texttime;

        public InsertViewHolder(View itemView){
            super(itemView);

            textdate =itemView.findViewById(R.id.Tdate);
            texttime =itemView.findViewById(R.id.Ttime);

            texttype1=itemView.findViewById(R.id.type1);
            texttype2=itemView.findViewById(R.id.type2);
            texttype3=itemView.findViewById(R.id.type3);

            textvalue1=itemView.findViewById(R.id.Va1);
            textvalue2=itemView.findViewById(R.id.Va2);
            textvalue3=itemView.findViewById(R.id.Va3);

        }

    }
}
