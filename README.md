#JFreq - Versão 1.0: Contador de Frequencias de Palavras

![screenshot1](https://user-images.githubusercontent.com/34322384/49344721-5a7a6a00-f662-11e8-8bbf-49ee11db4647.png)
![screenshot2](https://user-images.githubusercontent.com/34322384/49344722-5c442d80-f662-11e8-8b5c-ba26b5022215.png)

## Como executar:
##### No modo grafico:
	Clique em jfreq.jar ou execute o comando: java -jar jfreq.jar

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

## Configurações:
	O arquivo "config.ini" contém algumas configurações e pode ser editado durante a execução
	do programa em modo gráfico. As configurações disponíveis são:

	Configuração			Valores possíveis				Descrição
	=================================================================================================================================
	inverter_ordem			true / false					Inverte a ordenação (de 'z' para 'a')
	desenhar_arvore 		true / false					Desenha a arvore na tela
	substituir_acentuacao		true / false					Substitui as acentuações (ex: 'ã' para 'a')
	ignorar_palavras_comuns		true / false					Ignora palavras comuns, como 'a' e 'ou'
	palavras_comuns			lista de palavras (separadas por virgula)	Lista das palavras comuns a serem ignoradas
	minimo_letras			1...N						Minimo de letras para uma palavra ser considerada
	maximo_letras			1...N						Maximo de letras para uma palavra ser considerada
	maximo_exibicoes		1...N						Numero maximo de palavras exibidas na tela
