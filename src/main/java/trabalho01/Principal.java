package trabalho01;

import java.util.Date;
import java.util.List;
import corejava.Console;

public class Principal
{	public static void main (String[] args) 
	{	
		String tipo;
		String nome;
		double preco;
		String dataFabricacao;
		String dataValidade;
		Bebida umaBebida;

		BebidaDAO bebidaDAO = FabricaDeDAOs.getDAO(BebidaDAO.class);

		boolean continua = true;
		while (continua)
		{	System.out.println('\n' + "O que voc� deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um produto");
			System.out.println("2. Alterar um produto");
			System.out.println("3. Remover um produto");
			System.out.println("4. Listar todos os produtos");
			System.out.println("5. Sair");
						
			int opcao = Console.readInt('\n' + 
							"Digite um n�mero entre 1 e 5:");
					
			switch (opcao)
			{	case 1:
				{
					tipo = Console.readLine('\n' + 
							"Informe o tipo da bebida: ");
					nome = Console.readLine('\n' + 
						"Informe o nome da bebida: ");
					preco = Console.readDouble(
						"Informe o pre�o da bebida: ");
					dataFabricacao = Console.readLine(
						"Informe a data de fabrica��o da bebida: ");
					dataValidade = Console.readLine(
						"Informe a data de validade da bebida: ");
						
					umaBebida = new Bebida(tipo, nome, preco, Util.strToDate(dataFabricacao), Util.strToDate(dataValidade));
					
					bebidaDAO.inclui(umaBebida);
					
					System.out.println('\n' + "Produto n�mero " + 
							umaBebida.getId() + " inclu�do com sucesso!");	

					break;
				}

				case 2:
				{	int resposta = Console.readInt('\n' + 
						"Digite o n�mero do produto que voc� deseja alterar: ");
										
					try
					{	umaBebida = bebidaDAO.recuperaUmaBebida(resposta);
					}
					catch(BebidaNaoEncontradaException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"N�mero = " + umaBebida.getId() + 
						"    Tipo = " + umaBebida.getTipo() +
						"    Nome = " + umaBebida.getNome() +
						"    Pre�o = " + umaBebida.getPreco());
												
					System.out.println('\n' + "O que voc� deseja alterar?");
					System.out.println('\n' + "1. Tipo");
					System.out.println('\n' + "2. Nome");
					System.out.println('\n' + "3. Preco");
					System.out.println('\n' + "4. Data de Fabrica��o");
					System.out.println('\n' + "5. Data de Validade");

					int opcaoAlteracao = Console.readInt('\n' + 
											"Digite um n�mero de 1 a 5:");
					
					switch (opcaoAlteracao)
					{	case 1:
							String novoTipo = Console.
										readLine("Digite o novo tipo da bebida: ");
							
							umaBebida.setTipo(novoTipo);

							try
							{	bebidaDAO.altera(umaBebida);

								System.out.println('\n' + 
									"Altera��o de tipo efetuada com sucesso!");
							}
							catch(BebidaNaoEncontradaException e)
							{	System.out.println('\n' + e.getMessage());
							}
							try {
								throw new EstadoDeObjetoObsoletoException();
							} catch (EstadoDeObjetoObsoletoException e1) {
								System.out.println('\n' + "A opera��o n�o foi realizada porque os dados j� foram modificados por outro usu�rio");
							}	
							break;
					
						case 2:
							String novoNome = Console.
									readLine("Digite o novo Nome: ");
							
							umaBebida.setNome(novoNome);

							try
							{	bebidaDAO.altera(umaBebida);

								System.out.println('\n' + 
									"Altera��o de nome efetuada com sucesso!");						
							}
							catch(BebidaNaoEncontradaException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
							
						case 3:
							double novoPreco = Console.
									readDouble("Digite o novo Pre�o: ");
							
							umaBebida.setPreco(novoPreco);

							try
							{	bebidaDAO.altera(umaBebida);

								System.out.println('\n' + 
									"Altera��o de pre�o com sucesso!");						
							}
							catch(BebidaNaoEncontradaException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
							
						case 4:
							String novaDataFabricacao = Console.
									readLine("Digite a nova Data de Fabrica��o: ");
							
							umaBebida.setDataFabricacao(Util.strToDate(novaDataFabricacao));

							try
							{	bebidaDAO.altera(umaBebida);

								System.out.println('\n' + 
									"Altera��o de Data de Fabrica��o efetuada " +
									"com sucesso!");						
							}
							catch(BebidaNaoEncontradaException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
							
						case 5:
							String novaDataValidade = Console.
									readLine("Digite a nova Data de Validade: ");
							
							umaBebida.setDataValidade(Util.strToDate(novaDataValidade));

							try
							{	bebidaDAO.altera(umaBebida);

								System.out.println('\n' + 
									"Altera��o de lance m�nimo efetuada " +
									"com sucesso!");						
							}
							catch(BebidaNaoEncontradaException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;

						default:
							System.out.println('\n' + "Op��o inv�lida!");
					}

					break;
				}

				case 3:
				{	int resposta = Console.readInt('\n' + 
						"Digite o n�mero da bebida que voc� deseja remover: ");
									
					try
					{	umaBebida = bebidaDAO.
										recuperaUmaBebida(resposta);
					}
					catch(BebidaNaoEncontradaException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"N�mero = " + umaBebida.getId() + 
						"    Nome = " + umaBebida.getNome());
														
					String resp = Console.readLine('\n' + 
						"Confirma a remo��o da bebida?");

					if(resp.equals("s"))
					{	try
						{	bebidaDAO.exclui (umaBebida.getId());
							System.out.println('\n' + 
								"Bebida removida com sucesso!");
						}
						catch(BebidaNaoEncontradaException e)
						{	System.out.println('\n' + e.getMessage());
						}
					}
					else
					{	System.out.println('\n' + 
							"Bebida n�o removida.");
					}
					
					break;
				}

				case 4:
				{	
					List<Bebida> bebidas = bebidaDAO.recuperaBebidas();

					for (Bebida bebida : bebidas)
					{	
						System.out.println('\n' + 
							"Id = " + bebida.getId() +
							"  Tipo = " + bebida.getTipo() +
							"  Nome = " + bebida.getNome() +
							"  Pre�o = " + bebida.getPreco() +
							"  Data Fabrica��o = " + bebida.getDataFabricacaoMasc() +
							"  Data Validade = " + bebida.getDataValidadeMasc());
					}
					
					break;
				}

				case 5:
				{	continua = false;
					break;
				}

				default:
					System.out.println('\n' + "Op��o inv�lida!");
			}
		}		
	}
}
