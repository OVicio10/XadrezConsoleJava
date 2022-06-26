package tabuleiro;

public abstract class Peca {
	protected Posicao posicao;
	private Tabuleiro tab;
	
	public Peca(Tabuleiro tab) {
		this.tab = tab;
		posicao = null;
	}

	public Tabuleiro getTab() {
		return tab;
	}
	
	public abstract boolean[][] possiveisMovimentos();
	
	public boolean possivelMovimento(Posicao pos) {
		return possiveisMovimentos()[pos.getLinha()][pos.getColuna()];
	}
	
	public boolean existeMovimentoPossivel() {
		boolean [][] mat = possiveisMovimentos();
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
