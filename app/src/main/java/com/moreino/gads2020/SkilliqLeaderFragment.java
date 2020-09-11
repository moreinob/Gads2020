package com.moreino.gads2020;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkilliqLeaderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ApiClient mApiClient;
    private RecyclerView mRecyclerView;
    private SkillIqLeaderRecyclerAdapter mSkillIqLeaderRecyclerAdapter;

    public SkilliqLeaderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SkilliqLeaderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SkilliqLeaderFragment newInstance(String param1, String param2) {
        SkilliqLeaderFragment fragment = new SkilliqLeaderFragment();
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
        View view = inflater.inflate(R.layout.fragment_skilliq_leader, container, false);
        mApiClient = LeadersClient.buildService(ApiClient.class);
        mRecyclerView = view.findViewById(R.id.list_skill_iq_leaders);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSkillIqLeaderRecyclerAdapter = new SkillIqLeaderRecyclerAdapter(getContext());
        populateSkillIqLeaders();
        return view;
    }


    private void populateSkillIqLeaders() {
        Call<List<SkillIqLeader>> call = mApiClient.getSkillIqLeaders();
        call.enqueue(new Callback<List<SkillIqLeader>>() {
            @Override
            public void onResponse(Call<List<SkillIqLeader>> call, Response<List<SkillIqLeader>> response) {

                List<SkillIqLeader> skillIqLeadersList = response.body();
                mSkillIqLeaderRecyclerAdapter.setData(Collections.unmodifiableList(skillIqLeadersList));
                mRecyclerView.setAdapter(mSkillIqLeaderRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<SkillIqLeader>> call, Throwable t) {
                Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), "Could not load Skill IQ leaders information, check internet connection!", Toast.LENGTH_LONG).show();
            }
        });


    }
}