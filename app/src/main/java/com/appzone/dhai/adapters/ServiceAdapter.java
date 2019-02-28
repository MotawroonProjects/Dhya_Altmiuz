package com.appzone.dhai.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appzone.dhai.R;
import com.appzone.dhai.models.ServiceDataModel;

import java.util.List;
import java.util.Locale;

public class ServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ITEM_DATA = 1;
    private final int ITEM_LOAD= 2;

    private List<ServiceDataModel.ServiceModel> serviceModelList;
    private Context context;
    private Fragment fragment;

    public ServiceAdapter(List<ServiceDataModel.ServiceModel> serviceModelList, Context context,Fragment fragment) {
        this.serviceModelList = serviceModelList;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == ITEM_DATA)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.service_row,parent,false);
            return new MyHolder(view);
        }else
            {
                View view = LayoutInflater.from(context).inflate(R.layout.load_more_row,parent,false);
                return new ProgressHolder(view);
            }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyHolder)
        {
            ServiceDataModel.ServiceModel serviceModel = serviceModelList.get(holder.getAdapterPosition());
            MyHolder myHolder = (MyHolder) holder;
            myHolder.BindData(serviceModel);
        }else
            {
                ProgressHolder progressHolder = (ProgressHolder) holder;
                progressHolder.progBar.setIndeterminate(true);
            }
    }

    @Override
    public int getItemCount() {
        return serviceModelList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;

        public MyHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);

        }

        public void BindData(ServiceDataModel.ServiceModel serviceModel)
        {
            if (Locale.getDefault().getLanguage().equals("ar"))
            {
                tv_name.setText(serviceModel.getTitle_ar());
            }else
                {
                    tv_name.setText(serviceModel.getTitle_en());

                }

        }
    }

    public class ProgressHolder extends RecyclerView.ViewHolder {
        private ProgressBar progBar;
        public ProgressHolder(View itemView) {
            super(itemView);
            progBar = itemView.findViewById(R.id.progBar);

        }
    }

    @Override
    public int getItemViewType(int position) {
        ServiceDataModel.ServiceModel serviceModel = serviceModelList.get(position);

        if (serviceModel !=null)
        {
            return ITEM_DATA;
        }else
            {
                return ITEM_LOAD;
            }
    }
}