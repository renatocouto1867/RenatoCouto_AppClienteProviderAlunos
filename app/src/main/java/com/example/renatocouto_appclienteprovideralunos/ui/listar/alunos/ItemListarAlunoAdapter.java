package com.example.renatocouto_appclienteprovideralunos.ui.listar.alunos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.renatocouto_appclienteprovideralunos.entity.Aluno;
import com.example.renatocouto_appclienteprovideralunos.R;

import java.util.List;

public class ItemListarAlunoAdapter extends RecyclerView.Adapter<ItemListarAlunoAdapter.ViewHolder> {
    private final OnItemClickListener onItemClickListener;
    private final List<Aluno> alunos;

    public ItemListarAlunoAdapter(List<Aluno> alunos, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.alunos = alunos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alunos_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Aluno aluno = alunos.get(position);

        holder.textViewNome.setText(aluno.getNome());
        holder.textViewIdade.setText("Idade: "+aluno.getIdade());
        holder.textViewNota1.setText(String.format("%.2f", aluno.getNota1()));
        holder.textViewNota2.setText(String.format("%.2f", aluno.getNota2()));
        holder.textViewNota3.setText(String.format("%.2f", aluno.getNota3()));

        holder.btnEditAluno.setOnClickListener(v -> onItemClickListener.onEditClick(aluno));
        holder.btnDeleteAluno.setOnClickListener(v -> onItemClickListener.onDeleteClick(aluno));
    }

    @Override
    public int getItemCount() {
        return alunos == null ? 0 : alunos.size();
    }

    public interface OnItemClickListener {
        void onEditClick(Aluno aluno);

        void onDeleteClick(Aluno aluno);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome;
        TextView textViewIdade;
        TextView textViewNota1;
        TextView textViewNota2;
        TextView textViewNota3;
        ImageButton btnEditAluno;
        ImageButton btnDeleteAluno;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.tv_aluno);
            textViewIdade = itemView.findViewById(R.id.tv_idade);
            textViewNota1 = itemView.findViewById(R.id.tv_nota1);
            textViewNota2 = itemView.findViewById(R.id.tv_nota2);
            textViewNota3 = itemView.findViewById(R.id.tv_nota3);

            btnEditAluno = itemView.findViewById(R.id.btn_edit_Disciplina);
            btnDeleteAluno = itemView.findViewById(R.id.btn_delete_Disciplina);
        }
    }
}
