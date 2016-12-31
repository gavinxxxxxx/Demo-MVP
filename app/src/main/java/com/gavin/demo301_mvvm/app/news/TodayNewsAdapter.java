package com.gavin.demo301_mvvm.app.news;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.gavin.demo301_mvvm.R;
import com.gavin.demo301_mvvm.base.RecyclerAdapter;
import com.gavin.demo301_mvvm.base.RecyclerHolder;
import com.gavin.demo301_mvvm.databinding.ItemTestBinding;
import com.gavin.demo301_mvvm.model.TodayNews;

import java.util.List;

/**
 * 这里是萌萌哒注释君
 *
 * @author gavin.xiong 2016/12/9  2016/12/9
 */
public class TodayNewsAdapter extends RecyclerAdapter<TodayNews.Story, ItemTestBinding> {

    public TodayNewsAdapter(Context context, List<TodayNews.Story> mData) {
        super(context, mData, R.layout.item_test);
    }

    @Override
    public void onBind(RecyclerHolder<ItemTestBinding> holder, TodayNews.Story t, int position) {
        holder.binding.textView.setText(t.getTitle());
        if (t.getImageUrls() != null && !t.getImageUrls().isEmpty()) {
            Glide.with(mContext)
                    .load(t.getImageUrls().get(0))
                    .into(holder.binding.imageView);
        }
    }

}
