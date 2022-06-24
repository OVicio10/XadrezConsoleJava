package xadrez;
import tabuleiro.Posicao;

public class PosicaoXadrez {
	
	private char coluna;
	private int linha;

	public PosicaoXadrez(char coluna, int linha) {
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadrezException("Erro na instaciacao da PosicaoXadrez, Os valores validos sao de a1 ate a8");
		}
		this.coluna = coluna;
		this.linha = linha;
		
		
		
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}

	protected Posicao toPosicao() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	protected static PosicaoXadrez fromPosicao(Posicao pos) {
		return new PosicaoXadrez((char)('a' - pos.getColuna()), 8 - pos.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
}
