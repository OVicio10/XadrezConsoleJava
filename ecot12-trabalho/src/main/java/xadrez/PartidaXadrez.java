package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tab;
	
	public PartidaXadrez() {
		tab = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		PosicaoInicial();
	}
	
	public int getTurno(){
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
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
	
	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem){
		Posicao pos = posicaoOrigem.toPosicao();
		validaPosicaoOrigem(pos);
		return tab.peca(pos).possiveisMovimentos();
	}
	
	public PecaXadrez executaMovimento(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validaPosicaoOrigem(origem);
		validaPosicaoDestivo(origem, destino);
		Peca capturaPeca = fazMovimento(origem, destino);
		proximoTurno();
		return (PecaXadrez)capturaPeca;
	}
	
	private void proximoTurno() {
		turno ++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
		
	}

	private void validaPosicaoDestivo(Posicao origem, Posicao destino) {
		if(!tab.peca(origem).possivelMovimento(destino)) {
			throw new XadrezException("A peca escolhida nao pode mover para a posicao destino");
		}
		
	}

	private Peca fazMovimento(Posicao origem, Posicao destino) {
		Peca p = tab.removePeca(origem);
		Peca capturaPeca = tab.removePeca(destino);
		tab.posicaoPeca(p, destino);
		return capturaPeca;
	}

	private void validaPosicaoOrigem(Posicao pos) {
		if(!tab.existePeca(pos)) {
			throw new XadrezException("Nao existe peca na posicao de origem");
		}
		if(jogadorAtual != ((PecaXadrez)tab.peca(pos)).getCor()) {
			throw new XadrezException("A peca escolhida nao e sua");
			
		}
		if(!tab.peca(pos).existeMovimentoPossivel()) {
			throw new XadrezException("Nao existe movimentos possiveis para a peca escolhida");
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
