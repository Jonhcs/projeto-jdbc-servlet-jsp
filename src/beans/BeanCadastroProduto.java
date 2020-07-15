package beans;

public class BeanCadastroProduto {
	private Long pid;
	private String nome;
	private Double valor;
	private Long quantidade;
	
	public BeanCadastroProduto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Long getPid() {
		return pid;
	}


	public void setPid(Long pid) {
		this.pid = pid;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
