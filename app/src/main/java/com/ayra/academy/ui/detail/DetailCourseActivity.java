package com.ayra.academy.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ayra.academy.R;
import com.ayra.academy.data.CourseEntity;
import com.ayra.academy.data.ModuleEntity;
import com.ayra.academy.ui.reader.CourseReaderActivity;
import com.ayra.academy.utils.GlideApp;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class DetailCourseActivity extends AppCompatActivity {

    public static final String EXTRA_COURSE = "extra_course";
    private Button btnStart;
    private TextView tvTitle, tvDesc, tvDate;
    private RecyclerView rvModule;
    private DetailCourseAdapter detailCourseAdapter;
    private ImageView ivPoster;
    private ProgressBar progressBar;
    private DetailCourseViewModel detailCourseViewModel;
    private List<ModuleEntity> moduleEntityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        detailCourseViewModel = ViewModelProviders.of(this).get(DetailCourseViewModel.class);

        detailCourseAdapter = new DetailCourseAdapter();
        progressBar = findViewById(R.id.progress_bar);
        btnStart = findViewById(R.id.btn_start);
        tvTitle = findViewById(R.id.text_title);
        tvDesc = findViewById(R.id.text_description);
        tvDate = findViewById(R.id.text_date);
        rvModule = findViewById(R.id.rv_module);
        ivPoster = findViewById(R.id.image_poster);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String courseId = extras.getString(EXTRA_COURSE);
            if (courseId != null) {
                detailCourseViewModel.setCourseId(courseId);
                moduleEntityList = detailCourseViewModel.getModules();
                detailCourseAdapter.setModuleEntities(moduleEntityList);
            }
        }

        if (detailCourseViewModel.getCourse() != null) {
            populateCourse(detailCourseViewModel.getCourse());
        }

        rvModule.setNestedScrollingEnabled(false);
        rvModule.setLayoutManager(new LinearLayoutManager(this));
        rvModule.setHasFixedSize(true);
        rvModule.setAdapter(detailCourseAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvModule.getContext(), DividerItemDecoration.VERTICAL);
        rvModule.addItemDecoration(dividerItemDecoration);

    }

    private void populateCourse(CourseEntity courses) {
        tvTitle.setText(courses.getTitle());
        tvDesc.setText(courses.getDescription());
        tvDate.setText(String.format("Deadline %s", courses.getDeadline()));

        GlideApp.with(getApplicationContext())
                .load(courses.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(ivPoster);

        btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(DetailCourseActivity.this, CourseReaderActivity.class);
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, detailCourseViewModel.getCourseId());
            view.getContext().startActivity(intent);
        });
    }

}
