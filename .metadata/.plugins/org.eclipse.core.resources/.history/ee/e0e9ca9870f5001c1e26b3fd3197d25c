package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca{
	private Cor cor;
	
	public PecaXadrez(Tabuleiro tab, Cor cor) {
		super(tab);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.fromPosicao(posicao);
	}
	
	protected boolean existePecaOponente(Posicao pos) {
		PecaXadrez p = (PecaXadrez)getTab().peca(pos);
		return p != null && p.getCor() != cor; 
	}
}
