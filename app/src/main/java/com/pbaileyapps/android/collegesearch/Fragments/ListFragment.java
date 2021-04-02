package com.pbaileyapps.android.collegesearch.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pbaileyapps.android.collegesearch.Adapters.CollegeRecycler;
import com.pbaileyapps.android.collegesearch.R;
import com.pbaileyapps.android.collegesearch.RetrofitComponents.WindowItem;
import com.pbaileyapps.android.collegesearch.RoomComponents.College;
import com.pbaileyapps.android.collegesearch.RoomComponents.CollegeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CollegeViewModel listViewModel;
    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ArrayList<WindowItem> windowItems = new ArrayList<WindowItem>();
        windowItems.add(new WindowItem("Cool","Cool"));
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        v.findViewById(R.id.search_bar).setVisibility(View.INVISIBLE);
        listViewModel = ViewModelProviders.of(this).get(CollegeViewModel.class);
        ArrayList<College> mList = new ArrayList<College>();
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        try{
            Log.d("ListItems",windowItems.toString());


            CollegeRecycler collegeRecycler =
                    new CollegeRecycler(getContext(),listViewModel);

            listViewModel.getAllColleges().observe(getViewLifecycleOwner(), new Observer<List<College>>() {
                @Override
                public void onChanged(List<College> colleges) {
                    collegeRecycler.setColleges(colleges);
                }
            });
            recyclerView.setAdapter(collegeRecycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

//url https://open.mapquestapi.com/geocoding/v1/address?key=zGxzl0uE9P0bAMCKd3su8AjJi8r0gtYR&location=Vermont&thumbMaps=true

        return v;
    }
    /*class RetroAsyncTask extends AsyncTask<WindowItem,Void,WindowItem>{
        @Override
        protected WindowItem doInBackground(WindowItem ... items) {
            WindowItem place = items[0];
            int image = 0;
            try {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://open.mapquestapi.com/geocoding/").build();
                RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
                String virginia = retrofitInterface.locationFinder("Virginia").execute().body().toString();
                String northCarolina = retrofitInterface.locationFinder("NorthCarolina").execute().body().toString();
                //int virginiaPic = virginia;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return new WindowItem(place.getName(),0);
        }

        @Override
        protected void onPostExecute(WindowItem windowItem) {
            super.onPostExecute(windowItem);
        }
    }*/

}