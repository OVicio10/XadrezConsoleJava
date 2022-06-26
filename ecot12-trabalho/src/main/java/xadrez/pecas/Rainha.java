package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

	public Rainha(Tabuleiro tab, Cor cor) {
		super(tab, cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

		Posicao p = new Posicao(0, 0);

		// para cima
		p.setValor(posicao.getLinha() - 1, posicao.getColuna());
		while (getTab().posicaoExiste(p) && !getTab().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}

		if (getTab().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// para baixo
		p.setValor(posicao.getLinha() + 1, posicao.getColuna());
		while (getTab().posicaoExiste(p) && !getTab().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}

		if (getTab().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// para esquerda
		p.setValor(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTab().posicaoExiste(p) && !getTab().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}

		if (getTab().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// para direita
		p.setValor(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTab().posicaoExiste(p) && !getTab().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}

		if (getTab().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// NO
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTab().posicaoExiste(p) && !getTab().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTab().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// NE
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTab().posicaoExiste(p) && !getTab().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTab().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// SE
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTab().posicaoExiste(p) && !getTab().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTab().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// SO
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTab().posicaoExiste(p) && !getTab().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTab().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}
