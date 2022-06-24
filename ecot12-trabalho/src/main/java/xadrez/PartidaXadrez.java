package xadrez;

import tabuleiro.Tabuleiro;
import tabuleiro.Posicao;
import xadrez.pecas.Torre;
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
	
	private void colocaNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tab.posicaoPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());

	}
	
	private void PosicaoInicial() {
	
		colocaNovaPeca('b', 6, new Torre(tab, Cor.BRANCO));
		colocaNovaPeca('e', 8, new Rei(tab, Cor.PRETO));
		colocaNovaPeca('e', 1, new Rei(tab, Cor.BRANCO));
	}
}
