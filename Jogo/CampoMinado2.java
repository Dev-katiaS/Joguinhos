package jogos;

import java.util.Scanner;

public class CampoMinado2 {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		int linhas, colunas, numeroDeBombas;
		do {
			System.out.print("Digite o n�mero de linhas do campo: ");
			linhas = entrada.nextInt();
			System.out.print("Digite o n�mero de colunas do campo: ");
			colunas = entrada.nextInt();
		} while (linhas < 2 || colunas < 2);
		do {
			System.out.print("Digite o n�mero de bombas: ");
			numeroDeBombas = entrada.nextInt();
		} while (numeroDeBombas <= 0 || numeroDeBombas >= (linhas * colunas));
		int matriz[][] = new int[linhas][colunas];
		gerarCampo(numeroDeBombas, matriz);
		jogarJogo(numeroDeBombas, matriz);
	}

	public static void gerarCampo(int quantidadeDeBombas, int campo[][]) {
		int i, j, contador = 0;
		do {
			i = (int) (Math.random() * (campo.length));
			j = (int) (Math.random() * (campo[0].length));
			if (campo[i][j] == 0) {
				campo[i][j] = -1;
				contador++;
			}
		} while (contador < quantidadeDeBombas);
	}

	public static void imprimirCampo(boolean mostraBombas, int campo[][]) {
		System.out.print(" ");
		for (int j = 0; j < campo[0].length; j++) {
			System.out.print("[" + j + "]");
		}
		System.out.println();
		for (int i = 0; i < campo.length; i++) {
			System.out.print("[" + i + "]");
			for (int j = 0; j < campo[0].length; j++) {
				if (campo[i][j] == 1) {
					System.out.print(" x ");
				} else if ((campo[i][j] == -1) && mostraBombas == true) {
					System.out.print(" B ");
				} else {
					System.out.print(" _ ");
				}
			}
			System.out.println();
		}
	}

	// O c�digo do m�todo a seguir considera que o campo tem no m�nimo 4 c�lulas
	public static boolean temBombaProxima(int i, int j, int campo[][]) {
		// (i,j) n�o pertence a "borda" da matriz/campo
		if ((i != 0) && (i != (campo.length - 1)) && (j != 0) && (j != campo[0].length - 1)) {
			if ((campo[i - 1][j - 1] == -1) || (campo[i - 1][j] == -1) || (campo[i - 1][j + 1] == -1)
					|| (campo[i][j - 1] == -1) || (campo[i][j + 1] == -1) || (campo[i + 1][j - 1] == -1)
					|| (campo[i + 1][j] == -1) || (campo[i + 1][j + 1] == -1)) {
				return true;
			}
		}
		if (i == 0) { // Primeira linha
			if (j == 0) { // Canto superior esquerdo
				if (campo[i + 1][j] == -1 || campo[i + 1][j + 1] == -1 || campo[i][j + 1] == -1) {
					return true;
				}
			} else if (j == (campo[0].length - 1)) { // Canto superior direito
				if (campo[i][j - 1] == -1 || campo[i + 1][j - 1] == -1 || campo[i + 1][j] == -1) {
					return true;
				}
			} else { // Entre os cantos superiores esquerdo e direito
				if (campo[i][j - 1] == -1 || campo[i][j + 1] == -1 || campo[i + 1][j - 1] == -1 || campo[i + 1][j] == -1
						|| campo[i + 1][j + 1] == -1) {
					return true;
				}
			}
		}
		if (i == (campo.length - 1)) { // Ultima linha
			if (j == 0) { // Canto inferior esquerdo
				if (campo[i - 1][j] == -1 || campo[i - 1][j + 1] == -1 || campo[i][j + 1] == -1) {
					return true;
				}
			} else if (j == (campo[0].length - 1)) { // Canto inferior direito
				if (campo[i][j - 1] == -1 || campo[i - 1][j - 1] == -1 || campo[i - 1][j] == -1) {
					return true;
				}
			} else { // Entre os cantos inferiores esquerdo e direito
				if (campo[i][j - 1] == -1 || campo[i][j + 1] == -1 || campo[i - 1][j - 1] == -1 || campo[i - 1][j] == -1
						|| campo[i - 1][j + 1] == -1) {
					return true;
				}
			}
		}
		// Entre o canto superior esquerdo e o canto inferior esquerdo
		if ((j == 0) && (i != 0) && (i != (campo.length - 1))) {
			if ((campo[i - 1][j] == -1) || (campo[i + 1][j] == -1) || (campo[i - 1][j + 1] == -1)
					|| (campo[i][j + 1] == -1) || (campo[i + 1][j + 1] == -1)) {
				return true;
			}
		}
		// Entre o canto superior direito e o canto inferior direito
		if ((j == campo[0].length - 1) && (i != 0) && (i != (campo.length - 1))) {
			if ((campo[i - 1][j] == -1) || (campo[i + 1][j] == -1) || (campo[i - 1][j - 1] == -1)
					|| (campo[i][j - 1] == -1) || (campo[i + 1][j - 1] == -1)) {
				return true;
			}
		}
		return false;
	}

	public static void jogarJogo(int quantidadeDeBombas, int campo[][]) {
		Scanner entrada = new Scanner(System.in);
		int i, j, pontos = 0, numeroDeCelulas = campo.length * campo[0].length;
		boolean errou = false;
		do {
			imprimirCampo(false, campo);
			do {
				System.out.print("Informe uma linha: ");
				i = entrada.nextInt();
				System.out.print("Informe uma coluna: ");
				j = entrada.nextInt();
			} while (campo[i][j] == 1);
			if (campo[i][j] == -1) {
				errou = true;
			} else {
				campo[i][j] = 1;
				pontos = pontos + 1;
				if (temBombaProxima(i, j, campo)) {
					System.out.println("CUIDADO! bomba pr�xima!");
				}
			}
		} while (!errou && (pontos < numeroDeCelulas - quantidadeDeBombas));
		if (errou) { // Perdeu
			imprimirCampo(true, campo);
			System.out.println("Game Over!");
			System.out.println("Pontua��o: " + pontos + "/" + (numeroDeCelulas - quantidadeDeBombas));
		} else { // Ganhou
			imprimirCampo(true, campo);
			System.out.println("Parab�ns, voc� ganhou o jogo!");
			System.out.println("Pontua��o: " + pontos + "/" + (numeroDeCelulas - quantidadeDeBombas));
		}
	}

}
