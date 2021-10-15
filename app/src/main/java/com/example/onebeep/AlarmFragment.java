package com.example.onebeep;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class AlarmFragment extends Fragment {
//    TextView alarmLabel;
//    List<String> groupList;
//    List<String> childList;
//    Map<String, List<String>> alarmList;
//    ExpandableListView expandableListView;
//    ExpandableListAdapter expandableListAdapter;
//    HashMap<String, List<List<String>>> listOfAlarms;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem("0800", "On", "012", "Off"));
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //createGroupList();
//        createCollection();
//        expandableListView = view.findViewById(R.id.alarmList);
//        expandableListAdapter = new MyExpandableListAdapter(getContext(), groupList, listOfAlarms);
//        expandableListView.setAdapter(expandableListAdapter);
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            int lastExpandedPosition = -1;
//            @Override
//            public void onGroupExpand(int i) {
//                if(lastExpandedPosition != -1 && i != lastExpandedPosition){
//                    expandableListView.collapseGroup(lastExpandedPosition);
//                }
//                lastExpandedPosition = i;
//            }
//        });
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
//                return false;
//            }
//        });
//
//


       return view;


    }
//    private void createCollection() {
//        ArrayList<String> tempList0 = new ArrayList<>();
//        tempList0.add("08:00");
//        ArrayList<String> tempList1 = new ArrayList<>();
//        tempList1.add("On");
//        ArrayList<String> tempList2 = new ArrayList<>();
//        tempList1.add("0");
//        tempList2.add("1");
//        tempList2.add("2");
//        ArrayList<String> tempList3 = new ArrayList<>();
//        tempList3.add("On");
//        ArrayList<List<String>> tempListMain = new ArrayList<>();
//        tempListMain.add(tempList0);
//        tempListMain.add(tempList1);
//        tempListMain.add(tempList2);
//        tempListMain.add(tempList3);
//        listOfAlarms = new HashMap<>();
//        listOfAlarms.put("0", tempListMain);
//        groupList = new ArrayList<>();
//        groupList.add("08:00");
//
//    }
//
//      private void loadChild(String[] mobileModels) {
//        childList = new ArrayList<>();
//        for(String model : mobileModels){
//            childList.add(model);
//        }
//    }
//
//    private void createGroupList() {
//        groupList = new ArrayList<>();
//        groupList.add("08:00");
//        groupList.add("");
//        groupList.add("10:00");
//    }
}