package com.dxl.baidumapdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dxl
 * @date 2019/1/19 11:22
 */
public class LocationRecyclerViewAdapter extends RecyclerView.Adapter<LocationRecyclerViewAdapter.LocationViewHolder> {

    private Context mContext;
    private List<LocationBean> mLocationBeans = new ArrayList<>();

    public LocationRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void setData(BDLocation bdLocation) {
        mLocationBeans = Util.getLocationBeans(bdLocation);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_location_view, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        LocationBean bean = mLocationBeans.get(position);
        holder.mMethod.setText(bean.method);
        holder.mResult.setText(bean.result);
        holder.mDesc.setText(bean.desc);
    }

    @Override
    public int getItemCount() {
        return mLocationBeans.size();
    }

    class LocationViewHolder extends RecyclerView.ViewHolder {

        TextView mDesc, mMethod, mResult;

        public LocationViewHolder(View itemView) {
            super(itemView);
            mDesc = itemView.findViewById(R.id.desc);
            mMethod = itemView.findViewById(R.id.method);
            mResult = itemView.findViewById(R.id.result);
        }
    }
}
