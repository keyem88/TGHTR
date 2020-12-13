package com.meisterk.tghtr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import java.lang.reflect.Array;

public class SongtextFragment extends Fragment {

    private RecyclerView songtextList;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager layoutManager;
    private FirebaseFunctions mFunctions;
    private Array latestSongtexts;
    private final String tag = getClass().getName();


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        return inflater.inflate(R.layout.fragment_songtext, container, false);
    }

    /*
    private Task<String> sayHello(){
        return mFunctions.getHttpsCallable("sayHello")
                .call()
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        String result = (String) task.getResult().getData();
                        return result;
                    }
                });
    };
     */


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFunctions = FirebaseFunctions.getInstance();
        songtextList= view.findViewById(R.id.songtext_list);
        layoutManager = new LinearLayoutManager(getContext());
        songtextList.setLayoutManager(layoutManager);
    }

}