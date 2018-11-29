#JFreq - Versão 1.0

## Modo de usar:
	java -jar jfreq.jar

##### Ou direto da linha de comando:
	java -jar jfreq.jar [algoritmo] [arquivo.txt]
	
## [Algoritmo]:
	- hlista	Hash com lista encadeada	
	- haberto	Hash aberto
	- arvore	Arvore binária de pesquisa (BST)
	- pseq		Pesquisa sequencial
	- pbinaria	Pesquisa binária

## [Arquivo]:
	Qualquer arquivo de texto, não-binário.

## Exemplo:
	java -jar jfreq.jar pseq palavras.txt

Configurações:
	O arquivo "config.ini" contém algumas configurações e pode ser editado durante a execução
	do programa em modo gráfico. As configurações disponíveis são:

	Configuração			Valores possíveis				Descrição
	=================================================================================================================================
	inverter_ordem			true / false					Inverte a ordenação (de 'z' para 'a')
	desligar_recursividade		true / false					Desliga a recursividade quando possível
	ignorar_palavras_comuns		true / false					Ignora palavras comuns, como 'a' e 'ou'
	palavras_comuns			lista de palavras (separadas por virgula)	Lista das palavras comuns a serem ignoradas
	minimo_letras			1...N						Minimo de letras para uma palavra ser considerada
	maximo_letras			1...N						Maximo de letras para uma palavra ser considerada
	maximo_exibicoes		1...N						Numero maximo de palavras exibidas na tela
	substituir_acentuacao		true / false					Substitui as acentuações (ex: 'ã' para 'a')
