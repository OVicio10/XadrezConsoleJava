package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

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
	
	public PecaXadrez executaMovimento(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validaPosicaoFonte(origem);
		Peca capturaPeca = fazMovimento(origem, destino);
		return (PecaXadrez)capturaPeca;
	}
	
	private Peca fazMovimento(Posicao origem, Posicao destino) {
		Peca p = tab.removePeca(origem);
		Peca capturaPeca = tab.removePeca(destino);
		tab.posicaoPeca(p, destino);
		return capturaPeca;
	}

	private void validaPosicaoFonte(Posicao pos) {
		if(!tab.existePeca(pos)) {
			
		}
		
	}

	private void colocaNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tab.posicaoPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());

	}
	
	private void PosicaoInicial() {
	
		colocaNovaPeca('c', 2, new Torre(tab, Cor.BRANCO));
		colocaNovaPeca('d', 2, new Torre(tab, Cor.BRANCO));
		colocaNovaPeca('e', 2, new Torre(tab, Cor.BRANCO));
		colocaNovaPeca('e', 1, new Torre(tab, Cor.BRANCO));
		colocaNovaPeca('c', 1, new Torre(tab, Cor.BRANCO));
		colocaNovaPeca('d', 1, new Rei(tab, Cor.BRANCO));
		
		colocaNovaPeca('d', 8, new Rei(tab, Cor.PRETO));
		colocaNovaPeca('c', 7, new Torre(tab, Cor.PRETO));
		colocaNovaPeca('c', 8, new Torre(tab, Cor.PRETO));
		colocaNovaPeca('d', 7, new Torre(tab, Cor.PRETO));
		colocaNovaPeca('e', 7, new Torre(tab, Cor.PRETO));
		colocaNovaPeca('e', 8, new Torre(tab, Cor.PRETO));
		
	}
}
