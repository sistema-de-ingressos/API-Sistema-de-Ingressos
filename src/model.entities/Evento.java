public class Evento {

    private long id;
    private String nome;
    private Integer descricao;
    private Date data;
    private String local;
    private Integer lote_atual;
    private Double valor_atual;
    private Integer quantidade_max;
    private Integer quantidade_atual;

    public Evento(long id, String nome, Integer descricao, Date data, String local, Integer lote_atual, Double valor_atual, Integer quantidade_max, Integer quantidade_atual) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.local = local;
        this.lote_atual = lote_atual;
        this.valor_atual = valor_atual;
        this.quantidade_max = quantidade_max;
        this.quantidade_atual = quantidade_atual;
    }
}
