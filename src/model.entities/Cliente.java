public class Cliente {

    private long id;
    private String nome;
    private Date data_de_nascimento;
    private String cpf;
    private Endereco endereco_id;

    public Cliente(long id, String nome, Date data_de_nascimento, String cpf, Endereco endereco_id) {
        this.id = id;
        this.nome = nome;
        this.data_de_nascimento = data_de_nascimento;
        this.cpf = cpf;
        this.endereco_id = endereco_id;
    }


}
