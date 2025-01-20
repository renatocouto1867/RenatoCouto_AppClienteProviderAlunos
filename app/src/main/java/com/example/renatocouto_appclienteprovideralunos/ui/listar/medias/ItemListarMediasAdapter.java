package com.example.renatocouto_appclienteprovideralunos.ui.listar.medias;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.renatocouto_appclienteprovideralunos.R;
import com.example.renatocouto_appclienteprovideralunos.entity.Aluno;

import java.util.List;

public class ItemListarMediasAdapter extends RecyclerView.Adapter<ItemListarMediasAdapter.ViewHolder> {

    private final List<Aluno> alunos;

    public ItemListarMediasAdapter(List<Aluno> alunos) {

        this.alunos = alunos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medias_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Aluno aluno = alunos.get(position);
        holder.textViewNome.setText(aluno.getNome());
        holder.textViewMedia.setText(String.format("%.2f", aluno.getMedia()));
        holder.textViewStatus.setText(aluno.getStatus());

    }

    @Override
    public int getItemCount() {
        return alunos == null ? 0 : alunos.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome;
        TextView textViewMedia;
        TextView textViewStatus;
        ImageButton btnEditAluno;
        ImageButton btnDeleteAluno;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.tv_media_aluno);
            textViewMedia = itemView.findViewById(R.id.tv_media);
            textViewStatus = itemView.findViewById(R.id.tv_status);

            btnEditAluno = itemView.findViewById(R.id.btn_edit_Disciplina);
            btnDeleteAluno = itemView.findViewById(R.id.btn_delete_Disciplina);
        }
    }
}
