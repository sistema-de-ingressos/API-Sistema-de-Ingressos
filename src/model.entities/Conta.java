public class Conta {

    private Cliente cliente_nome;
    private String email;
    private String senha;

    public Conta(Cliente cliente_nome, String email, String senha) {
        this.cliente_nome = cliente_nome;
        this.email = email;
        this.senha = senha;
    }
}
