package com.moreino.gads2020;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LearningLeaderFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ApiClient mApiClient;
    private RecyclerView mRecyclerView;
    private LearningLeaderRecyclerAdapter mLearningLeaderRecyclerAdapter;

    public LearningLeaderFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LearningLeaderFragment newInstance(String param1, String param2) {
        LearningLeaderFragment fragment = new LearningLeaderFragment();
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
        View view = inflater.inflate(R.layout.fragment_learning_leader, container, false);
        mApiClient = LeadersClient.buildService(ApiClient.class);
        mRecyclerView = view.findViewById(R.id.list_learning_leaders);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLearningLeaderRecyclerAdapter = new LearningLeaderRecyclerAdapter(getContext());
        populateLearningLeaders();
        return view;
    }

    private void populateLearningLeaders() {

        Call<List<LearningLeader>> call = mApiClient.getLearningLeaders();

        call.enqueue(new Callback<List<LearningLeader>>() {
            @Override
            public void onResponse(Call<List<LearningLeader>> call, Response<List<LearningLeader>> response) {
                List<LearningLeader> learningLeadersList = response.body();
                mLearningLeaderRecyclerAdapter.setData(learningLeadersList);
                mRecyclerView.setAdapter(mLearningLeaderRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<LearningLeader>> call, Throwable t) {
                Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), "Could not load Learning leaders information, check internet connection!", Toast.LENGTH_LONG).show();
            }
        });

    }
}