package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

	public Cavalo(Tabuleiro tab, Cor cor) {
		super(tab, cor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "C";
	}
	
	private boolean canMove(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTab().peca(posicao);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

		Posicao p = new Posicao(0, 0);

		p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTab().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValor(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getTab().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValor(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getTab().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getTab().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTab().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValor(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTab().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValor(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTab().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTab().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}
}
