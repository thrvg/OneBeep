package com.example.onebeep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mAlarmLabel;
        public ToggleButton mOnOffToggle;
        public ToggleButton mRepeatOnMon;
        public ToggleButton mRepeatOnTue;
        public ToggleButton mRepeatOnWed;
        public ToggleButton mRepeatOnThu;
        public ToggleButton mRepeatOnFri;
        public ToggleButton mRepeatOnSat;
        public ToggleButton mRepeatOnSun;
        public ToggleButton mBeepTwiceToggle;



        public ExampleViewHolder(View itemView) {
            super(itemView);
            mAlarmLabel = itemView.findViewById(R.id.alarmLabel);
            mOnOffToggle = itemView.findViewById(R.id.onOffToggle);
            mRepeatOnMon = itemView.findViewById(R.id.repeatOnMon);
            mRepeatOnTue = itemView.findViewById(R.id.repeatOnTue);
            mRepeatOnWed = itemView.findViewById(R.id.repeatOnWed);
            mRepeatOnThu = itemView.findViewById(R.id.repeatOnThu);
            mRepeatOnFri = itemView.findViewById(R.id.repeatOnFri);
            mRepeatOnSat = itemView.findViewById(R.id.repeatOnSat);
            mRepeatOnSun = itemView.findViewById(R.id.repeatOnSun);
            mBeepTwiceToggle = itemView.findViewById(R.id.beepTwiceToggle);
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        holder.mAlarmLabel.setText(currentItem.getAlarmTime());

        holder.mOnOffToggle.setChecked(currentItem.getOnOrOff()=="On");
        holder.mBeepTwiceToggle.setChecked(true);
        holder.mRepeatOnMon.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}