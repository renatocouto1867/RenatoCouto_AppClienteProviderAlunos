package com.example.renatocouto_appclienteprovideralunos.ui.listar.alunos;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.renatocouto_appclienteprovideralunos.MainActivity;
import com.example.renatocouto_appclienteprovideralunos.R;
import com.example.renatocouto_appclienteprovideralunos.auxiliar.Mensagens;
import com.example.renatocouto_appclienteprovideralunos.entity.Aluno;
import com.example.renatocouto_appclienteprovideralunos.ui.cadastra.CadastrarFragment;

import java.util.ArrayList;
import java.util.List;

public class ListarAlunosFragment extends Fragment {

    public static final Uri URI_ALUNOS = Uri.parse(
            "content://com.example.renatocouto_appprovideralunos.provider/alunos");
    private ItemListarAlunoAdapter itemListarAlunoAdapter;
    private RecyclerView recyclerViewAluno;
    private ProgressBar progressBar;
    private TextView textViewProgress;

    public ListarAlunosFragment() {

    }

    public static ListarAlunosFragment newInstance() {
        return new ListarAlunosFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listar_aluno, container, false);

        inicializarViews(view);

        carregarAlunos();

        return view;
    }

    private void inicializarViews(View view) {
        progressBar = view.findViewById(R.id.media_progress_circular);
        recyclerViewAluno = view.findViewById(R.id.recyclerViewAluno);
        textViewProgress = view.findViewById(R.id.tv_media_carregando);
    }

    private void carregarAlunos() {
        ContentResolver contentResolver = requireContext().getContentResolver();
        Cursor cursor = contentResolver.query(URI_ALUNOS, null, null, null, null);

        List<Aluno> alunoList = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Aluno aluno = new Aluno();
                aluno.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                aluno.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
                aluno.setIdade(cursor.getInt(cursor.getColumnIndexOrThrow("idade")));
                aluno.setNota1(cursor.getDouble(cursor.getColumnIndexOrThrow("nota1")));
                aluno.setNota2(cursor.getDouble(cursor.getColumnIndexOrThrow("nota2")));
                aluno.setNota3(cursor.getDouble(cursor.getColumnIndexOrThrow("nota3")));

                alunoList.add(aluno);
            }
            cursor.close();
        }
        progressBar.setVisibility(View.VISIBLE);
        textViewProgress.setVisibility(View.VISIBLE);

        if (alunoList != null && !alunoList.isEmpty()) {

            configurarRecyclerView(alunoList);
            progressBar.setVisibility(View.GONE);
            textViewProgress.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            configurarRecyclerView(alunoList);
            textViewProgress.setText(R.string.sem_aluno_cadastrado);
        }
    }

    private void configurarRecyclerView(List<Aluno> alunoList) {

        itemListarAlunoAdapter = new ItemListarAlunoAdapter(alunoList, new ItemListarAlunoAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(Aluno aluno) {
                editaAluno(aluno);
            }

            @Override
            public void onDeleteClick(Aluno aluno) {
                deleteAluno(aluno);
            }

        });

        recyclerViewAluno.setAdapter(itemListarAlunoAdapter);
        recyclerViewAluno.setLayoutManager(new LinearLayoutManager(requireContext()));

    }

    private void editaAluno(Aluno aluno) {
        Bundle result = new Bundle();
        result.putSerializable("aluno", aluno);

        Fragment fragment = CadastrarFragment.newInstance();
        fragment.setArguments(result);

        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.iniciarFragment(fragment, R.string.editar_cadastro);
        }
    }


    private void deleteAluno(Aluno aluno) {
        new AlertDialog.Builder(getContext()).setTitle(R.string.confirmar_exclusao)
                .setMessage(getString(R.string.realmente_deseja_deletar) + aluno.getNome() + "?")
                .setPositiveButton(getString(R.string.deletar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //sim
                        deletarSim(aluno);
                        carregarAlunos();
                    }
                }).setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //não
                        dialog.dismiss();
                    }
                }).create().show();

    }

    private void deletarSim(Aluno aluno) {
        Uri uri = Uri.parse("content://com.example.renatocouto_appprovideralunos.provider/alunos/" + aluno.getId());

        int linhasExcluidas = requireContext().getContentResolver().delete(uri, null, null);

        if (linhasExcluidas > 0) {
            Mensagens.showSucesso(requireView(), getString(R.string.aluno_deletado_com_sucesso));
        } else {
            Mensagens.showErro(requireView(), getString(R.string.falha_ao_deletar_o_aluno));
        }
    }
}
