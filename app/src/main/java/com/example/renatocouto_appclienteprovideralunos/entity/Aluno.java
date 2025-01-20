package com.example.renatocouto_appclienteprovideralunos.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "alunos")
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String nome;
    private int idade;
    private double nota1, nota2, nota3;

    public String getStatus() {
        if (getMedia() >= 6.0) {
            return "aprovado";
        } else return "reprovado";
    }

    public double getMedia() {
        return (nota1 + nota2 + nota3) / 3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }
}
