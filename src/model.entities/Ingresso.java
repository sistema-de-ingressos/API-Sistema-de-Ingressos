public class Ingresso {

    private long id;
    private Cliente cliente_id;
    private Evento evento_id;
    private Integer lote_comprado;
    private Double valor_pago;
    private String forma_de_pagamento;

    public Ingresso(long id, Cliente cliente_id, Evento evento_id, Integer lote_comprado, Double valor_pago, String forma_de_pagamento) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.evento_id = evento_id;
        this.lote_comprado = lote_comprado;
        this.valor_pago = valor_pago;
        this.forma_de_pagamento = forma_de_pagamento;
    }
}
