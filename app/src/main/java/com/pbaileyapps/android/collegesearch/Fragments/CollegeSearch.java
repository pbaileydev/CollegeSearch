package com.pbaileyapps.android.collegesearch.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.pbaileyapps.android.collegesearch.Adapters.WindowRecyclerAdapter;
import com.pbaileyapps.android.collegesearch.R;
import com.pbaileyapps.android.collegesearch.RetrofitComponents.RetrofitInterface;
import com.pbaileyapps.android.collegesearch.RetrofitComponents.WindowItem;
import com.pbaileyapps.android.collegesearch.RoomComponents.College;
import com.pbaileyapps.android.collegesearch.RoomComponents.CollegeViewModel;

import java.io.IOException;
import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollegeSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollegeSearch extends Fragment implements WindowRecyclerAdapter.likeInterface{
    private ArrayList<WindowItem> universityList;
    private WindowRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Context mContext;
    private View v;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CollegeViewModel collegeViewModel;
    public CollegeSearch() {
        mContext = getContext();
        try {


        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        if(universityList != null) {
            Log.d("ONRESUME", universityList.toString());
            recyclerView = v.findViewById(R.id.recyclerView);
            collegeViewModel = ViewModelProviders.of(this).get(CollegeViewModel.class);
            adapter = new WindowRecyclerAdapter(v.getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
            adapter.setWindowItemList(universityList);
            adapter.notifyDataSetChanged();
        }
        else{
            return;
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CollegeSearch newInstance(String param1, String param2) {
        CollegeSearch fragment = new CollegeSearch();
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
        v = inflater.inflate(R.layout.fragment_list, container, false);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        universityList = new ArrayList<WindowItem>();
        recyclerView = view.findViewById(R.id.recyclerView);
        collegeViewModel = ViewModelProviders.of(this).get(CollegeViewModel.class);
        adapter = new WindowRecyclerAdapter(view.getContext());
        adapter.setWindowItemList(universityList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        SearchView searchView = view.findViewById(R.id.search_bar);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                try {
                    universityList.clear();
                    adapter.setWindowItemList(null);
                    adapter.anInterface = new WindowRecyclerAdapter.likeInterface() {
                        @Override
                        public void likeCollege(String collegeName, String countryName) {
                            collegeViewModel.insertCollege(new College(collegeName,countryName));
                        }
                    };
                    String url = "http://universities.hipolabs.com/";
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create()).build();
                    RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
                    String country = "search?country="+query;
                    String totalUrl = url + country;
                    Call<JsonArray> item = retrofitInterface.locationFinder(totalUrl);
                    item.enqueue(new Callback<JsonArray>() {
                        @Override
                        public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                            Log.d("RESPONSE",response.message());
                            if(!response.isSuccessful()){

                                return;
                            }
                            Log.i("CodeRate",String.valueOf(response.code()));

                            Log.i("BODY",response.body().toString());
                            JsonArray JsonArray = (JsonArray) response.body();

                            /**/
                            int i = 0;
                            while(i < 10 && i < JsonArray.size()) {
                                JsonElement object = response.body().get(i);
                                String name = object.getAsJsonObject().get("name").getAsString();
                                universityList.add(new WindowItem(name,query));
                                Log.d("ItemElement",object.toString());
                                i++;
                            }
                            //Log.d("McDonalds",universityList.get(0).toString());
                            adapter.setWindowItemList(universityList);
                            adapter.notifyDataSetChanged();
                            //
                        }

                        @Override
                        public void onFailure(Call<JsonArray> call, Throwable t) {

                            if(t instanceof IOException){
                                Toast.makeText(getContext(),"Network problem",Toast.LENGTH_LONG).show();
                            }
                            else {

                                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    public void setList(ArrayList<WindowItem> items) {
        if(v == null){
            return;
        }
        else {
            universityList = items;
            Log.v("MTAG", v.toString());
        }
    }

    @Override
    public void likeCollege(String collegeName, String countryName) {
        collegeViewModel.insertCollege(new College(collegeName,countryName));
    }
}