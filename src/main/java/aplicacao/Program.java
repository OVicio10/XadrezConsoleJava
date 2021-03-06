package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaXadrez px = new PartidaXadrez();
		List<PecaXadrez> capturada = new ArrayList<>();
		
		while(!px.getXequeMate()) {
			try {
			Tela.limpaTela();
			Tela.imprimePartida(px, capturada);
			
			System.out.println();
			System.out.print("Origem: ");
			PosicaoXadrez origem = Tela.lerPosicaoXadrez(sc);
			
			boolean[][] possiveisMovimentos = px.movimentosPossiveis(origem);
			Tela.limpaTela();
			Tela.imprimeTabuleiro(px.getPecas(), possiveisMovimentos);
			
			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrez destino = Tela.lerPosicaoXadrez(sc);
			
			PecaXadrez pecaCapturada = px.executaMovimento(origem, destino);
			
			if(pecaCapturada != null) {
				capturada.add(pecaCapturada);
			}
			if(px.getPromocao() != null) {
				System.out.print("Entre com a promocao escolhida (B/C/T/Q): ");
				String type = sc.nextLine().toUpperCase();
				while(!type.equals("B") && !type.equals("C") && !type.equals("T") && !type.equals("Q")) {
					System.out.print("Valor invalido! Entre com a promocao escolhida (B/C/T/Q): ");
					type = sc.nextLine().toUpperCase();
				}
				px.recolocaPecaPromovida(type);
			}
			
			}catch(XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		Tela.limpaTela();
		Tela.imprimePartida(px, capturada);
	}
}
