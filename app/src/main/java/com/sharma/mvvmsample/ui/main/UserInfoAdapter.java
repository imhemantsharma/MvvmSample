package com.sharma.mvvmsample.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sharma.mvvmsample.R;
import com.sharma.mvvmsample.data.model.api.UserInfo;
import com.sharma.mvvmsample.ui.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Hemant Sharma on 01-01-19.
 */
public class UserInfoAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<UserInfo> userInfoList;

    UserInfoAdapter(List<UserInfo> infoList) {
        userInfoList = infoList;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_info, parent, false));
    }

    @Override
    public long getItemId(int position) {
        return userInfoList.get(position).getId();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (userInfoList != null && userInfoList.size() > 0) {
            return userInfoList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends BaseViewHolder {

        ImageView ivThumbnail;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvWeb;

        ViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvWeb = itemView.findViewById(R.id.tvWeb);
        }

        public void onBind(int position) {
            final UserInfo userInfo = userInfoList.get(position);

            /// URL pointing Random placeholder image
            Glide.with(itemView.getContext())
                    .load("https://i.pravatar.cc?img=".concat(String.valueOf(position)))
                    .into(ivThumbnail);

            tvTitle.setText(userInfo.getName());
            tvDescription.setText(userInfo.getEmail());
            tvWeb.setText(userInfo.getWebsite());
        }
    }
}
