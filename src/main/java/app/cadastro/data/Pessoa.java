package app.cadastro.data;

public class Pessoa {
    private Long id;
    private String nome;
    private int idade;
    private String cpf;
    private String email;

    public Pessoa() {
    }

    public Pessoa(Long id, String nome, int idade, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.email = email;
    }
}
