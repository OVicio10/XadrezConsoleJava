package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Rainha;
import xadrez.pecas.Peao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tab;
	private boolean xeque;
	private boolean xequeMate;
	private PecaXadrez vulnerabilidadeEnPassant;
	private PecaXadrez promocao;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaXadrez() {
		tab = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		PosicaoInicial();
	}

	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean getXeque() {
		return xeque;
	}

	public boolean getXequeMate() {
		return xequeMate;
	}

	public PecaXadrez getVulnerabilidadeEnPassant() {
		return vulnerabilidadeEnPassant;
	}

	public PecaXadrez getPromocao() {
		return promocao;
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tab.getLinhas()][tab.getColunas()];
		for (int i = 0; i < tab.getLinhas(); i++) {
			for (int j = 0; j < tab.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tab.peca(i, j);
			}
		}
		return mat;
	}

	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao pos = posicaoOrigem.toPosicao();
		validaPosicaoOrigem(pos);
		return tab.peca(pos).possiveisMovimentos();
	}

	public PecaXadrez executaMovimento(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validaPosicaoOrigem(origem);
		validaPosicaoDestivo(origem, destino);
		Peca pecaCapturada = fazMovimento(origem, destino);

		if (testeXeque(jogadorAtual)) {
			desfazMovimento(origem, destino, pecaCapturada);
			throw new XadrezException("voce nao se colocar em xeque");
		}

		PecaXadrez pecaMovida = (PecaXadrez)tab.peca(destino);

		// #Movimento especial promocao
		promocao = null;
		if (pecaMovida instanceof Peao) {
			if ((pecaMovida.getCor() == Cor.BRANCO && destino.getLinha() == 0) || (pecaMovida.getCor() == Cor.PRETO && destino.getLinha() == 7)) {
				promocao = (PecaXadrez) tab.peca(destino);
				promocao = recolocaPecaPromovida("Q");
			}
		}

		xeque = (testeXeque(oponente(jogadorAtual))) ? true : false;

		if (testeXequeMate(oponente(jogadorAtual))) {
			xequeMate = true;
		} else {
			proximoTurno();
		}

		// #Movimento especial En Passant
		if (pecaMovida instanceof Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			vulnerabilidadeEnPassant = pecaMovida;
		} else {
			vulnerabilidadeEnPassant = null;
		}

		return (PecaXadrez) pecaCapturada;
	}
	
	public PecaXadrez recolocaPecaPromovida(String type) {
		if (promocao == null) {
			throw new IllegalStateException("Nao existe peca para ser promovida");
		}
		if (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
			return promocao;
		}

		Posicao pos = promocao.getPosicaoXadrez().toPosicao();
		Peca p = tab.removePeca(pos);
		pecasNoTabuleiro.remove(p);

		PecaXadrez novaPeca = novaPeca(type, promocao.getCor());
		tab.posicaoPeca(novaPeca, pos);
		pecasNoTabuleiro.add(novaPeca);

		return novaPeca;
	}

	private PecaXadrez novaPeca(String type, Cor cor) {
		if (type.equals("B")) return new Bispo(tab, cor);
		if (type.equals("C")) return new Cavalo(tab, cor);
		if (type.equals("Q")) return new Rainha(tab, cor);
		return new Torre(tab, cor);
	}

	private Peca fazMovimento(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez) tab.removePeca(origem);
		p.aumentaContadorMovimento();
		Peca pecaCapturada = tab.removePeca(destino);
		tab.posicaoPeca(p, destino);

		if (pecasCapturadas != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);

		}

		// #Movimento especial roque pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tab.removePeca(origemT);
			tab.posicaoPeca(torre, destinoT);
			torre.aumentaContadorMovimento();
		}

		// #Movimento especial roque grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tab.removePeca(origemT);
			tab.posicaoPeca(torre, destinoT);
			torre.aumentaContadorMovimento();
		}

		// #Movimento especial En Passant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
				Posicao posicaoPeao;
				if (p.getCor() == Cor.BRANCO) {
					posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				} else {
					posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				pecaCapturada = tab.removePeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
			}
		}

		return pecaCapturada;
	}

	private void desfazMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez) tab.removePeca(destino);
		p.diminuiContadorMovimento();
		tab.posicaoPeca(p, origem);

		if (pecaCapturada != null) {
			tab.posicaoPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}

		// #Movimento especial roque pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tab.removePeca(origemT);
			tab.posicaoPeca(torre, destinoT);
			torre.aumentaContadorMovimento();
		}

		// #Movimento especial roque grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tab.removePeca(origemT);
			tab.posicaoPeca(torre, destinoT);
			torre.aumentaContadorMovimento();
		}
	}

	private void validaPosicaoOrigem(Posicao pos) {
		if (!tab.existePeca(pos)) {
			throw new XadrezException("Nao existe peca na posicao de origem");
		}
		if (jogadorAtual != ((PecaXadrez) tab.peca(pos)).getCor()) {
			throw new XadrezException("A peca escolhida nao e sua");

		}
		if (!tab.peca(pos).existeMovimentoPossivel()) {
			throw new XadrezException("Nao existe movimentos possiveis para a peca escolhida");
		}

	}
	
	private void validaPosicaoDestivo(Posicao origem, Posicao destino) {
		if (!tab.peca(origem).possivelMovimento(destino)) {
			throw new XadrezException("A peca escolhida nao pode mover para a posicao destino");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;

	}

	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private PecaXadrez rei(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez) p;
			}
		}
		throw new IllegalStateException("Nao existe rei " + cor + " no tabuleiro.");
	}

	private boolean testeXeque(Cor cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosicao();
		List<Peca> pecaOponentes = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecaOponentes) {
			boolean[][] mat = p.possiveisMovimentos();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testeXequeMate(Cor cor) {
		if (!testeXeque(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.possiveisMovimentos();
			for (int i = 0; i < tab.getLinhas(); i++) {
				for (int j = 0; j < tab.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = fazMovimento(origem, destino);
						boolean testeXeque = testeXeque(cor);
						desfazMovimento(origem, destino, pecaCapturada);
						if (!testeXeque) {
							return false;
						}
					}
				}
			}
		}

		return true;
	}

	private void colocaNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tab.posicaoPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);

	}

	private void PosicaoInicial() {

		colocaNovaPeca('a', 1, new Torre(tab, Cor.BRANCO));
		colocaNovaPeca('b', 1, new Cavalo(tab, Cor.BRANCO));
		colocaNovaPeca('c', 1, new Bispo(tab, Cor.BRANCO));
		colocaNovaPeca('d', 1, new Rainha(tab, Cor.BRANCO));
		colocaNovaPeca('e', 1, new Rei(tab, Cor.BRANCO, this));
		colocaNovaPeca('f', 1, new Bispo(tab, Cor.BRANCO));
		colocaNovaPeca('g', 1, new Cavalo(tab, Cor.BRANCO));
		colocaNovaPeca('h', 1, new Torre(tab, Cor.BRANCO));
		colocaNovaPeca('a', 2, new Peao(tab, Cor.BRANCO, this));
		colocaNovaPeca('b', 2, new Peao(tab, Cor.BRANCO, this));
		colocaNovaPeca('c', 2, new Peao(tab, Cor.BRANCO, this));
		colocaNovaPeca('d', 2, new Peao(tab, Cor.BRANCO, this));
		colocaNovaPeca('e', 2, new Peao(tab, Cor.BRANCO, this));
		colocaNovaPeca('f', 2, new Peao(tab, Cor.BRANCO, this));
		colocaNovaPeca('g', 2, new Peao(tab, Cor.BRANCO, this));
		colocaNovaPeca('h', 2, new Peao(tab, Cor.BRANCO, this));

		colocaNovaPeca('a', 8, new Torre(tab, Cor.PRETO));
		colocaNovaPeca('b', 8, new Cavalo(tab, Cor.PRETO));
		colocaNovaPeca('c', 8, new Bispo(tab, Cor.PRETO));
		colocaNovaPeca('d', 8, new Rainha(tab, Cor.PRETO));
		colocaNovaPeca('e', 8, new Rei(tab, Cor.PRETO, this));
		colocaNovaPeca('f', 8, new Bispo(tab, Cor.PRETO));
		colocaNovaPeca('g', 8, new Cavalo(tab, Cor.PRETO));
		colocaNovaPeca('h', 8, new Torre(tab, Cor.PRETO));
		colocaNovaPeca('a', 7, new Peao(tab, Cor.PRETO, this));
		colocaNovaPeca('b', 7, new Peao(tab, Cor.PRETO, this));
		colocaNovaPeca('c', 7, new Peao(tab, Cor.PRETO, this));
		colocaNovaPeca('d', 7, new Peao(tab, Cor.PRETO, this));
		colocaNovaPeca('e', 7, new Peao(tab, Cor.PRETO, this));
		colocaNovaPeca('f', 7, new Peao(tab, Cor.PRETO, this));
		colocaNovaPeca('g', 7, new Peao(tab, Cor.PRETO, this));
		colocaNovaPeca('h', 7, new Peao(tab, Cor.PRETO, this));

	}
}
