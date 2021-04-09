package trabalho01;

public class BebidaNaoEncontradaException extends Exception
{	
	private final static long serialVersionUID = 1;
	
	private int codigo;
	
	public BebidaNaoEncontradaException(String msg)
	{	super(msg);
	}

	public BebidaNaoEncontradaException(int codigo, String msg)
	{	super(msg);
		this.codigo = codigo;
	}
	
	public int getCodigoDeErro()
	{	return codigo;
	}
}	