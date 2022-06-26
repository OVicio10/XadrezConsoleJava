package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	private PartidaXadrez partidaXadrez;

	public Rei(Tabuleiro tab, Cor cor, PartidaXadrez px) {
		super(tab, cor);
		this.partidaXadrez = px;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean podeMover(Posicao pos) {
		PecaXadrez p = (PecaXadrez) getTab().peca(pos);
		return p == null || p.getCor() != getCor();
	}

	private boolean TesteRoque(Posicao pos) {
		PecaXadrez p = (PecaXadrez) getTab().peca(pos);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContadorMovimento() == 0;
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

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

		// #Movimento especial Roque
		if (getContadorMovimento() == 0 && !partidaXadrez.getXeque()) {
			// #Movimento especial Roque pequeno
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if (TesteRoque(posT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if (getTab().peca(p1) == null && getTab().peca(p2) == null) {
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
			// #Movimento especial Roque grande
			Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if (TesteRoque(posT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if (getTab().peca(p1) == null && getTab().peca(p2) == null && getTab().peca(p3) == null) {
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}

		return mat;
	}
}
