package com.pbaileyapps.android.collegesearch.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pbaileyapps.android.collegesearch.R;
import com.pbaileyapps.android.collegesearch.RetrofitComponents.WindowItem;
import com.pbaileyapps.android.collegesearch.RoomComponents.CollegeViewModel;

import java.util.List;

public class WindowRecyclerAdapter extends RecyclerView.Adapter<WindowRecyclerAdapter.WindowViewHolder>  {
    private Context mContext;
    private List<WindowItem> windowItemList;
    protected CollegeViewModel model;
    public interface likeInterface{
        public void likeCollege(String collegeName,String countryName);
    }
    public likeInterface anInterface;
    public WindowRecyclerAdapter(Context context){
        mContext = context;
        this.model = model;
    }

    public void setWindowItemList(List<WindowItem> windowItemList) {
        this.windowItemList = windowItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WindowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        return new WindowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WindowViewHolder holder, int position) {
        if(windowItemList != null) {
            WindowItem item = windowItemList.get(position);
            if(item.getCountry() == null) {
                holder.textView.setText(item.getName());
                holder.textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
            }
            else {
                String data = item.getName();
                String countryText = item.getCountry();
                holder.textView.setText(data.toUpperCase());
                holder.countryView.setText(countryText.toUpperCase());

                holder.likeView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            anInterface.likeCollege(data,countryText);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });
            }
        }
        else{
            holder.textView.setText("No data yet");
        }
    }

    @Override
    public int getItemCount() {
        if(windowItemList == null){
            return 0;
        }
        else {
            return windowItemList.size();
        }
    }
    class WindowViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private TextView countryView;
        private ImageView likeView;
        likeInterface likeInstance;
        public WindowViewHolder(View v){
            super(v);
            countryView = v.findViewById(R.id.country);
            textView = v.findViewById(R.id.list_item_text);
            likeView = v.findViewById(R.id.thumbsup);

        }
    }

}
