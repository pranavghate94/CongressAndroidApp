package com.example.ghate.congress;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CommitteesHouse.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CommitteesHouse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommitteesHouse extends Fragment {
    private String URL = "https://sunlight-148914.appspot.com";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CommitteesHouse() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommitteesHouse.
     */
    // TODO: Rename and change types and number of parameters
    public static CommitteesHouse newInstance(String param1, String param2) {
        CommitteesHouse fragment = new CommitteesHouse();
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
        final View view =  inflater.inflate(R.layout.fragment_commitees_house, container, false);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        CongressAPIInterface apiClient = retrofit.create(CongressAPIInterface.class);
        Call<CommitteeResults> committeeCall = apiClient.getCommitteeDetails("house");
        committeeCall.enqueue(new Callback<CommitteeResults>() {
            @Override
            public void onResponse(Call<CommitteeResults> call, Response<CommitteeResults> response) {
                List<Committee> committees = response.body().getResults();
                final ArrayList<Committee> committeeArrayList = new ArrayList<Committee>(committees);
                Collections.sort(committeeArrayList, new Comparator<Committee>() {
                    @Override
                    public int compare(Committee a, Committee b) {
                        return a.getName().compareTo(b.getName());
                    }
                });

                CommitteeListAdapter listAdapter = new CommitteeListAdapter(getContext(),committeeArrayList);
                ListView listView = (ListView)view.findViewById(R.id.listview);
                listView.setAdapter(listAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity().getBaseContext(),CommitteeInfo.class);
                        String object_string = new Gson().toJson(committeeArrayList.get(i));
                        intent.putExtra("comm_object",object_string);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<CommitteeResults> call, Throwable t) {
                Log.e("COMERR",t.toString());
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
