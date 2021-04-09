package trabalho01;

import java.util.List;


public interface BebidaDAO
{	
	long inclui(Bebida umaBebida); 

	void altera(Bebida umaBebida)
		throws BebidaNaoEncontradaException; 
	
	void exclui(long id) 
		throws BebidaNaoEncontradaException; 
	
	Bebida recuperaUmaBebida(long numero) 
		throws BebidaNaoEncontradaException; 
	
	List<Bebida> recuperaBebidas();
}