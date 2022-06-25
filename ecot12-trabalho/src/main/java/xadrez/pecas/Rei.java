package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	public Rei(Tabuleiro tab, Cor cor) {
		super(tab, cor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean podeMover(Posicao pos) {
		PecaXadrez p = (PecaXadrez)getTab().peca(pos);
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] possiveisMovimentos(){
		boolean [][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];
		
		Posicao p = new Posicao(0, 0);

		// para cima
		p.setValor(p.getLinha() - 1, p.getColuna());
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// para baixo
		p.setValor(posicao.getLinha() + 1, posicao.getColuna());
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// esquerda
		p.setValor(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// direita
		p.setValor(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// NO
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// NE
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// SO
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// SE
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}
