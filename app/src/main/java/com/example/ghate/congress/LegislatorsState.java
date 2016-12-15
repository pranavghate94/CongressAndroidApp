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
import java.util.Collections;
import java.util.Comparator;
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
 * {@link LegislatorsState.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LegislatorsState#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LegislatorsState extends Fragment implements View.OnClickListener {
    private ListView listView;
    Map<String, Integer> hashMap;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String URL = "http://sunlight-148914.appspot.com";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LegislatorsState() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LegislatorsState.
     */
    // TODO: Rename and change types and number of parameters
    public static LegislatorsState newInstance(String param1, String param2) {
        LegislatorsState fragment = new LegislatorsState();
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
            String alphabet_index = legislator.getStateName().substring(0, 1);
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Gson gson = new Gson();
        final View view = inflater.inflate(R.layout.fragment_legislators_state, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        CongressAPIInterface apiClient = retrofit.create(CongressAPIInterface.class);
        Call<LegislatorResults> legislatorCall = apiClient.getLegislatorDetails("");
        legislatorCall.enqueue(new Callback<LegislatorResults>() {
            @Override
            public void onResponse(Call<LegislatorResults> call, Response<LegislatorResults> response) {
                //Populate ListView here
                try {
                    List<Legislator> legislators = response.body().getResults();
                    final ArrayList<Legislator> legislatorArrayList = new ArrayList<Legislator>(legislators);

                    Collections.sort(legislatorArrayList, new Comparator<Legislator>() {
                        @Override
                        public int compare(Legislator a, Legislator b) {
                            return a.getStateName().compareTo(b.getStateName());
                        }
                    });
                    LegislatorListAdapter listAdapter = new LegislatorListAdapter(getContext(), legislatorArrayList);
                    listView.setAdapter(listAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity().getBaseContext(), LegislatorInfo.class);
                            String object_string = gson.toJson(legislatorArrayList.get(i));
                            intent.putExtra("legislator_object", object_string);
                            startActivity(intent);
                        }
                    });

                    getAlphabetIndexList(legislatorArrayList);
                    displayAlphabetIndex();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LegislatorResults> call, Throwable t) {
                Log.e("LEGEXCP", t.toString());
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
