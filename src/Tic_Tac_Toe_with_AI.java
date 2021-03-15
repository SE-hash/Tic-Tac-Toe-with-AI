import java.util.Scanner;

public class Tic_Tac_Toe_with_AI {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the cells: ");
        String cells = scan.nextLine().replace('_', ' ');

        System.out.println("---------");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.print("| ");
            }
            if (cells.charAt(i) == '_') {
                System.out.print("  ");
            } else {
                System.out.print(cells.charAt(i) + " ");
            }
            if (i % 3 == 2) {
                System.out.println("|");
            }
        }
        System.out.println("---------");

        int turns;
        if (judge(cells) == 'X') {
            turns = 1;
        } else turns = 2;


        while (true) {
            int coordinateX;
            int coordinateY;
            int marks;
            System.out.print("Enter the coordinates: ");
            if (scan.hasNextInt()) {
                String coordinate = scan.nextLine();
                coordinateX = (coordinate.charAt(0) - '0');
                coordinateY = (coordinate.charAt(2) - '0');
                marks = (coordinateX - 1) * 3 + (coordinateY - 1);
                if (coordinateX > 3 || coordinateX < 0 || coordinateY > 3 || coordinateY < 0) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                if (cells.charAt(marks) != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                cells = paintX(cells, marks, turns);

                boolean gameNotFinished;
                boolean draw;
                boolean impossible;
                boolean xWin;
                boolean oWin;

                xWin = check(cells, 'X');
                oWin = check(cells, 'O');
                impossible = (xWin && oWin) || count(cells);
                draw = (isMax(cells)) && !xWin && !oWin;
                gameNotFinished = !xWin && !oWin && !draw;

                if (impossible) {
                    System.out.println("Impossible");
                    return;
                }

                if (xWin) {
                    System.out.println("X wins");
                    return;
                }
                if (oWin) {
                    System.out.println("O wins");
                    return;
                }
                if (draw) {
                    System.out.println("Draw");
                    return;
                }
                if (gameNotFinished) {
                    System.out.println("Game not finished");
                    return;
                }
            } else {
                System.out.println("You should enter numbers!");
                scan.nextLine();
            }
        }
    }

    static String paintX(String cells, int marks, int turns) {
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
        return cells;
    }

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
        } else return cells.charAt(2) == a && cells.charAt(2) == cells.charAt(4) && cells.charAt(4) == cells.charAt(6);
    }

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

    static char judge(String cells) {
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
        if (x <= o) {
            return 'X';
        } else {
            return 'O';
        }
    }

    static boolean isMax(String cells) {
        int num = 0;
        for (int i = 0; i < cells.length(); i++) {
            if (cells.charAt(i) == ' ') {
                num++;
            }
        }
        return num == 0;
    }
}
