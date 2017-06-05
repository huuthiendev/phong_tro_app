package com.project.huuthiendev.demophongtro.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.huuthiendev.demophongtro.Adapter.PhongTroAdapter;
import com.project.huuthiendev.demophongtro.Model.PhongTro;
import com.project.huuthiendev.demophongtro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUUTHIENDEV on 11/2/2016.
 */

public class TimelineFragment extends Fragment {

    private RecyclerView recyclerView;
    private PhongTroAdapter adapter;
    private DatabaseReference mDatabase;
    private List<PhongTro> listPhongTro;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rvList);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listPhongTro = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new PhongTroAdapter(getActivity(), listPhongTro);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mDatabase.child("PhongTro").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PhongTro data = dataSnapshot.getValue(PhongTro.class);
                listPhongTro.add(data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;
    }
}
