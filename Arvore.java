package br.ewefernandes.arvorechar;

public class Arvore {
	No raiz;

	public Arvore() {
		raiz = null;
	}
	
	private void insertLeaf (No no, No raizSubArvore) {
		if (raiz == null) {
			raiz = no;
		} else if (no.dado < raizSubArvore.dado) {
			if (raizSubArvore.esquerda == null) {
				raizSubArvore.esquerda = no;
			} else {
				insertLeaf(no, raizSubArvore.esquerda);
			}
		} else if (no.dado >= raizSubArvore.dado) {
			if (raizSubArvore.direita == null) {
				raizSubArvore.direita = no;
			} else {
				insertLeaf(no, raizSubArvore.direita);
			}
		}
	}
	
	public void insert(char dado) {
		No no = new No();
		no.dado = dado;
		no.esquerda = null;
		no.direita = null;
		insertLeaf(no, raiz);
	}
	
	private No nodeSearch(No raizSubArvore, int valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Arvore Vazia");
		} else if (raizSubArvore.dado > valor) {
			return nodeSearch(raizSubArvore.esquerda, valor);
		} else if (raizSubArvore.dado < valor) {
			return nodeSearch(raizSubArvore.direita, valor);
		} else {
			return raizSubArvore;
		}
	}
	
	private int nodeLevel(No raizSubArvore, char valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Arvore Vazia");
		} else if (raizSubArvore.dado > valor) {
			return 1 + nodeLevel(raizSubArvore.esquerda, valor);
		} else if (raizSubArvore.dado < valor) {
			return 1 + nodeLevel(raizSubArvore.direita, valor);
		} else {
			return 0;
		}
	}
	
	public void search(char valor) throws Exception {
		try {
			No no = nodeSearch(raiz, valor);
			int level = nodeLevel(raiz, valor);
			System.out.println("Valor "+no.dado+" nivel " + level);
		} catch (Exception e) {
			throw new Exception("Valor nao encontrado");
		}
	}
	
	private No removeChild(No raizSubArvore, char valor) throws Exception {

		if (raiz == null) {
			throw new Exception("Arvore vazia");
		} else if (raizSubArvore.dado > valor) {
			raizSubArvore.esquerda = removeChild(raizSubArvore.esquerda, valor);
		} else if (raizSubArvore.dado < valor) {
			raizSubArvore.direita = removeChild(raizSubArvore.direita, valor);
		} else { 
			if (raizSubArvore.esquerda == null && raizSubArvore.direita == null) { 
				raizSubArvore = null;
			} else if (raizSubArvore.esquerda == null) { 
				raizSubArvore = raizSubArvore.direita;
			} else if (raizSubArvore.direita == null) {
				raizSubArvore = raizSubArvore.esquerda;
			} else {
				No no = raizSubArvore.esquerda;
				while (no.direita != null) {
					no = no.direita;
				}
				raizSubArvore.dado = no.dado; 
				no.dado = valor;
				raizSubArvore.esquerda = removeChild(raizSubArvore.esquerda, valor);
			}
		}
		return raizSubArvore;
	}
	
	public void remove(char valor) throws Exception {
		try {
			removeChild(raiz, valor);
		} catch (Exception e) {
			throw new Exception("Valor nao existente");
		}
	}
	
	private void prefix(No raizSubArvore) throws Exception {
		if (raiz == null) {
			throw new Exception("Arvore vazia");
		} else {
			System.out.print(raizSubArvore.dado + " ");
			if (raizSubArvore.esquerda != null) {
				prefix(raizSubArvore.esquerda);
			}
			if (raizSubArvore.direita != null) {
				prefix(raizSubArvore.direita);
			}
		}
	}
	
	private void infix(No raizSubArvore) throws Exception {
		if (raiz == null) {
			throw new Exception("Arvore vazia");
		} else {
			if (raizSubArvore.esquerda != null) {
				infix(raizSubArvore.esquerda);
			}
			
			System.out.print(raizSubArvore.dado + " ");

			if (raizSubArvore.direita != null) {
				infix(raizSubArvore.direita);
			}
		}
	}
	
	private void postfix(No raizSubArvore) throws Exception {
		if (raiz == null) {
			throw new Exception("Arvore vazia");
		} else {
			if (raizSubArvore.esquerda != null) {
				postfix(raizSubArvore.esquerda);
			}
			if (raizSubArvore.direita != null) {
				postfix(raizSubArvore.direita);
			}
			System.out.print(raizSubArvore.dado + " ");
		}
	}
	
	public void prefixSearch() throws Exception {
		prefix(raiz);
	}
	
	public void infixSearch() throws Exception {
		infix(raiz);
	}
	
	public void postfixSearch() throws Exception {
		postfix(raiz);
	}


}
