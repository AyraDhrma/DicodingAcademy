package com.ayra.academy.ui.reader.list;

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

public class ModuleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final MyAdapterClickListener listener;
    private List<ModuleEntity> moduleEntities = new ArrayList<>();

    public ModuleListAdapter(MyAdapterClickListener listener) {
        this.listener = listener;
    }

    void setModule(List<ModuleEntity> module) {
        if (module == null) return;
        this.moduleEntities.clear();
        this.moduleEntities.addAll(module);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_module_list_custom, parent, false);

        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        ModuleEntity entity = moduleEntities.get(position);
        ModuleViewHolder moduleViewHolder = (ModuleViewHolder) holder;
        moduleViewHolder.bind(entity.getTitle());
        moduleViewHolder.itemView.setOnClickListener(view -> {
            listener.onItemClicked(holder.getAdapterPosition(), moduleEntities.get(moduleViewHolder.getAdapterPosition()).getModuleId());
        });
    }

    @Override
    public int getItemCount() {
        return moduleEntities.size();
    }

    interface MyAdapterClickListener {
        void onItemClicked(int postion, String moduleId);
    }

    class ModuleViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle, textViewLastSeen;

        ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_module_title);
            textViewLastSeen = itemView.findViewById(R.id.text_last_seen);
        }

        void bind(String title) {
            textViewTitle.setText(title);
        }
    }
}
