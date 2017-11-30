package oman.ita.com.fragmentdemo.hct_home;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import oman.ita.com.fragmentdemo.R;
import oman.ita.com.fragmentdemo.hct_students.HCTStudents_Fragments;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HCTHomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HCTHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HCTHomeFragment extends Fragment implements HCTHomeAdapter.OnAdapterItemClick {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
   private static final int NO_COLUMNS=3;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String[] home_items;
    TypedArray home_items_images;
    ArrayList<HomeItems> homeItems;
    RecyclerView homeRecycler;

    private OnFragmentInteractionListener mListener;

    public HCTHomeFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HCTHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HCTHomeFragment newInstance(String param1, String param2) {
        HCTHomeFragment fragment = new HCTHomeFragment();
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
        View view=inflater.inflate(R.layout.fragment_hcthome, container, false);
        home_items=getResources().getStringArray(R.array.home_items);
        home_items_images=getResources().obtainTypedArray(R.array.home_items_images);
        homeItems=new ArrayList<>();
        homeRecycler =(RecyclerView) view.findViewById(R.id.fragment_home);
        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
        homeRecycler.setLayoutManager(manager);
        createHomeItems();
        HCTHomeAdapter adapter=new HCTHomeAdapter(getActivity(),homeItems);
        adapter.setOnItemClick(this);
        homeRecycler.setAdapter(adapter);

        return view;
    }

    private void createHomeItems() {
        for (int i=0;i<home_items.length;i++){
            HomeItems items=new HomeItems();
            items.setHomeItems(home_items[i]);
            items.setHomeImages(home_items_images.getResourceId(i,-1));
            homeItems.add(items);
        }
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(),
                "this is the position "+position,Toast.LENGTH_SHORT).show();
        Fragment fragment=null;
        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
        switch (position){
            case 0:
                fragment=new HCTStudents_Fragments();
                break;
            case 1:
                break;
            case 3:
                break;
        }
        transaction.replace(R.id.main_container,fragment);
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();
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
