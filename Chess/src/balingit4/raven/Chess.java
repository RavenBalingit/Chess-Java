package balingit4.raven;

import java.util.Arrays;
import java.util.Scanner;

public class Chess {
	public static void main(String[] args) {

		Scanner inputKeyboard = new Scanner(System.in);

		// Define the chess board and backdoor arrays
		char[][] matrixBoard = new char[8][8];
		char[][] backdoor = new char[8][8];

		// Initialize game-related variables
		boolean game = false;
		int turn = 0;

		// Define ANSI escape codes for text colors
		String resetColor = "\u001B[0m";
		String redColor = "\u001B[31m";
		String highlightText = "\u001B[7m";
		String lightBrownColor = "\u001B[33;1m";
		String limeColor = "\u001B[32m";

		// Define chess piece characters
		char torreB = 'T';
		char torreN = 'T';
		char caballoB = 'C';
		char caballoN = 'C';
		char alfilB = 'A';
		char alfilN = 'A';
		char kingB = 'K';
		char kingN = 'K';
		char queenB = 'Q';
		char queenN = 'Q';
		char peonB = 'P';
		char peonN = 'P';

		// Initialize the chess board with empty spaces
		for (int i = 0; i < 8; i++) {
			Arrays.fill(matrixBoard[i], '-');
		}

		// Initialize the backdoor matrix with player pieces
		for (int i = 0; i < 8; i++) {
			if (i < 2) {
				Arrays.fill(backdoor[i], '1');
			} else if (i > 5) {
				Arrays.fill(backdoor[i], '0');
			} else {
				Arrays.fill(backdoor[i], '-');
			}
		}

		// Set up the initial positions of chess pieces on the board
		// White pieces
		matrixBoard[0][0] = torreN;
		matrixBoard[0][7] = torreN;
		matrixBoard[0][1] = caballoN;
		matrixBoard[0][6] = caballoN;
		matrixBoard[0][2] = alfilN;
		matrixBoard[0][5] = alfilN;
		matrixBoard[0][4] = kingN;
		matrixBoard[0][3] = queenN;

		// Pawns
		for (int i = 0; i < 8; i++) {
			matrixBoard[1][i] = peonN;
			matrixBoard[6][i] = peonB;
		}

		// Black pieces
		matrixBoard[7][0] = torreB;
		matrixBoard[7][7] = torreB;
		matrixBoard[7][1] = caballoB;
		matrixBoard[7][6] = caballoB;
		matrixBoard[7][2] = alfilB;
		matrixBoard[7][5] = alfilB;
		matrixBoard[7][4] = kingB;
		matrixBoard[7][3] = queenB;

		// Display the initial chess board
		System.out.print(highlightText + lightBrownColor + "   A  B  C  D  E  F  G  H   " + resetColor);
		System.out.println();
		for (int i = 0; i < 8; i++) {
			System.out.print(highlightText + lightBrownColor + ((i - 8) * (-1)) + " " + resetColor);
			for (int j = 0; j < 8; j++) {
				if (backdoor[i][j] == '0') {
					System.out.print("(" + matrixBoard[i][j] + ")");
				} else if (backdoor[i][j] == '1') {
					System.out.print(redColor + "[" + matrixBoard[i][j] + "]" + resetColor);
				} else {
					System.out.print(" " + matrixBoard[i][j] + " ");
				}
			}
			System.out.print(highlightText + lightBrownColor + ((i - 8) * (-1)) + " " + resetColor);
			System.out.println();
		}

		System.out.print(highlightText + lightBrownColor + "   A  B  C  D  E  F  G  H   " + resetColor);
		System.out.println();
		System.out.println();

		do {
			// Initialize variables for player's move (which piece)
			int row = -1;
			int column = -1;
			char charTurn = '*';
			char charAux = '-';
			boolean firstInput = false;
			boolean columnValid;
			char columnChar = '*';

			// Determine player's turn based on the value of 'turn'
			if (turn % 2 == 0) {
				charTurn = '0';
			} else {
				charTurn = '1';
			}

			// Loop for where you want to move
			int rowPicked = -1;
			int columnPicked = -1;
			char columnPickedChar = '*';
			boolean secondInput = false;
			boolean secondColumnValid;

			while (secondInput == false) {

				// Prompt for row and column selection for the first player
				System.out.println(limeColor + "PICKING THE PIECE" + resetColor);
				while (firstInput == false) {
					// Get the row input
					row = -1;
					column = -1;
					columnChar = '*';
					while (row < 1 || row > 8) {
						System.out.print("Row (1-8): ");
						row = inputKeyboard.nextInt();
						if (row < 1 || row > 8) {
							System.out.println(redColor + "Invalid number, please try again." + resetColor + "\n");
						}
					}
					System.out.println();
					// Map the row input to the corresponding index in the matrix

					switch (row) {
					case 1:
						row = 7;
						break;
					case 2:
						row = 6;
						break;
					case 3:
						row = 5;
						break;
					case 4:
						row = 4;
						break;
					case 5:
						row = 3;
						break;
					case 6:
						row = 2;
						break;
					case 7:
						row = 1;
						break;
					case 8:
						row = 0;
						break;
					}
					// Validate and get the column input
					columnValid = false;
					while (columnValid == false) {
						System.out.print("Column (A-H): ");
						columnChar = inputKeyboard.next().charAt(0);

						// Map the column input to the corresponding index in the matrix
						switch (columnChar) {
						case 'A':
							column = 0;
							columnValid = true;
							break;
						case 'B':
							column = 1;
							columnValid = true;
							break;
						case 'C':
							column = 2;
							columnValid = true;
							break;
						case 'D':
							column = 3;
							columnValid = true;
							break;
						case 'E':
							column = 4;
							columnValid = true;
							break;
						case 'F':
							column = 5;
							columnValid = true;
							break;
						case 'G':
							column = 6;
							columnValid = true;
							break;
						case 'H':
							column = 7;
							columnValid = true;
							break;
						}

						if (columnValid == false) {
							System.out.println(redColor + "Invalid column, please try again." + resetColor + "\n");
						}

					}
					System.out.println();
					// Check if the selected piece belongs to the current player
					if (charTurn != backdoor[row][column]) {
						System.out.println(
								redColor + "Picked another's player's piece, please try again." + resetColor + "\n");
					} else {
						firstInput = true;
					}

				}
				// Display the piece selected by the player (it changes color depending
				// on who's turn it is)

				if (turn % 2 == 0) {
					System.out.println("Piece selected: " + "(" + matrixBoard[row][column] + ")");
					System.out.println();
				} else {
					System.out
							.println("Piece selected: " + redColor + "[" + matrixBoard[row][column] + "]" + resetColor);
					System.out.println();
				}

				// Set variables for the second move (where)
				rowPicked = -1;
				columnPicked = -1;
				columnPickedChar = '*';

				// Prompt for row and column selection for the player
				System.out.println(limeColor + "SELECTING WHERE TO PUT IT" + resetColor);

				// Get the row input
				while (rowPicked < 1 || rowPicked > 8) {
					System.out.print("Row: (1-8): ");
					rowPicked = inputKeyboard.nextInt();
					if (rowPicked < 1 || rowPicked > 8) {
						System.out.println(redColor + "Invalid number, please try again." + resetColor + "\n");
					}
				}
				System.out.println();

				// Map the row input to the corresponding index in the matrix
				switch (rowPicked) {
				case 1:
					rowPicked = 7;
					break;
				case 2:
					rowPicked = 6;
					break;
				case 3:
					rowPicked = 5;
					break;
				case 4:
					rowPicked = 4;
					break;
				case 5:
					rowPicked = 3;
					break;
				case 6:
					rowPicked = 2;
					break;
				case 7:
					rowPicked = 1;
					break;
				case 8:
					rowPicked = 0;
					break;
				}

				// Validate and get the column input
				secondColumnValid = false;
				while (secondColumnValid == false) {
					System.out.print("Column (A-H): ");
					columnPickedChar = inputKeyboard.next().charAt(0);

					// Map the column input to the corresponding index in the matrix
					switch (columnPickedChar) {
					case 'A':
						columnPicked = 0;
						secondColumnValid = true;
						break;
					case 'B':
						columnPicked = 1;
						secondColumnValid = true;
						break;
					case 'C':
						columnPicked = 2;
						secondColumnValid = true;
						break;
					case 'D':
						columnPicked = 3;
						secondColumnValid = true;
						break;
					case 'E':
						columnPicked = 4;
						secondColumnValid = true;
						break;
					case 'F':
						columnPicked = 5;
						secondColumnValid = true;
						break;
					case 'G':
						columnPicked = 6;
						secondColumnValid = true;
						break;
					case 'H':
						columnPicked = 7;
						secondColumnValid = true;
						break;
					}

					if (secondColumnValid == false) {
						System.out.println(redColor + "Invalid column, please try again.\n" + resetColor);
					}

				}
				System.out.println();

				// Check if the selected piece belongs to the opponent
				if (charTurn == backdoor[rowPicked][columnPicked]) {
					System.out.println(redColor + "Picked where your own piece is, please try again.\n" + resetColor);
				} else {
					secondInput = true;
				}
			}

			// Check if the piece in the selected position is a '0' (pawn for player 1)
			if (backdoor[row][column] == '0') {
				// Check if the selected piece is a pawn
				if (matrixBoard[row][column] == 'P') {
					// Check the validity of the move for the pawn
					if ((backdoor[rowPicked][columnPicked] != row - 1 && backdoor[rowPicked][columnPicked] != '1')
							|| (rowPicked == row - 1 && columnPicked == column - 1
									&& backdoor[rowPicked][columnPicked] == '1')
							|| (rowPicked == row - 1 && columnPicked == column + 1
									&& backdoor[rowPicked][columnPicked] == '1')) {

						// Check if the pawn is at the starting position (row 6)
						if (row == 6) {
							// Check if the move is valid for the starting position
							if (rowPicked == row - 1 || rowPicked == row - 2) {
								// Update the board after a valid move
								matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
								backdoor[rowPicked][columnPicked] = backdoor[row][column];
								matrixBoard[row][column] = charAux;
								backdoor[row][column] = charAux;
								turn++;
							} else {
								System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
							}
						} else {
							// Check if the move is valid for non-starting positions
							if ((matrixBoard[rowPicked][columnPicked] == matrixBoard[row - 1][column])
									|| (rowPicked == row - 1 && columnPicked == column - 1
											&& backdoor[rowPicked][columnPicked] == '1')
									|| (rowPicked == row - 1 && columnPicked == column + 1
											&& backdoor[rowPicked][columnPicked] == '1')) {
								if (!(rowPicked > row+1)) {
									// Update the board after a valid move
								matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
								backdoor[rowPicked][columnPicked] = backdoor[row][column];
								matrixBoard[row][column] = charAux;
								backdoor[row][column] = charAux;
								turn++;
								}

								
							} else {
								System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
							}
						}
					} else {
						System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
					}
				}
				// Check if the selected piece is a rook
				else if (matrixBoard[row][column] == 'T') {
					// Check the validity of the move for the rook
					boolean validMove = false;

					// Check for vertical and horizontal moves
					for (int i = 0; i < 8; i++) {
						if ((i != row && matrixBoard[i][column] != '-')
								|| (i != column && matrixBoard[row][i] != '-')) {
							// If there's any piece in the same row or column, set validMove to true
							validMove = true;
							break;
						}
					}

					// Check for horizontal movement
					if (rowPicked == row && Math.abs(columnPicked - column) > 1) {
						int start = Math.min(column, columnPicked) + 1;
						int end = Math.max(column, columnPicked);

						// Check if there are any obstacles in the horizontal path
						for (int i = start; i < end; i++) {
							if (matrixBoard[row][i] != '-' || backdoor[row][i] != '0') {
								validMove = false;
								break;
							}
						}
					}

					// Check for vertical movement
					else if (columnPicked == column && Math.abs(rowPicked - row) > 1) {
						int start = Math.min(row, rowPicked) + 1;
						int end = Math.max(row, rowPicked);

						// Check if there are any obstacles in the vertical path
						for (int i = start; i < end; i++) {
							if (matrixBoard[i][column] != '-' || backdoor[i][column] != '0') {
								validMove = false;
								break;
							}
						}
					} else {
						// Invalid move for rook (neither horizontal nor vertical)
						validMove = false;
					}

					// Check if the move is valid and the destination cell has a different piece
					if (validMove && backdoor[rowPicked][columnPicked] != backdoor[row][column]) {
						// Update the board after a valid move
						matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
						backdoor[rowPicked][columnPicked] = backdoor[row][column];
						matrixBoard[row][column] = charAux;
						backdoor[row][column] = charAux;
						turn++;
					} else {
						// Print an error message for an invalid move
						System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
					}
				}

				// Check if the selected piece is a bishop
				else if (matrixBoard[row][column] == 'A') {
					// Check the validity of the move for the bishop
					boolean validMove = false;

					// Check if the movement is diagonal and not staying in the same position
					if (Math.abs(rowPicked - row) == Math.abs(columnPicked - column) && rowPicked != row
							&& columnPicked != column) {
						validMove = true;
					}

					if (validMove) {
						// Determine the direction of the diagonal movement
						int rowStep, columnStep;
						if (row < rowPicked) {
							rowStep = 1;
						} else {
							rowStep = -1;
						}

						if (column < columnPicked) {
							columnStep = 1;
						} else {
							columnStep = -1;
						}

						int i = row + rowStep;
						int j = column + columnStep;

						// Check for obstacles along the diagonal path
						while (i != rowPicked && j != columnPicked) {
							if (matrixBoard[i][j] != '-') {
								validMove = false;
								break;
							}
							i += rowStep;
							j += columnStep;
						}
					}

					// Check if the move is valid and the destination cell has a different piece
					if (validMove) {
						if (backdoor[rowPicked][columnPicked] != backdoor[row][column]) {
							// Update the board after a valid move
							matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
							backdoor[rowPicked][columnPicked] = backdoor[row][column];
							matrixBoard[row][column] = charAux;
							backdoor[row][column] = charAux;
							turn++;
						} else {
							// Print an error message for an invalid move
							System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
						}
					} else {
						// Print an error message for an invalid move
						System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
					}
				}

				// Check if the selected piece is a queen
				else if (matrixBoard[row][column] == 'Q') {
				    // Check the validity of the move for the queen
				    boolean validMove = false;

				    // Check for vertical and horizontal moves (rook movement)
				    for (int i = 0; i < 8; i++) {
				        if ((i != row && matrixBoard[i][column] != '-')
				                || (i != column && matrixBoard[row][i] != '-')) {
				            // If there's any piece in the same row or column, set validMove to true
				            validMove = true;
				            break;
				        }
				    }

				    // Check for horizontal movement
				    if (rowPicked == row && Math.abs(columnPicked - column) > 1) {
				        int start = Math.min(column, columnPicked) + 1;
				        int end = Math.max(column, columnPicked);

				        // Check if there are any obstacles in the horizontal path
				        for (int i = start; i < end; i++) {
				            if (matrixBoard[row][i] != '-') {
				                validMove = false;
				                break;
				            }
				        }
				    }

				    // Check for vertical movement
				    else if (columnPicked == column && Math.abs(rowPicked - row) > 1) {
				        int start = Math.min(row, rowPicked) + 1;
				        int end = Math.max(row, rowPicked);

				        // Check if there are any obstacles in the vertical path
				        for (int i = start; i < end; i++) {
				            if (matrixBoard[i][column] != '-') {
				                validMove = false;
				                break;
				            }
				        }
				    }

				    // Check if the movement is diagonal and not staying in the same position
				    // (bishop movement)
				    else if (Math.abs(rowPicked - row) == Math.abs(columnPicked - column) && rowPicked != row
				            && columnPicked != column) {
				        validMove = true;

				        // Determine the direction of the diagonal movement
				        int rowStep, columnStep;
				        if (row < rowPicked) {
				            rowStep = 1;
				        } else {
				            rowStep = -1;
				        }

				        if (column < columnPicked) {
				            columnStep = 1;
				        } else {
				            columnStep = -1;
				        }

				        int i = row + rowStep;
				        int j = column + columnStep;

				        // Check for obstacles along the diagonal path
				        while (i != rowPicked && j != columnPicked) {
				            if (matrixBoard[i][j] != '-') {
				                validMove = false;
				                break;
				            }
				            i += rowStep;
				            j += columnStep;
				        }
				    } else {
				        // Invalid move for queen (neither horizontal nor vertical nor diagonal)
				        validMove = false;
				    }

				    // Check if the move is valid and the destination cell has a different piece
				    if (validMove && backdoor[rowPicked][columnPicked] != backdoor[row][column]) {
				        // Update the board after a valid move
				        matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
				        backdoor[rowPicked][columnPicked] = backdoor[row][column];
				        matrixBoard[row][column] = charAux;
				        backdoor[row][column] = charAux;
				        turn++;
				    } else {
				        // Print an error message for an invalid move
				        System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
				    }
				}

				// Check if the selected piece is a knight
				else if (matrixBoard[row][column] == 'C') {
					// Check the validity of the move for the knight
					boolean validMove = false;
					if ((rowPicked - row == 2 && (columnPicked - column == 1 || column - columnPicked == 1))
							|| (row - rowPicked == 2 && (columnPicked - column == 1 || column - columnPicked == 1))
							|| (columnPicked - column == 2 && (rowPicked - row == 1 || row - rowPicked == 1))
							|| (column - columnPicked == 2 && (rowPicked - row == 1 || row - rowPicked == 1))) {
						validMove = true;
					}

					if (validMove && backdoor[rowPicked][columnPicked] != '0') {
						// Update the board after a valid move
						matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
						backdoor[rowPicked][columnPicked] = backdoor[row][column];
						matrixBoard[row][column] = charAux;
						backdoor[row][column] = charAux;
						turn++;
					} else {
						System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
					}
				}
				// Check if the selected piece is a king
				else if (matrixBoard[row][column] == 'K') {
					// Check the validity of the move for the king
					boolean validMove = false;
					int rowDiff = rowPicked - row;
					int colDiff = columnPicked - column;

					if ((rowDiff == 1 || rowDiff == 0 || rowDiff == -1)
							&& (colDiff == 1 || colDiff == 0 || colDiff == -1)) {
						validMove = true;
					}

					if (validMove && backdoor[rowPicked][columnPicked] != '0') {
						// Update the board after a valid move
						matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
						backdoor[rowPicked][columnPicked] = backdoor[row][column];
						matrixBoard[row][column] = charAux;
						backdoor[row][column] = charAux;
						turn++;
					} else {
						System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
					}
				}
			}

			// Check if the piece in the selected position is '1' (pawn for player 2)
			else if (backdoor[row][column] == '1') {
				// Check if the selected piece is a pawn
				if (matrixBoard[row][column] == 'P') {
					// Check the validity of the move for the pawn
					if ((backdoor[rowPicked][columnPicked] != row + 1 && backdoor[rowPicked][columnPicked] != '0')
							|| (rowPicked == row + 1 && columnPicked == column - 1
									&& backdoor[rowPicked][columnPicked] == '0')
							|| (rowPicked == row + 1 && columnPicked == column + 1
									&& backdoor[rowPicked][columnPicked] == '0')) {

						// Check if the pawn is at the starting position (row 1)
						if (row == 1) {
							// Check if the move is valid for the starting position
							if (rowPicked == row + 1 || rowPicked == row + 2) {
								// Update the board after a valid move
								matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
								backdoor[rowPicked][columnPicked] = backdoor[row][column];
								matrixBoard[row][column] = charAux;
								backdoor[row][column] = charAux;
								turn++;
							} else {
								System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
							}
						} else {
							// Check if the move is valid for non-starting positions
							if ((matrixBoard[rowPicked][columnPicked] == matrixBoard[row + 1][column])
									|| (rowPicked == row + 1 && columnPicked == column - 1
											&& backdoor[rowPicked][columnPicked] == '0')
									|| (rowPicked == row + 1 && columnPicked == column + 1
											&& backdoor[rowPicked][columnPicked] == '0')) {

								// Update the board after a valid move
								matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
								backdoor[rowPicked][columnPicked] = backdoor[row][column];
								matrixBoard[row][column] = charAux;
								backdoor[row][column] = charAux;
								turn++;
							} else {
								System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
							}
						}
					} else {
						System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
					}
				}
				// Check if the selected piece is a rook
				else if (matrixBoard[row][column] == 'T') {
					// Check the validity of the move for the rook
					boolean validMove = false;

					// Check for vertical and horizontal moves
					for (int i = 0; i < 8; i++) {
						if ((i != row && matrixBoard[i][column] != '-')
								|| (i != column && matrixBoard[row][i] != '-')) {
							// If there's any piece in the same row or column, set validMove to true
							validMove = true;
							break;
						}
					}

					// Check for horizontal movement
					if (rowPicked == row && Math.abs(columnPicked - column) > 1) {
						int start = Math.min(column, columnPicked) + 1;
						int end = Math.max(column, columnPicked);

						// Check if there are any obstacles in the horizontal path
						for (int i = start; i < end; i++) {
							if (matrixBoard[row][i] != '-' || backdoor[row][i] != '0') {
								validMove = false;
								break;
							}
						}
					}

					// Check for vertical movement
					else if (columnPicked == column && Math.abs(rowPicked - row) > 1) {
						int start = Math.min(row, rowPicked) + 1;
						int end = Math.max(row, rowPicked);

						// Check if there are any obstacles in the vertical path
						for (int i = start; i < end; i++) {
							if (matrixBoard[i][column] != '-' || backdoor[i][column] != '0') {
								validMove = false;
								break;
							}
						}
					} else {
						// Invalid move for rook (neither horizontal nor vertical)
						validMove = false;
					}

					// Check if the move is valid and the destination cell has a different piece
					if (validMove && backdoor[rowPicked][columnPicked] != backdoor[row][column]) {
						// Update the board after a valid move
						matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
						backdoor[rowPicked][columnPicked] = backdoor[row][column];
						matrixBoard[row][column] = charAux;
						backdoor[row][column] = charAux;
						turn++;
					} else {
						// Print an error message for an invalid move
						System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
					}
				}

				// Check if the selected piece is a bishop
				else if (matrixBoard[row][column] == 'A') {
					// Check the validity of the move for the bishop
					boolean validMove = false;

					// Check if the movement is diagonal and not staying in the same position
					if (Math.abs(rowPicked - row) == Math.abs(columnPicked - column) && rowPicked != row
							&& columnPicked != column) {
						validMove = true;
					}

					if (validMove) {
						// Determine the direction of the diagonal movement
						int rowStep, columnStep;
						if (row < rowPicked) {
							rowStep = 1;
						} else {
							rowStep = -1;
						}

						if (column < columnPicked) {
							columnStep = 1;
						} else {
							columnStep = -1;
						}

						int i = row + rowStep;
						int j = column + columnStep;

						// Check for obstacles along the diagonal path
						while (i != rowPicked && j != columnPicked) {
							if (matrixBoard[i][j] != '-') {
								validMove = false;
								break;
							}
							i += rowStep;
							j += columnStep;
						}
					}

					// Check if the move is valid and the destination cell has a different piece
					if (validMove) {
						if (backdoor[rowPicked][columnPicked] != backdoor[row][column]) {
							// Update the board after a valid move
							matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
							backdoor[rowPicked][columnPicked] = backdoor[row][column];
							matrixBoard[row][column] = charAux;
							backdoor[row][column] = charAux;
							turn++;
						} else {
							// Print an error message for an invalid move
							System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
						}
					} else {
						// Print an error message for an invalid move
						System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
					}
				}

				// Check if the selected piece is a queen
				else if (matrixBoard[row][column] == 'Q') {
				    // Check the validity of the move for the queen
				    boolean validMove = false;

				    // Check for vertical and horizontal moves (rook movement)
				    for (int i = 0; i < 8; i++) {
				        if ((i != row && matrixBoard[i][column] != '-')
				                || (i != column && matrixBoard[row][i] != '-')) {
				            // If there's any piece in the same row or column, set validMove to true
				            validMove = true;
				            break;
				        }
				    }

				    // Check for horizontal movement
				    if (rowPicked == row && Math.abs(columnPicked - column) > 1) {
				        int start = Math.min(column, columnPicked) + 1;
				        int end = Math.max(column, columnPicked);

				        // Check if there are any obstacles in the horizontal path
				        for (int i = start; i < end; i++) {
				            if (matrixBoard[row][i] != '-') {
				                validMove = false;
				                break;
				            }
				        }
				    }

				    // Check for vertical movement
				    else if (columnPicked == column && Math.abs(rowPicked - row) > 1) {
				        int start = Math.min(row, rowPicked) + 1;
				        int end = Math.max(row, rowPicked);

				        // Check if there are any obstacles in the vertical path
				        for (int i = start; i < end; i++) {
				            if (matrixBoard[i][column] != '-') {
				                validMove = false;
				                break;
				            }
				        }
				    }

				    // Check if the movement is diagonal and not staying in the same position
				    // (bishop movement)
				    else if (Math.abs(rowPicked - row) == Math.abs(columnPicked - column) && rowPicked != row
				            && columnPicked != column) {
				        validMove = true;

				        // Determine the direction of the diagonal movement
				        int rowStep, columnStep;
				        if (row < rowPicked) {
				            rowStep = 1;
				        } else {
				            rowStep = -1;
				        }

				        if (column < columnPicked) {
				            columnStep = 1;
				        } else {
				            columnStep = -1;
				        }

				        int i = row + rowStep;
				        int j = column + columnStep;

				        // Check for obstacles along the diagonal path
				        while (i != rowPicked && j != columnPicked) {
				            if (matrixBoard[i][j] != '-') {
				                validMove = false;
				                break;
				            }
				            i += rowStep;
				            j += columnStep;
				        }
				    } else {
				        // Invalid move for queen (neither horizontal nor vertical nor diagonal)
				        validMove = false;
				    }

				    // Check if the move is valid and the destination cell has a different piece
				    if (validMove && backdoor[rowPicked][columnPicked] != backdoor[row][column]) {
				        // Update the board after a valid move
				        matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
				        backdoor[rowPicked][columnPicked] = backdoor[row][column];
				        matrixBoard[row][column] = charAux;
				        backdoor[row][column] = charAux;
				        turn++;
				    } else {
				        // Print an error message for an invalid move
				        System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
				    }
				}
				// Check if the selected piece is a knight
				else if (matrixBoard[row][column] == 'C') {
					// Check the validity of the move for the knight
					boolean validMove = false;
					if ((rowPicked - row == 2 && (columnPicked - column == 1 || column - columnPicked == 1))
							|| (row - rowPicked == 2 && (columnPicked - column == 1 || column - columnPicked == 1))
							|| (columnPicked - column == 2 && (rowPicked - row == 1 || row - rowPicked == 1))
							|| (column - columnPicked == 2 && (rowPicked - row == 1 || row - rowPicked == 1))) {
						validMove = true;
					}

					if (validMove && backdoor[rowPicked][columnPicked] != '1') {
						// Update the board after a valid move
						matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
						backdoor[rowPicked][columnPicked] = backdoor[row][column];
						matrixBoard[row][column] = charAux;
						backdoor[row][column] = charAux;
						turn++;
					} else {
						System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
					}
				}
				// Check if the selected piece is a king
				else if (matrixBoard[row][column] == 'K') {
					// Check the validity of the move for the king
					boolean validMove = false;
					int rowDiff = rowPicked - row;
					int colDiff = columnPicked - column;

					if ((rowDiff == 1 || rowDiff == 0 || rowDiff == -1)
							&& (colDiff == 1 || colDiff == 0 || colDiff == -1)) {
						validMove = true;
					}

					if (validMove == true && backdoor[rowPicked][columnPicked] != '1') {
						// Update the board after a valid move
						matrixBoard[rowPicked][columnPicked] = matrixBoard[row][column];
						backdoor[rowPicked][columnPicked] = backdoor[row][column];
						matrixBoard[row][column] = charAux;
						backdoor[row][column] = charAux;
						turn++;
					} else {
						System.out.println(redColor + "Invalid move, please try again. " + resetColor + "\n");
					}
				}
			}

			// Display the current state of the chess board
			System.out.print(highlightText + lightBrownColor + "   A  B  C  D  E  F  G  H   " + resetColor);
			System.out.println();
			for (int i = 0; i < 8; i++) {
				System.out.print(highlightText + lightBrownColor + ((i - 8) * (-1)) + " " + resetColor);
				for (int j = 0; j < 8; j++) {
					if (backdoor[i][j] == '0') {
						System.out.print("(" + matrixBoard[i][j] + ")");
					} else if (backdoor[i][j] == '1') {
						System.out.print(redColor + "[" + matrixBoard[i][j] + "]" + resetColor);
					} else {
						System.out.print(" " + matrixBoard[i][j] + " ");
					}
				}
				System.out.print(highlightText + lightBrownColor + ((i - 8) * (-1)) + " " + resetColor);
				System.out.println();
			}

			System.out.print(highlightText + lightBrownColor + "   A  B  C  D  E  F  G  H   " + resetColor);
			System.out.println();

			// Check for missing kings and declare a winner
			boolean whiteKingExists = false;
			boolean blackKingExists = false;

			// Iterate through the board to find remaining kings
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (matrixBoard[i][j] == 'K') {
						if (backdoor[i][j] == '0') {
							whiteKingExists = true;
						} else if (backdoor[i][j] == '1') {
							blackKingExists = true;
						}
					}
				}
			}

			// Declare who the winner is
			if (!whiteKingExists && blackKingExists) {
				System.out.println(limeColor + highlightText + "Black King wins! Game over." + resetColor);
				game = true;
			} else if (whiteKingExists && !blackKingExists) {
				System.out.println(limeColor + highlightText + "White King wins! Game over." + resetColor);
				game = true;
			}

		} while (game == false);
	}
}