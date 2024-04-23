public class Endereco {

    private long id;
    private String logradouro;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;
    private Integer numero;
    private String complemento;

    public Endereco(long id, String logradouro, String cep, String bairro, String cidade, String estado, Integer numero, String complemento) {
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.complemento = complemento;
    }
}
