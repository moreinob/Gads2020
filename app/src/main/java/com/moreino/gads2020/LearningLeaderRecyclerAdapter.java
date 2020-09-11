package com.moreino.gads2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class LearningLeaderRecyclerAdapter extends RecyclerView.Adapter<LearningLeaderRecyclerAdapter.ViewHolder> {

    private List<LearningLeader> mLearningLeaders;
    private final LayoutInflater mLayoutInflater;

    public LearningLeaderRecyclerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<LearningLeader> learningLeaders) {
        mLearningLeaders = learningLeaders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.learning_leader_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LearningLeader learningLeader = mLearningLeaders.get(position);
        holder.mLearningLeaderName.setText(learningLeader.getName());
        String learningHoursAndCountryText = learningLeader.getHours() + " Learning hours, " + learningLeader.getCountry();
        holder.mLearningLeaderHoursCountry.setText(learningHoursAndCountryText);
    }

    @Override
    public int getItemCount() {
        return mLearningLeaders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mLearningLeaderName;
        private final TextView mLearningLeaderHoursCountry;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLearningLeaderName = itemView.findViewById(R.id.learning_leader_name);
            mLearningLeaderHoursCountry = itemView.findViewById(R.id.learning_leader_hours_country);
        }
    }
}
