package xadrez.pecas;

import tabuleiro.Tabuleiro;
import tabuleiro.Posicao;
import xadrez.PartidaXadrez;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	private PartidaXadrez px;
	
	public Peao(Tabuleiro tab, Cor cor, PartidaXadrez partidaXadrez) {
		super(tab, cor);
		this.px = partidaXadrez;
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

		Posicao p = new Posicao(0, 0);

		if (getCor() == Cor.BRANCO) {
			p.setValor(posicao.getLinha() - 1, posicao.getColuna());
			if (getTab().posicaoExiste(p) && !getTab().existePeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTab().posicaoExiste(p) && !getTab().existePeca(p) && getTab().posicaoExiste(p2) && !getTab().existePeca(p2) && getContadorMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTab().posicaoExiste(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTab().posicaoExiste(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			// #Movimento especial En Passant branco
			if (posicao.getLinha() == 3) {
				Posicao esq = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
			if (getTab().posicaoExiste(esq) && existePecaOponente(esq) && getTab().peca(esq) == px.getVulnerabilidadeEnPassant()) {
				mat[esq.getLinha() - 1][esq.getColuna()] = true;
			}
			Posicao dir = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
			if (getTab().posicaoExiste(dir) && existePecaOponente(dir) && getTab().peca(dir) == px.getVulnerabilidadeEnPassant()) {
				mat[dir.getLinha() - 1][dir.getColuna()] = true;
				}
			}
		} else {
			p.setValor(posicao.getLinha() + 1, posicao.getColuna());
			if (getTab().posicaoExiste(p) && !getTab().existePeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTab().posicaoExiste(p) && !getTab().existePeca(p) && getTab().posicaoExiste(p2) && !getTab().existePeca(p2) && getContadorMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTab().posicaoExiste(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTab().posicaoExiste(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			// #Movimento especial En Passant branco
			if (posicao.getLinha() == 3) {
				Posicao esq = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTab().posicaoExiste(esq) && existePecaOponente(esq) && getTab().peca(esq) == px.getVulnerabilidadeEnPassant()) {
				mat[esq.getLinha() - 1][esq.getColuna()] = true;
				}
			Posicao dir = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTab().posicaoExiste(dir) && existePecaOponente(dir) && getTab().peca(dir) == px.getVulnerabilidadeEnPassant()) {
				mat[dir.getLinha() - 1][dir.getColuna()] = true;
				}
			}
		}
		return mat;
	}
}
