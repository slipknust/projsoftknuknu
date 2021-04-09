package trabalho01;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="bebida")
// @SequenceGenerator(name="SEQUENCIA", 
//	                  sequenceName="SEQ_PRODUTO",
//                    allocationSize=1)

public class Bebida
{	
	private Long id;
	private String tipo;
	private String nome;
	private double preco;
	private Date dataFabricacao;
	private Date dataValidade;
	
	private int versao;

	// ********* Construtores *********

	@Version
	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public Bebida()
	{
	}

	public Bebida(String tipo,
				   String nome,
	               double preco, 
	               Date dataFabricacao,
	               Date dataValidade)
	{	this.tipo = tipo;
		this.nome = nome;
		this.preco = preco;
		this.dataFabricacao = dataFabricacao;	
		this.dataValidade = dataValidade;
	}

	// ********* Métodos do Tipo Get *********

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId()
	{	return id;
	}
	
	public String getTipo()
	{	return tipo;
	}
	
	public String getNome()
	{	return nome;
	}
	
	@Column(name="PRECO")
	public double getPreco()
	{	return preco;
	}
	
	@Transient
	public String preco()
	{	return Util.doubleToStr(preco);
	}

	@Column(name="DATA_FABRICACAO")
	public Date getDataFabricacao()
	{	return dataFabricacao;
	}
	
	@Transient
	public String getDataFabricacaoMasc()
	{	return Util.dateToStr(dataFabricacao);
	}

	@Column(name="DATA_VALIDADE")
	public Date getDataValidade()
	{	return dataValidade;
	}
	
	@Transient
	public String getDataValidadeMasc()
	{	return Util.dateToStr(dataValidade);
	}

	// ********* Métodos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id)
	{	this.id = id;
	}

	public void setTipo(String tipo)
	{	this.tipo = tipo;
	}
	
	public void setNome(String nome)
	{	this.nome = nome;
	}
	
	public void setPreco(double preco)
	{	this.preco = preco;
	}
	
	public void setDataFabricacao(Date dataFabricacao)
	{	this.dataFabricacao = dataFabricacao;	
	}
	
	public void setDataValidade(Date dataValidade)
	{	this.dataValidade = dataValidade;
	}
}

