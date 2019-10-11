package com.ayra.academy.ui.detail;

import android.media.midi.MidiOutputPort;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayra.academy.R;
import com.ayra.academy.data.ModuleEntity;

import java.util.ArrayList;
import java.util.List;

public class DetailCourseAdapter extends RecyclerView.Adapter<DetailCourseAdapter.ModuleViewHolder> {
    private List<ModuleEntity> moduleEntities = new ArrayList<>();

    public void setModuleEntities(List<ModuleEntity> entities) {
        if (entities == null) return;
        moduleEntities.clear();
        moduleEntities.addAll(entities);
    }

    @NonNull
    @Override
    public DetailCourseAdapter.ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_module_list, parent, false);

        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailCourseAdapter.ModuleViewHolder holder, int position) {
        holder.bind(moduleEntities.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return moduleEntities.size();
    }

    class ModuleViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;

        ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.text_module_title);
        }

        void bind(String title) {
            tvTitle.setText(title);
        }

    }
}
