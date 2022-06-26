package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca{
	private Cor cor;
	private int contadorMovimento;
	
	public PecaXadrez(Tabuleiro tab, Cor cor) {
		super(tab);
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}
	
	public int getContadorMovimento() {
		return contadorMovimento;
	}
	
	protected void aumentaContadorMovimento() {
		contadorMovimento++;
	}

	protected void diminuiContadorMovimento() {
		contadorMovimento--;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.fromPosicao(posicao);
	}
	
	protected boolean existePecaOponente(Posicao pos) {
		PecaXadrez p = (PecaXadrez)getTab().peca(pos);
		return p != null && p.getCor() != cor; 
	}
}
