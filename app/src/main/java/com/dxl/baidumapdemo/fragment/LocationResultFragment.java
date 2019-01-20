package com.dxl.baidumapdemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.dxl.baidumapdemo.R;
import com.dxl.baidumapdemo.adapter.LocationRecyclerViewAdapter;

/**
 * @author dxl
 * @date 2019/1/19 16:33
 */
public class LocationResultFragment extends Fragment {

    RecyclerView mRecyclerView;
    LocationRecyclerViewAdapter mAdapter;

    public void setBDLocation(BDLocation bdLocation) {
        mBDLocation = bdLocation;
        mAdapter.setData(bdLocation);
    }

    BDLocation mBDLocation;

    public static LocationResultFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LocationResultFragment fragment = new LocationResultFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new LocationRecyclerViewAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result_location, container, false);
    }
}
