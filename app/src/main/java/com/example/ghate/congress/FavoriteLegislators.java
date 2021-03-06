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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavoriteLegislators.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavoriteLegislators#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteLegislators extends Fragment implements View.OnClickListener {
    private ListView listView;
    Map<String, Integer> hashMap;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static ArrayList<Legislator> favorites = new ArrayList<>();
    public static LegislatorListAdapter listAdapter;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FavoriteLegislators() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteLegislators.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteLegislators newInstance(String param1, String param2) {
        FavoriteLegislators fragment = new FavoriteLegislators();
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

    private void getAlphabetIndexList(ArrayList<Legislator> legislators) {
        hashMap = new LinkedHashMap<String, Integer>();
        int index = 0;
        for (Legislator legislator : legislators) {
            String alphabet_index = legislator.getLastName().substring(0, 1);
            if (hashMap.get(alphabet_index) == null) {
                hashMap.put(alphabet_index, index);

            }
            index++;
        }
    }

    private void displayAlphabetIndex() {
        final LinearLayout alphabetindex = (LinearLayout) getView().findViewById(R.id.alphaindex);
        TextView textView;
        List<String> indexList = new ArrayList<String>(hashMap.keySet());
        for (String alphabet : indexList) {
            textView = (TextView) getActivity().getLayoutInflater().inflate(R.layout.alphabet_index_item, null);
            textView.setText(alphabet);
            textView.setOnClickListener(this);
            alphabetindex.addView(textView);
        }
    }


    public void onClick(View view) {
        TextView selectedIndex = (TextView) view;
        listView.setSelection(hashMap.get(selectedIndex.getText()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_favorite_legislators, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sunlight-148914.appspot.com").addConverterFactory(GsonConverterFactory.create()).build();
        CongressAPIInterface apiClient = retrofit.create(CongressAPIInterface.class);
        Call<LegislatorResults> legislatorCall = apiClient.getLegislatorDetails("house");
        legislatorCall.enqueue(new Callback<LegislatorResults>() {
            @Override
            public void onResponse(Call<LegislatorResults> call, Response<LegislatorResults> response) {
                if (favorites != null) {

                    listAdapter = new LegislatorListAdapter(getContext(), favorites);
                    listView.setAdapter(listAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity().getBaseContext(), LegislatorInfo.class);
                            String object_string = new Gson().toJson(favorites.get(i));
                            intent.putExtra("legislator_object", object_string);
                            startActivity(intent);
                        }
                    });
                    getAlphabetIndexList(favorites);
                    displayAlphabetIndex();

                } else {
                    Log.e("ERR", "Legislator Array Null ::: FAV");
                }
            }

            @Override
            public void onFailure(Call<LegislatorResults> call, Throwable t) {

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

    public static void addFavorite(Legislator legislator) {
        favorites.add(legislator);

        new FavoriteLegislators().getAlphabetIndexList(favorites);


    }

    public static void removeFavorite(Legislator legislator) {
        for (int i = 0; i < favorites.size(); i++) {
            if (favorites.get(i).getBioguideId().compareTo(legislator.getBioguideId()) == 0) {
                favorites.remove(i);
            }
        }


        new FavoriteLegislators().getAlphabetIndexList(favorites);

        listAdapter.notifyDataSetChanged();


    }

    public static int checkFavorite(Legislator legislator) {
        for (Legislator leg : favorites) {
            if (leg.getBioguideId().compareTo(legislator.getBioguideId()) == 0) {
                return 1;
            }
        }
        return 0;
    }


}
