package school.sptech;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca() {
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        if (livro == null || livro.getTitulo() == null || livro.getTitulo().isBlank() ||
                livro.getAutor() == null || livro.getAutor().isBlank() || livro.getDataPublicacao() == null) {
            return;
        }
        livros.add(livro);
    }

    public void removerLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) return;

        livros.removeIf(livro -> livro.getTitulo().equalsIgnoreCase(titulo));
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) return null;

        return livros.stream()
                .filter(livro -> livro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public Integer contarLivros() {
        return livros.size();
    }

    public List<Livro> obterLivrosAteAno(Integer ano) {
        if (ano == null) return new ArrayList<>();

        return livros.stream()
                .filter(livro -> livro.getDataPublicacao().getYear() <= ano)
                .collect(Collectors.toList());
    }

    public List<Livro> retornarTopCincoLivros() {
        return livros.stream()
                .sorted(Comparator.comparingDouble(Livro::calcularMediaAvaliacoes).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "nome='" + nome + '\'' +
                ", livros=" + livros +
                '}';
    }

}
