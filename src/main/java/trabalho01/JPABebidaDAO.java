package trabalho01;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;

public class JPABebidaDAO implements BebidaDAO
{	
	public long inclui(Bebida umaBebida) 
	{	EntityManager em = null;
		EntityTransaction tx = null;
		
		try
		{	// transiente - objeto novo: ainda não persistente
			// persistente - apos ser persistido 
			// destacado - objeto persistente não vinculado a um entity manager
		
		em = FabricaDeEntityManager.criarSessao();
		tx = em.getTransaction();
		tx.begin();
		
		em.persist(umaBebida);
		
		tx.commit();			
			
			
			
			
			return umaBebida.getId();
		} 
		catch(RuntimeException e)
		{	if (tx != null)
			{	
				try
				{	tx.rollback();
				}
				catch(RuntimeException he)
				{ }
			}
			throw e;
		}
		finally
		{	em.close();		
		}
	}

	public void altera(Bebida umaBebida) throws BebidaNaoEncontradaException
	{	EntityManager em = null;
		EntityTransaction tx = null;
		Bebida bebida = null;
		try
		{	
			em = FabricaDeEntityManager.criarSessao();
			tx = em.getTransaction();
			tx.begin();
			
			bebida = em.find(Bebida.class, umaBebida.getId(), LockModeType.PESSIMISTIC_WRITE);
			
			if(bebida == null)
			{
				tx.rollback();
				throw new BebidaNaoEncontradaException("Bebida não encontrada");
				
			}
			
			em.merge(umaBebida);
			
			tx.commit();
		} 
		
		catch(OptimisticLockException e) 
		{
			if(tx != null)
			{
				tx.rollback();
			}
			try {
				throw new EstadoDeObjetoObsoletoException();
			} catch (EstadoDeObjetoObsoletoException e1) {
				
			}
			
		}
		
		catch(RuntimeException e)
		{ 
			if (tx != null)
		    {   
				try
		        {	tx.rollback();
		        }
		        catch(RuntimeException he)
		        { }
		    }
		    throw e;
		}
		finally
		{   em.close();
		}
	}

	public void exclui(long numero) throws BebidaNaoEncontradaException 
	{	EntityManager em = null;
		EntityTransaction tx = null;
		
		try
		{	
			em = FabricaDeEntityManager.criarSessao();
			tx = em.getTransaction();
			tx.begin();

			Bebida bebida = em.find(Bebida.class, new Long(numero), LockModeType.PESSIMISTIC_WRITE);
			
			if(bebida == null)
			{	tx.rollback();
				throw new BebidaNaoEncontradaException("Bebida não encontrado");
			}

			em.remove(bebida);
			tx.commit();
		} 
		catch(RuntimeException e)
		{   
			if (tx != null)
		    {   
				try
		        {	tx.rollback();
		        }
		        catch(RuntimeException he)
		        { }
		    }
		    throw e;
		}
		finally
		{   em.close();
		}
	}

	public Bebida recuperaUmaBebida(long numero) throws BebidaNaoEncontradaException
	{	EntityManager em = null;
		
		try
		{	
			em = FabricaDeEntityManager.criarSessao();

			Bebida umaBebida = em.find(Bebida.class, numero);
			
			// Características no método find():
			// 1. É genérico: não requer um cast.
			// 2. Retorna null caso a linha não seja encontrada no banco.

			if(umaBebida == null)
			{	throw new BebidaNaoEncontradaException("Bebida não encontrado");
			}
			return umaBebida;
		} 
		finally
		{   em.close();
		}
	}

	public List<Bebida> recuperaBebidas()
	{	EntityManager em = null;
		
		try
		{	em = FabricaDeEntityManager.criarSessao();

		@SuppressWarnings("unchecked")
		List<Bebida> bebidas = em.createQuery("select b from Bebida b order by b.id")
									.getResultList();

			// Retorna um List vazio caso a tabela correspondente esteja vazia.
			
			return bebidas;
		} 
		finally
		{   em.close();
		}
	}
	
}
