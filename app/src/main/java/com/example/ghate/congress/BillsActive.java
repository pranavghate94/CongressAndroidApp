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
 * {@link BillsActive.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BillsActive#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillsActive extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String URL = "http://sunlight-148914.appspot.com";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BillsActive() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BillsActive.
     */
    // TODO: Rename and change types and number of parameters
    public static BillsActive newInstance(String param1, String param2) {
        BillsActive fragment = new BillsActive();
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



        final View view = inflater.inflate(R.layout.fragment_bills_active, container, false);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        CongressAPIInterface apiClient = retrofit.create(CongressAPIInterface.class);
        Call<BillResults> billResultsCall = apiClient.getBillDetails("true");
        billResultsCall.enqueue(new Callback<BillResults>() {
            @Override
            public void onResponse(Call<BillResults> call, Response<BillResults> response) {
                try{
                    List<Bill> bills = response.body().getResults();
                    final ArrayList<Bill> billArrayList = new ArrayList<Bill>(bills);
                    Collections.sort(billArrayList, new Comparator<Bill>() {
                        @Override
                        public int compare(Bill a, Bill b) {
                            return a.getIntroducedOn().compareTo(b.getIntroducedOn());
                        }
                    });
                    BillsListAdapter listAdapter = new BillsListAdapter(getContext(),billArrayList);
                    ListView listView = (ListView)view.findViewById(R.id.listview);
                    listView.setAdapter(listAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity().getBaseContext(),BillInfo.class);
                            String object_string = new Gson().toJson(billArrayList.get(i));
                            intent.putExtra("bill_object",object_string);
                            startActivity(intent);
                        }
                    });
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BillResults> call, Throwable t) {
                Log.e("BILLERR",t.toString());
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
