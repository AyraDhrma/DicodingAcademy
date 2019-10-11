package com.ayra.academy.ui.academy;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ayra.academy.R;
import com.ayra.academy.utils.DataDummy;

public class AcademyFragment extends Fragment {
    private RecyclerView recyclerViewCourse;
    private ProgressBar progressBar;
    private AcademyAdapter academyAdapter;

    public AcademyFragment() {

    }

    public static Fragment newInstance() {
        return new AcademyFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_academy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewCourse = view.findViewById(R.id.rv_academy);
        progressBar = view.findViewById(R.id.progressBar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            academyAdapter = new AcademyAdapter(getActivity());
            academyAdapter.setListCourse(DataDummy.generateDummyCourse());

            recyclerViewCourse.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewCourse.setHasFixedSize(true);
            recyclerViewCourse.setAdapter(academyAdapter);
        }
    }

}
