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
		{	System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um produto");
			System.out.println("2. Alterar um produto");
			System.out.println("3. Remover um produto");
			System.out.println("4. Listar todos os produtos");
			System.out.println("5. Sair");
						
			int opcao = Console.readInt('\n' + 
							"Digite um número entre 1 e 5:");
					
			switch (opcao)
			{	case 1:
				{
					tipo = Console.readLine('\n' + 
							"Informe o tipo da bebida: ");
					nome = Console.readLine('\n' + 
						"Informe o nome da bebida: ");
					preco = Console.readDouble(
						"Informe o preço da bebida: ");
					dataFabricacao = Console.readLine(
						"Informe a data de fabricação da bebida: ");
					dataValidade = Console.readLine(
						"Informe a data de validade da bebida: ");
						
					umaBebida = new Bebida(tipo, nome, preco, Util.strToDate(dataFabricacao), Util.strToDate(dataValidade));
					
					bebidaDAO.inclui(umaBebida);
					
					System.out.println('\n' + "Produto número " + 
							umaBebida.getId() + " incluído com sucesso!");	

					break;
				}

				case 2:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número do produto que você deseja alterar: ");
										
					try
					{	umaBebida = bebidaDAO.recuperaUmaBebida(resposta);
					}
					catch(BebidaNaoEncontradaException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + umaBebida.getId() + 
						"    Tipo = " + umaBebida.getTipo() +
						"    Nome = " + umaBebida.getNome() +
						"    Preço = " + umaBebida.getPreco());
												
					System.out.println('\n' + "O que você deseja alterar?");
					System.out.println('\n' + "1. Tipo");
					System.out.println('\n' + "2. Nome");
					System.out.println('\n' + "3. Preco");
					System.out.println('\n' + "4. Data de Fabricação");
					System.out.println('\n' + "5. Data de Validade");

					int opcaoAlteracao = Console.readInt('\n' + 
											"Digite um número de 1 a 5:");
					
					switch (opcaoAlteracao)
					{	case 1:
							String novoTipo = Console.
										readLine("Digite o novo tipo da bebida: ");
							
							umaBebida.setTipo(novoTipo);

							try
							{	bebidaDAO.altera(umaBebida);

								System.out.println('\n' + 
									"Alteração de tipo efetuada com sucesso!");
							}
							catch(BebidaNaoEncontradaException e)
							{	System.out.println('\n' + e.getMessage());
							}
							try {
								throw new EstadoDeObjetoObsoletoException();
							} catch (EstadoDeObjetoObsoletoException e1) {
								System.out.println('\n' + "A operação não foi realizada porque os dados já foram modificados por outro usuário");
							}	
							break;
					
						case 2:
							String novoNome = Console.
									readLine("Digite o novo Nome: ");
							
							umaBebida.setNome(novoNome);

							try
							{	bebidaDAO.altera(umaBebida);

								System.out.println('\n' + 
									"Alteração de nome efetuada com sucesso!");						
							}
							catch(BebidaNaoEncontradaException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
							
						case 3:
							double novoPreco = Console.
									readDouble("Digite o novo Preço: ");
							
							umaBebida.setPreco(novoPreco);

							try
							{	bebidaDAO.altera(umaBebida);

								System.out.println('\n' + 
									"Alteração de preço com sucesso!");						
							}
							catch(BebidaNaoEncontradaException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
							
						case 4:
							String novaDataFabricacao = Console.
									readLine("Digite a nova Data de Fabricação: ");
							
							umaBebida.setDataFabricacao(Util.strToDate(novaDataFabricacao));

							try
							{	bebidaDAO.altera(umaBebida);

								System.out.println('\n' + 
									"Alteração de Data de Fabricação efetuada " +
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
									"Alteração de lance mínimo efetuada " +
									"com sucesso!");						
							}
							catch(BebidaNaoEncontradaException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;

						default:
							System.out.println('\n' + "Opção inválida!");
					}

					break;
				}

				case 3:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número da bebida que você deseja remover: ");
									
					try
					{	umaBebida = bebidaDAO.
										recuperaUmaBebida(resposta);
					}
					catch(BebidaNaoEncontradaException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + umaBebida.getId() + 
						"    Nome = " + umaBebida.getNome());
														
					String resp = Console.readLine('\n' + 
						"Confirma a remoção da bebida?");

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
							"Bebida não removida.");
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
							"  Preço = " + bebida.getPreco() +
							"  Data Fabricação = " + bebida.getDataFabricacaoMasc() +
							"  Data Validade = " + bebida.getDataValidadeMasc());
					}
					
					break;
				}

				case 5:
				{	continua = false;
					break;
				}

				default:
					System.out.println('\n' + "Opção inválida!");
			}
		}		
	}
}
