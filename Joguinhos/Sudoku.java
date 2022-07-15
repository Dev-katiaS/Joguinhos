package jogos;

import java.util.Scanner;

public class Sudoku {

	public static int vetorDeSudokus[][][] = { { { 1, 2, 3, 4 }, { 4, 3, 2, 1 }, { 3, 4, 1, 2 }, { 2, 1, 4, 3 } },
			{ { 2, 1, 4, 3 }, { 3, 4, 1, 2 }, { 1, 2, 3, 4 }, { 4, 3, 2, 1 } },
			{ { 3, 4, 1, 2 }, { 2, 1, 4, 3 }, { 4, 3, 2, 1 }, { 1, 2, 3, 4 } },
			{ { 4, 3, 1, 2 }, { 1, 2, 4, 3 }, { 3, 4, 2, 1 }, { 2, 1, 3, 4 } } };

	public static void main(String[] args) {
		int matrizDoSudoku[][] = new int[4][4];
		jogarSudoku(matrizDoSudoku);
	}

	public static void sorteiaSudoku(int matriz[][], int vetorDeMatrizes[][][]) {
		int t = (int) Math.random() * vetorDeMatrizes.length;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = vetorDeMatrizes[t][i][j];
			}
		}
	}

	public static void imprimeSudoku(int matriz[][]) {
		System.out.println("|---|---|---|---|");
		for (int i = 0; i < 4; i++) {
			System.out.print("|");
			for (int j = 0; j < 4; j++) {
				if (matriz[i][j] == 0) {
					System.out.print(" |");
				} else
					System.out.print(" " + matriz[i][j] + " |");
			}
			System.out.println("\n|---|---|---|---|");
		}
	}

	public static void setaNivelDoSukoku(int n, int matriz[][]) {
		int i, j, contador = 0;
		switch (n) {
		case 1: // N�vel f�cil
			do {
				i = (int) (Math.random() * 4);
				j = (int) (Math.random() * 4);
				if (matriz[i][j] != 0) {
					matriz[i][j] = 0;
					contador++;
				}
			} while (contador < 4);
			break;
		case 2: // N�vel m�dio
			do {
				i = (int) (Math.random() * 4);
				j = (int) (Math.random() * 4);
				if (matriz[i][j] != 0) {
					matriz[i][j] = 0;
					contador++;
				}
			} while (contador < 8);
			break;
		case 3: // N�vel dif�cil
			do {
				i = (int) (Math.random() * 4);
				j = (int) (Math.random() * 4);
				if (matriz[i][j] != 0) {
					matriz[i][j] = 0;
					contador++;
				}
			} while (contador < 12);
			break;
		default:
			System.out.println("Op��es v�lidas: 1 (f�cil), 2 (m�dio) e 3 (dif�cil)");
		}
	}

	public static boolean verificaSudoku(int matriz[][]) {
		// Verifica linhas
		for (int i = 0; i < 4; i++)
			if ((matriz[i][0] + matriz[i][1] + matriz[i][2] + matriz[i][3]) != 10)
				return false;
		// Verifica colunas
		for (int j = 0; j < 4; j++)
			if ((matriz[0][j] + matriz[1][j] + matriz[2][j] + matriz[3][j]) != 10)
				return false;
		// Verifica submatriz
		if ((matriz[0][0] + matriz[0][1] + matriz[1][0] + matriz[1][1]) != 10)
			return false;
		// Verifica submatriz
		if ((matriz[0][2] + matriz[0][3] + matriz[1][2] + matriz[1][3]) != 10)
			return false;
		// Verifica submatriz
		if ((matriz[2][0] + matriz[2][1] + matriz[3][0] + matriz[3][1]) != 10)
			return false;
		// Verifica submatriz
		if ((matriz[2][2] + matriz[2][3] + matriz[3][2] + matriz[3][3]) != 10)
			return false;
		return true;
	}

	public static void jogarSudoku(int matriz[][]) {
		Scanner entrada = new Scanner(System.in);
		System.out.print("Escolha o n�vel de dificuldade (1 (f�cil), 2 (m�dio) ou 3 (dif�cil)): ");
		int nivel = entrada.nextInt();
		sorteiaSudoku(matriz, vetorDeSudokus);
		setaNivelDoSukoku(nivel, matriz);
		int valor, i, j, jogadas = 0;
		do {
			imprimeSudoku(matriz);
			System.out.print("Digite o valor (1, 2, 3 ou 4) para acrescentar:");
			valor = entrada.nextInt();
			do {
				System.out.print("Informe uma linha (1, 2, 3 ou 4) : ");
				i = entrada.nextInt();
				System.out.print("Informe uma coluna (1, 2, 3 ou 4) : ");
				j = entrada.nextInt();
				i--;
				j--;
			} while (matriz[i][j] != 0);
			matriz[i][j] = valor;
			jogadas++;
		} while (jogadas < nivel * 4);
		imprimeSudoku(matriz);
		if (verificaSudoku(matriz))
			System.out.println("Parab�ns, a solu��o do Sudoku est� correta!");
		else
			System.out.println("A solu��o est� incorreta!");
	}
}
