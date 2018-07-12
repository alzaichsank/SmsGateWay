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
import android.widget.Toast;

import com.example.alzaichsank.smsgateway.R;
import com.example.alzaichsank.smsgateway.model.contact_list.ContactListData;
import com.example.alzaichsank.smsgateway.view.ContactActivity;
import com.example.alzaichsank.smsgateway.view.ContactListActivity;
import com.example.alzaichsank.smsgateway.view.FromActivity;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder>
{
    private List<ContactListData> contactListData;
    private Context context;


    public ContactListAdapter(Context context, List<ContactListData> contactListData)
    {
        this.contactListData = contactListData;
        this.context = context;
    }

    @Override
    public ContactListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_list_contacts,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactListAdapter.ViewHolder holder, final int position)
    {
        holder.textNohp.setText(contactListData.get(position).getContact());
        holder.textNameUser.setText(contactListData.get(position).getName());

    }

    @Override
    public int getItemCount()
    {
        return contactListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textNohp, textNameUser;
        private RelativeLayout main_layout;

        public ViewHolder(View itemView)
        {
            super(itemView);

            context = itemView.getContext();
            textNohp = itemView.findViewById(R.id.textNohp);
            textNameUser = itemView.findViewById(R.id.textNameUser);
            main_layout = itemView.findViewById(R.id.listContacts);
            main_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), FromActivity.class);
                    Bundle bundle=new Bundle();

                    bundle.putString("mode", "edit");
                    bundle.putString("extaraid", contactListData.get(getAdapterPosition()).getId());
                    bundle.putString("extaraname", contactListData.get(getAdapterPosition()).getName());
                    bundle.putString("extaratelepone", contactListData.get(getAdapterPosition()).getContact());
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);

                    ((ContactListActivity)context).finish();

                }
            });
        }

    }

}
