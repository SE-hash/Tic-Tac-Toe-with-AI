package tictactoe;

import java.util.Random;
import java.util.Scanner;

class board {
    String cells;
    int turns;

    board(String str) {
        cells = str;
    }

    void disCells(int marks) {
        System.out.println("---------");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.print("| ");
            }
            if (i == marks) {
                StringBuilder c = new StringBuilder(cells);
                if (turns % 2 == 0) {
                    c.setCharAt(marks, 'O');
                    cells = c.toString();
                    System.out.print("O ");
                } else {
                    c.setCharAt(marks, 'X');
                    cells = c.toString();
                    System.out.print("X ");
                }
            } else {
                System.out.print(cells.charAt(i) + " ");
            }

            if (i % 3 == 2) {
                System.out.println("|");
            }
        }
        System.out.println("---------");
    }

    void menu() {
        Scanner scan = new Scanner(System.in);

        boolean isExit = false;

        while (!isExit) {
            System.out.print("Input command: ");
            String command = scan.nextLine();
            switch (command) {
                case "start easy easy":
                    play(false, false);
                    break;
                case "start easy user":
                    play(false, true);
                    break;
                case "start user easy":
                    play(true, false);
                    break;
                case "start user user":
                    play(true, true);
                    break;
                case "exit":
                    isExit = true;
                    break;
                default:
                    System.out.println("Bad parameters!");
                    break;
            }
        }
    }

    /**
     * true表示user    false 表示easy
     *
     * @param player1 play X
     * @param player2 play O
     */
    void play(boolean player1, boolean player2) {
        turns = 1;

        disCells(-1);

        while (true) {
            move(player1);
            if (judge()) {
                cells = "         ";
                break;
            }
            move(player2);
            if (judge()) {
                cells = "         ";
                break;
            }
        }
    }

    void move(boolean is) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        String coordinates;
        int coordinateX, coordinateY;
        int marks;

        if (is) {
            System.out.print("Enter the coordinates: ");
            if (scan.hasNextInt()) {
                coordinates = scan.nextLine();
                coordinateX = (coordinates.charAt(0) - '0');
                coordinateY = (coordinates.charAt(2) - '0');
                marks = (coordinateX - 1) * 3 + (coordinateY - 1);

                if (coordinateX > 3 || coordinateX < 0 || coordinateY > 3 || coordinateY < 0) {
                    System.out.println("Coordinates should be form 1 to 3!");
                    return;
                }
                if (cells.charAt(marks) != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    return;
                }

                disCells(marks);
                turns++;

            } else {
                System.out.println("You should enter numbers!");
                scan.nextLine();
            }
        }
        else {
            marks = random.nextInt(9);
            System.out.println("Making move level \"easy\"");
            while (cells.charAt(marks) != ' ') {
                marks = random.nextInt(9);
            }
            disCells(marks);
            turns++;
        }
    }

    boolean judge() {
        boolean draw;
        boolean impossible;
        boolean xWin;
        boolean oWin;

        xWin = check(cells, 'X');
        oWin = check(cells, 'O');
        impossible = (xWin && oWin) || count(cells);
        draw = (isMax(cells)) && !xWin && !oWin;

        if (impossible) {
            System.out.println("Impossible");
            return true;
        }
        if (xWin) {
            System.out.println("X wins");
            return true;
        }
        if (oWin) {
            System.out.println("O wins");
            return true;
        }
        if (draw) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    /**
     * @param cells 棋盘
     * @param a     取X或者O
     * @return 检查字符a是否胜利
     */
    static boolean check(String cells, char a) {
        if (cells.charAt(0) == a && cells.charAt(0) == cells.charAt(1) && cells.charAt(1) == cells.charAt(2)) {
            return true;
        } else if (cells.charAt(3) == a && cells.charAt(3) == cells.charAt(4) && cells.charAt(4) == cells.charAt(5)) {
            return true;
        } else if (cells.charAt(6) == a && cells.charAt(6) == cells.charAt(7) && cells.charAt(7) == cells.charAt(8)) {
            return true;
        } else if (cells.charAt(0) == a && cells.charAt(0) == cells.charAt(3) && cells.charAt(3) == cells.charAt(6)) {
            return true;
        } else if (cells.charAt(1) == a && cells.charAt(1) == cells.charAt(4) && cells.charAt(4) == cells.charAt(7)) {
            return true;
        } else if (cells.charAt(2) == a && cells.charAt(2) == cells.charAt(5) && cells.charAt(5) == cells.charAt(8)) {
            return true;
        } else if (cells.charAt(0) == a && cells.charAt(0) == cells.charAt(4) && cells.charAt(4) == cells.charAt(8)) {
            return true;
        } else
            return cells.charAt(2) == a && cells.charAt(2) == cells.charAt(4) && cells.charAt(4) == cells.charAt(6);
    }

    /**
     * @param cells 棋盘
     * @return 检查棋盘中X和O的数量，若其中一个比另外一数量多出1以上返回true
     */
    static boolean count(String cells) {
        int x = 0;
        int o = 0;
        for (int i = 0; i < cells.length(); i++) {
            if (cells.charAt(i) == 'X') {
                x++;
            }
            if (cells.charAt(i) == 'O') {
                o++;
            }
        }
        return Math.abs(x - o) > 1;
    }

    /**
     * @param cells 棋盘
     * @return 如果棋盘中没有可以下子的地方，返回true
     */
    static boolean isMax(String cells) {
        int num = 0;
        for (int i = 0; i < cells.length(); i++) {
            if (cells.charAt(i) == ' ') {
                num++;
            }
        }
        return num <= 0;
    }
}