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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavoriteCommittees.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavoriteCommittees#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteCommittees extends Fragment {
    private ListView listView;
    public static ArrayList<Committee> favorites = new ArrayList<>();
    public static CommitteeListAdapter listAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FavoriteCommittees() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteCommittees.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteCommittees newInstance(String param1, String param2) {
        FavoriteCommittees fragment = new FavoriteCommittees();
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
        View view =  inflater.inflate(R.layout.fragment_favorite_committees, container, false);
        listView = (ListView)view.findViewById(R.id.listview);
        if(favorites!=null) {
            listAdapter = new CommitteeListAdapter(getContext(), favorites);
            listView.setAdapter(listAdapter);

            Collections.sort(favorites, new Comparator<Committee>() {
                @Override
                public int compare(Committee a, Committee b) {
                    return a.getName().compareTo(b.getName());
                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getActivity().getBaseContext(),CommitteeInfo.class);
                    String object_string = new Gson().toJson(favorites.get(i));
                    intent.putExtra("comm_object", object_string);
                    startActivity(intent);
                }
            });
        }
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

    public static void addFavorite(Committee committee){
        favorites.add(committee);

    }

    public static void removeFavorite(Committee committee){
        for(int i=0;i<favorites.size();i++){
            if(favorites.get(i).getCommitteeId().compareTo(committee.getCommitteeId()) == 0){
                favorites.remove(i);
            }
        }
        listAdapter.notifyDataSetChanged();

    }

    public static int checkFavorite(Committee committee){
        Log.e("TAG",new Gson().toJson(committee));
        for(Committee com : favorites){
            if(com.getName().compareTo(committee.getName()) == 0){
                return 1;
            }
        }
        return 0;
    }

}
