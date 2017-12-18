package com.nof.utilities.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nof.utilities.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/18.
 */

public class TitleFragment extends ListFragment {
    boolean mDualPane;
    int mCurCheckPosition = 0;
    List<String> data = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("onAttach");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("onActivityCreated");
        for (int i = 0; i < 50; i++) {
            data.add("Title "+i);
        }
        setListAdapter(new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,data));
        View detailsFrame = getActivity().findViewById(R.id.details);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if(savedInstanceState != null){
            mCurCheckPosition = savedInstanceState.getInt("curChioce", 0);
        }

        if(mDualPane){
            getListView().setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            showDetail(mCurCheckPosition);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("onSaveInstanceState");
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        showDetail(position);
    }

    private void showDetail(int index){
        mCurCheckPosition = index;
        if(mDualPane){
            getListView().setItemChecked(index,true);
            DetailFragment details = (DetailFragment) getFragmentManager().findFragmentById(R.id.details);
            if(details == null || details.getShowIndex() != index){
                details = DetailFragment.newInstance(index);
                getFragmentManager().beginTransaction()
                        .replace(R.id.details,details)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
        }else{
            Intent intent = new Intent();
            intent.setClass(getContext(), DetailActivity.class);
            intent.putExtra("index",index);
            startActivity(intent);
        }
    }
}
