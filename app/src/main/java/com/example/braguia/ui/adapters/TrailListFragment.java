package com.example.braguia.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braguia.R;
import com.example.braguia.viewmodel.TrailViewModel;
import com.example.braguia.data.Trail;

import java.util.List;

public class TrailListFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;

    private TrailViewModel trailViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TrailListFragment() {
    }

    public static TrailListFragment newInstance(int columnCount) {
        TrailListFragment fragment = new TrailListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        trailViewModel = new ViewModelProvider(this).get(TrailViewModel.class);
        trailViewModel.getAllTrails().observe(getViewLifecycleOwner(), x -> {
            loadRecyclerView(view, x);
        });
        return view;
    }

    private void loadRecyclerView(View view, List<Trail> trails){
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new TrailRecyclerViewAdapter(trails));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
