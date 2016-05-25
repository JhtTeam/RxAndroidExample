package com.rxandroidsample.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rxandroidsample.R;
import com.rxandroidsample.data.model.Profile;
import com.rxandroidsample.data.model.Ribot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anhndt on 5/25/16.
 */
public class RibotAdapter extends RecyclerView.Adapter<RibotAdapter.RibotAdapterHolder> {
    private List<Ribot> mItems = new ArrayList<>();

    public void add(Ribot profile) {
        mItems.add(profile);
        notifyItemInserted(mItems.size() - 1);
    }
    public void remove(int position) {
        if (position >= 0 && position < mItems.size()) {
            mItems.remove(position);
            notifyItemRemoved(position);
        }
    }
    public void add(List<Ribot> items) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public RibotAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ribot_item, parent, false);
        return new RibotAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RibotAdapterHolder holder, int position) {
        Ribot profile = mItems.get(position);
        holder.bindTo(profile);
    }

    @Override
    public int getItemCount() {
        return (mItems != null) ? mItems.size() : 0;
    }

    public static class RibotAdapterHolder extends RecyclerView.ViewHolder {
        private final ImageView imgAvatar;
        private final TextView txtName;
        private final TextView txtEmail;
        public RibotAdapterHolder(View itemView) {
            super(itemView);
            imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
        }

        public void bindTo(Ribot profile) {
            txtName.setText(profile.profile.getNameText());
            txtEmail.setText(profile.profile.getEmail());

        }
    }
}
