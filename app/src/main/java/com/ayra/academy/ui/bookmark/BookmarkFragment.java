package com.ayra.academy.ui.bookmark;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.ayra.academy.R;
import com.ayra.academy.data.CourseEntity;
import com.ayra.academy.utils.DataDummy;

public class BookmarkFragment extends Fragment implements BookmarkFragmentCallback{
    private BookmarkAdapter bookmarkAdapter;
    private RecyclerView rvBookmark;
    private ProgressBar progressBar;

    public BookmarkFragment() {

    }

    public static Fragment newInstance() {
        return new BookmarkFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvBookmark = view.findViewById(R.id.rv_bookmark);
        progressBar = view.findViewById(R.id.progressBar);
    }

    @Override
    public void onShareClick (CourseEntity courseEntity) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang.")
                    .setText(String.format("Segera daftar kelas %s di dicoding.com", courseEntity.getTitle()))
                    .startChooser();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            bookmarkAdapter = new BookmarkAdapter(getActivity(), this);
            bookmarkAdapter.setListCourse(DataDummy.generateDummyCourse());

            rvBookmark.setLayoutManager(new LinearLayoutManager(getContext()));
            rvBookmark.setHasFixedSize(true);
            rvBookmark.setAdapter(bookmarkAdapter);
        }
    }
}
