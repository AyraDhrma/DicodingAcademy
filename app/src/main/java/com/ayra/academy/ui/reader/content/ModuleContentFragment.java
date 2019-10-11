package com.ayra.academy.ui.reader.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ayra.academy.R;
import com.ayra.academy.data.ModuleEntity;
import com.ayra.academy.ui.reader.CourseReaderViewModel;

public class ModuleContentFragment extends Fragment {
    public static final String TAG = ModuleContentFragment.class.getSimpleName();
    private WebView webView;
    private ProgressBar progressBar;
    private CourseReaderViewModel viewModel;

    public ModuleContentFragment() {
    }

    public static ModuleContentFragment newInstance() {
        return new ModuleContentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_module_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView = view.findViewById(R.id.web_view);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            viewModel = ViewModelProviders.of(getActivity()).get(CourseReaderViewModel.class);
            ModuleEntity module = viewModel.getSelectedModule();
            populateWebView(module);
        }
    }

    private void populateWebView(ModuleEntity content) {
        webView.loadData(content.contentEntity.getContent(), "text/html", "UTF-8");
    }

}
