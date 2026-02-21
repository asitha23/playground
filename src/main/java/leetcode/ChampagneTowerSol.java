package leetcode;

import static java.lang.IO.println;

public class ChampagneTowerSol {

    //Wrong implementation
    public double champagneTower(int poured, int query_row, int query_glass) {

        int glassOnTop = calGlassOnTop(query_row);

        if (glassOnTop >= poured)
            return 0d;
        int rest = poured - glassOnTop;

        if (rest >= query_row + 1)
            return 1d;
        double x =  ((double)rest/(2d *query_row));
        if (query_row < 2 || query_glass == query_row || query_glass == 0)
            return x;

        return 2d * x;

    }

    private int calGlassOnTop(int row) {
        int res = 0;
        for (int i = 1; i <= row; i++ ) {
            res += i;
        }
        return res;
    }

    void main() {
        ChampagneTowerSol o = new ChampagneTowerSol();
        println(o.champagneTower(25, 6,1));
        println(o.champagneTowerGemi(25, 6,1));
    }

        //correct implementation
        public double champagneTowerGemi(int poured, int query_row, int query_glass) {
            // We use a 2D array to represent the glasses.
            // Even though it's a pyramid, a 101x101 matrix is sufficient.
            double[][] tower = new double[102][102];

            // Put all the poured liquid into the first glass
            tower[0][0] = (double) poured;

            for (int row = 0; row <= query_row; row++) {
                for (int col = 0; col <= row; col++) {
                    // Calculate the excess champagne
                    double excess = (tower[row][col] - 1.0) / 2.0;

                    // If there is excess, distribute it to the two glasses below
                    if (excess > 0) {
                        tower[row + 1][col] += excess;
                        tower[row + 1][col + 1] += excess;
                    }
                }
            }

            // The glass might have received more than 1 cup,
            // but it can only hold a maximum of 1.0.
            return Math.min(1.0, tower[query_row][query_glass]);
        }
}
