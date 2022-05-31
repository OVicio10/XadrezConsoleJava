package xadrez;

import tabuleiro.Tabuleiro;
import tabuleiro.Posicao;
import xadrez.pecas.Peao;
import xadrez.pecas.Rei;

public class PartidaXadrez {
	private Tabuleiro tab;
	
	public PartidaXadrez() {
		tab = new Tabuleiro(8, 8);
		PosicaoInicial();
	}
	
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] mat = new PecaXadrez[tab.getLinhas()][tab.getColunas()];
		for (int i = 0; i < tab.getLinhas(); i++) {
			for (int j = 0; j < tab.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tab.peca(i,j);
			}
		}
		return mat;
	}
	
	private void PosicaoInicial() {
		tab.posicaoPeca(new Peao(tab, Cor.BRANCO), new Posicao(2,1));
		tab.posicaoPeca(new Rei(tab, Cor.PRETO), new Posicao(0,4));
		tab.posicaoPeca(new Rei(tab, Cor.BRANCO), new Posicao(5,2));
	}
}
