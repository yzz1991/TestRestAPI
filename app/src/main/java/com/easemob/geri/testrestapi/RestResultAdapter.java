package com.easemob.geri.testrestapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easemob.geri.testrestapi.bean.RestResultBean;

import java.util.List;

/**
 * Created by Geri on 2017/1/16.
 */

public class RestResultAdapter extends RecyclerView.Adapter<RestResultAdapter.RestResultViewHolder>{

    private Context mContext;
    private List<RestResultBean> mList;
    private LayoutInflater mInflater;
    //自定义item点击接口
    private OnItemClickListener mOnItemClickListener;

    public RestResultAdapter(Context context, List<RestResultBean> mList) {
        this.mContext = context;
        this.mList = mList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RestResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RestResultViewHolder(mInflater.inflate(R.layout.item_rest_result,null));
    }

    @Override
    public void onBindViewHolder(final RestResultViewHolder holder, int position) {
        holder.tvName.setText(mList.get(position).getName());
        holder.tvCode.setText(mList.get(position).getCode()+"");
        //item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(holder.itemView, pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 自定义聊天ViewHolder 用来展示聊天数据
     */
    public static class RestResultViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvCode;
        /**
         * 构造方法
         *
         * @param itemView 显示聊天数据的 ItemView
         */
        public RestResultViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code);
        }
    }

    /**
     * Created by Geri on 2016/12/2.
     * recycleView的点击回调接口
     */

    public interface OnItemClickListener {

        //点击
        void onItemClick(View view, int position);
    }

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
}
