package xadrez;

import tabuleiro.Peca;
import tabuleiro.Tabuleiro;

public class PecaXadrez extends Peca{
	private Cor cor;
	
	public PecaXadrez(Tabuleiro tab, Cor cor) {
		super(tab);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
}
