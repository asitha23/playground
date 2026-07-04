class MatrixRotation {

    /**
     * Given two n x n binary matrices mat and target, return true if it is possible to make mat
     * equal to target by rotating mat in 90-degree increments, or false otherwise.
     * <a href="https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/">link</a>
     * @param mat array
     * @param target array
     * @return boolean
     */
    public boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++) {
            if (isSame(mat, target)) {
                return true;
            }
            mat = rotateClockWise(mat);
        }
        return false;
    }

    private int[][] rotateClockWise(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] ans = new int[row][col];
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row; i++) {
                ans[j][row - 1 - i] = mat[i][j];
            }
        }
        return ans;
    }

    private boolean isSame(int[][] mat, int[][] target) {
        int row = mat.length;
        int col = mat[0].length;
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row; i++) {
                if (target[i][j] != mat[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
