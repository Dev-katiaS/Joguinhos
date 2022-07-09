package jogos;

import java.util.Scanner;

public class CampoMinado {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		int tamanho, numeroDeBombas, posicao, contador = 0, pontos = 0;
		boolean errou = false;
		do {
			System.out.print("Digite o tamanho do caminho: ");
			tamanho = entrada.nextInt();
		} while (tamanho <= 1);
		do {
			System.out.print("Digite o n�mero de bombas: ");
			numeroDeBombas = entrada.nextInt();
		} while (numeroDeBombas <= 0 || numeroDeBombas >= tamanho);
		int caminho[] = new int[tamanho];
		for (int i = 0; i < caminho.length; i++) {
			caminho[i] = 0;
		}
		do {
			posicao = (int) (Math.random() * (tamanho));
			if (caminho[posicao] == 0) {
				caminho[posicao] = -1;
				contador = contador + 1;
			}
		} while (contador < numeroDeBombas);
		do { // La�o principal
			for (int i = 0; i < caminho.length; i++) {
				if (caminho[i] == 0 || caminho[i] == -1) {
					System.out.print(" _ ");
				} else {
					System.out.print(" x ");
				}
			}
			System.out.println();
			for (int i = 0; i < caminho.length; i++) {
				System.out.print("[" + i + "]");
			}
			do {
				System.out.print("\nInforme uma posi��o: ");
				posicao = entrada.nextInt();
			} while (caminho[posicao] == 1);
			if (caminho[posicao] == -1) {
				errou = true;
			} else {
				caminho[posicao] = 1;
				pontos = pontos + 1;
				if (posicao == 0) {
					if (caminho[posicao + 1] == -1) {
						System.out.println("CUIDADO: bomba pr�xima!");
					}
				} else if (posicao == (tamanho - 1)) {
					if (caminho[tamanho - 2] == -1) {
						System.out.println("CUIDADO: bomba pr�xima!");
					}
				} else if (caminho[posicao - 1] == -1 || caminho[posicao + 1] == -1) {
					System.out.println("CUIDADO: bomba pr�xima!");
				}
			}
		} while (!errou && pontos < (tamanho - numeroDeBombas));
		if (errou) { // Perdeu
			for (int i = 0; i < caminho.length; i++) {
				if (caminho[i] == 0) {
					System.out.print(" _ ");
				} else if (caminho[i] == 1) {
					System.out.print(" x ");
				} else {
					System.out.print(" b ");
				}
			}
			System.out.println();
			for (int i = 0; i < caminho.length; i++) {
				System.out.print("[" + i + "]");
			}
			System.out.println("\nGame Over!");
			System.out.println("Pontua��o: " + pontos + "/" + (tamanho - numeroDeBombas));
		} else { // Ganhou
			for (int i = 0; i < caminho.length; i++) {
				if (caminho[i] == 1) {
					System.out.print(" x ");
				} else if (caminho[i] == -1) {
					System.out.print(" b ");
				}
			}
			System.out.println();
			for (int i = 0; i < caminho.length; i++) {
				System.out.print("[" + i + "]");
			}
			System.out.println("\nParab�ns, voc� ganhou o jogo!");
			System.out.println("Pontua��o: " + pontos + "/" + (tamanho - numeroDeBombas));
		}
	}
}
