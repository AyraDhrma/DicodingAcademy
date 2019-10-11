package com.ayra.academy.ui.bookmark;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayra.academy.R;
import com.ayra.academy.data.CourseEntity;
import com.ayra.academy.ui.detail.DetailCourseActivity;
import com.ayra.academy.utils.GlideApp;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.AcademyViewHolder> {
    private final Activity activity;
    private final BookmarkFragmentCallback callback;
    private ArrayList<CourseEntity> courseEntities = new ArrayList<>();

    public BookmarkAdapter(Activity activity, BookmarkFragmentCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    public void setListCourse(List<CourseEntity> course) {
        if (course == null) return;
        this.courseEntities.clear();
        this.courseEntities.addAll(course);
    }

    @NonNull
    @Override
    public BookmarkAdapter.AcademyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_bookmark_list, parent, false);

        return new AcademyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AcademyViewHolder holder, int position) {
        CourseEntity entity = courseEntities.get(position);
        
        holder.tvTitle.setText(entity.getTitle());
        holder.tvDate.setText(String.format("Deadline %s", entity.getDeadline()));
        holder.tvDescription.setText(entity.getDescription());
        GlideApp.with(holder.itemView.getContext())
                .load(entity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.ivPoster);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, DetailCourseActivity.class);
            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, entity.getCourseId());
            activity.startActivity(intent);
        });
        holder.ibShare.setOnClickListener(view -> callback.onShareClick(courseEntities.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return courseEntities.size();
    }

    class AcademyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDate, tvDescription;
        private ImageView ivPoster;
        private ImageButton ibShare;

        AcademyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            ivPoster = itemView.findViewById(R.id.img_poster);
            ibShare = itemView.findViewById(R.id.img_share);
        }
    }
}
