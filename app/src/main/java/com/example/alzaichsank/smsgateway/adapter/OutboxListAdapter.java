package com.example.alzaichsank.smsgateway.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alzaichsank.smsgateway.R;
import com.example.alzaichsank.smsgateway.model.contact_list.ContactListData;
import com.example.alzaichsank.smsgateway.model.outbox.Outboxdata;
import com.example.alzaichsank.smsgateway.view.ContactListActivity;
import com.example.alzaichsank.smsgateway.view.DetailOutboxActivity;
import com.example.alzaichsank.smsgateway.view.FromActivity;
import com.example.alzaichsank.smsgateway.view.OutboxActivity;

import java.util.List;

public class OutboxListAdapter extends RecyclerView.Adapter<OutboxListAdapter.ViewHolder>
{
    private List<Outboxdata> outboxdata;
    private Context context;


    public OutboxListAdapter(Context context, List<Outboxdata> outboxdata)
    {
        this.outboxdata = outboxdata;
        this.context = context;
    }

    @Override
    public OutboxListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_list_outbox,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OutboxListAdapter.ViewHolder holder, final int position)
    {
        holder.textNohp.setText(outboxdata.get(position).getMessage());
        holder.textNameUser.setText(outboxdata.get(position).getNo_tujuan());
        holder.textDateUser.setText(outboxdata.get(position).getDate());

    }

    @Override
    public int getItemCount()
    {
        return outboxdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textNohp, textNameUser,textDateUser;
        private RelativeLayout main_layout;

        public ViewHolder(View itemView)
        {
            super(itemView);

            context = itemView.getContext();
            textNohp = itemView.findViewById(R.id.textNohp);
            textNameUser = itemView.findViewById(R.id.textNameUser);
            textDateUser = itemView.findViewById(R.id.textDate);
            main_layout = itemView.findViewById(R.id.listContacts);


            main_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailOutboxActivity.class);
                    Bundle bundle=new Bundle();

                    bundle.putString("mode", "edit");
                    bundle.putString("extaraid", outboxdata.get(getAdapterPosition()).getId());
                    bundle.putString("extaraname", outboxdata.get(getAdapterPosition()).getNo_tujuan());
                    bundle.putString("extaratelepone", outboxdata.get(getAdapterPosition()).getMessage());
                    bundle.putString("extaradate", outboxdata.get(getAdapterPosition()).getDate());
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);

                    ((OutboxActivity)context).finish();

                }
            });
        }

    }

}
