package aplicacao;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaXadrez px = new PartidaXadrez();
		
		while(true) {
			Tela.imprimeTabuleiro(px.getPecas());
			
			System.out.println();
			System.out.print("Origem: ");
			PosicaoXadrez origem = Tela.lerPosicaoXadrez(sc);
			
			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrez destino = Tela.lerPosicaoXadrez(sc);
			
			PecaXadrez capturaPeca = px.executaMovimento(origem, destino);
		}
		
	}
}
