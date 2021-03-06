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
import com.pbaileyapps.android.collegesearch.RoomComponents.College;
import com.pbaileyapps.android.collegesearch.RoomComponents.CollegeViewModel;

import java.util.List;

public class CollegeRecycler extends RecyclerView.Adapter<CollegeRecycler.CollegeViewHolder> {
    private List<College> colleges;
    private Context context;
    private CollegeViewModel model;
    public deleteInterface deleteInterfaceInstance;
    public interface deleteInterface{
        void deleteCollege(College college, int position);
    }
    public CollegeRecycler(Context context, CollegeViewModel model) {
        super();

        this.context = context;
        this.model = model;
    }

    public void setColleges(List<College> colleges) {
        this.colleges = colleges;
        notifyDataSetChanged();
    }

    static class CollegeViewHolder extends RecyclerView.ViewHolder{
        TextView countryView;
        TextView collegeView;
        ImageView collegeImage,deleteImage;
        public CollegeViewHolder(@NonNull View itemView) {
            super(itemView);
            collegeView = itemView.findViewById(R.id.list_item_text);
            countryView = itemView.findViewById(R.id.country);
            collegeImage = itemView.findViewById(R.id.thumbsup);
            deleteImage = itemView.findViewById(R.id.trash);
        }
    }
    @NonNull
    @Override
    public CollegeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CollegeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CollegeViewHolder holder, int position) {

        College college = colleges.get(position);
        holder.collegeView.setText(college.getName().toUpperCase());
        holder.countryView.setText(college.getCountry().toUpperCase());
        holder.collegeImage.setVisibility(View.INVISIBLE);
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    deleteInterfaceInstance.deleteCollege(college,position);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (colleges == null) {
            return 0;
        } else {
            return colleges.size();
        }
    }
}
