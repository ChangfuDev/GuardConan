package cn.hzmeurasia.guardconan.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.hzmeurasia.guardconan.MyApplication;
import cn.hzmeurasia.guardconan.R;
import cn.hzmeurasia.guardconan.activity.AdvancedToolsActivity;
import cn.hzmeurasia.guardconan.activity.AppManagerActivity;
import cn.hzmeurasia.guardconan.activity.BlackNumberActivity;
import cn.hzmeurasia.guardconan.activity.CleanCacheActivity;
import cn.hzmeurasia.guardconan.activity.OperatorSetActivity;
import cn.hzmeurasia.guardconan.activity.ProgressManagerActivity;
import cn.hzmeurasia.guardconan.activity.SettingsActivity;
import cn.hzmeurasia.guardconan.activity.TrafficMonitoringActivity;
import cn.hzmeurasia.guardconan.activity.VirusScanActivity;
import cn.hzmeurasia.guardconan.entity.Options;

/**
 * 类名: OptionsAdapter<br>
 * 功能:(主页选项适配类)<br>
 * 作者:黄振敏 <br>
 * 日期:2018/12/11 15:23
 */
public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.ViewHolder> {


    private List<Options> mOptions;

    private ItemClickListener itemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.options_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                switch(position) {
                    //手机防盗
                    case 0:
                        itemClickListener.onClick(position);
                        break;
                    //通讯卫士
                    case 1:
                        BlackNumberActivity.startAct(MyApplication.getContext());
                        break;
                    //软件管家
                    case 2:
                        AppManagerActivity.startAct(MyApplication.getContext());
                        break;
                    //手机杀毒
                    case 3:
                        VirusScanActivity.startAct(MyApplication.getContext());
                        break;
                    //缓存清理
                    case 4:
                        CleanCacheActivity.startAct(MyApplication.getContext());
                        break;
                    //进程管理
                    case 5:
                        ProgressManagerActivity.startAct(MyApplication.getContext());
                        break;
                    //流量统计
                    case 6:
                        SharedPreferences spf = MyApplication.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
                        if (spf.getBoolean("isset_operator", false)) {
                            TrafficMonitoringActivity.startAct(MyApplication.getContext());
                        } else {
                            OperatorSetActivity.startAct(MyApplication.getContext());
                        }
                        break;
                    //高级工具
                    case 7:
                        AdvancedToolsActivity.startAct(MyApplication.getContext());
                        break;
                    //设置中心
                    case 8:
                        SettingsActivity.startAct(MyApplication.getContext());
                        break;
                    default:
                        break;
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Options options = mOptions.get(position);
        holder.textView.setText(options.getContent());
        Glide.with(MyApplication.getContext()).load(options.getImageId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mOptions.size();
    }

    public OptionsAdapter(List<Options> optionsList) {
        mOptions = optionsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        @BindView(R.id.iv_card)
        ImageView imageView;
        @BindView(R.id.tv_card)
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            ButterKnife.bind(this, itemView);
        }
    }

}
