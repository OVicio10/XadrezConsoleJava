package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabuleiro tab, Cor cor) {
		super(tab, cor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "T";
	}
}
