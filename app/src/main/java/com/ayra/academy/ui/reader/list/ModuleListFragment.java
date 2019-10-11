package com.ayra.academy.ui.reader.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ayra.academy.R;
import com.ayra.academy.data.ModuleEntity;
import com.ayra.academy.ui.reader.CourseReaderCallback;
import com.ayra.academy.ui.reader.CourseReaderViewModel;

import java.util.ArrayList;

public class ModuleListFragment extends Fragment implements ModuleListAdapter.MyAdapterClickListener {

    public static final String TAG = ModuleListFragment.class.getSimpleName();
    private ModuleListAdapter adapter;
    private CourseReaderCallback courseReaderCallback;
    private RecyclerView rvModuleCustom;
    private ProgressBar progressBar;
    private CourseReaderViewModel viewModel;

    public ModuleListFragment() {
        // Required empty public constructor
    }

    public static ModuleListFragment newInstance() {
        return new ModuleListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvModuleCustom = view.findViewById(R.id.rv_module);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            viewModel = ViewModelProviders.of(getActivity()).get(CourseReaderViewModel.class);
            adapter = new ModuleListAdapter(this);
            populateRecycleView(viewModel.getModules());
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        courseReaderCallback = ((CourseReaderCallback) context);
    }

    private void populateRecycleView(ArrayList<ModuleEntity> moduleEntities) {
        progressBar.setVisibility(View.GONE);
        adapter.setModule(moduleEntities);
        rvModuleCustom.setLayoutManager(new LinearLayoutManager(getContext()));
        rvModuleCustom.setHasFixedSize(true);
        rvModuleCustom.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvModuleCustom.getContext(), DividerItemDecoration.VERTICAL);
        rvModuleCustom.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onItemClicked(int postion, String moduleId) {
        courseReaderCallback.moveTo(postion, moduleId);
        viewModel.setSelectedModule(moduleId);
    }
}
