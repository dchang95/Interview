

// TODO: Look at elevator interview problem.



// Count number of Islands.
class Solution {
private int row;
private int col;

public int numIslands(char[][] grid) {
        int count = 0;
        row = grid.length;
        if (row == 0) {
                return 0;
        }
        col = grid[0].length;
        boolean visited[][] = new boolean[row][col];
        for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                        if (grid[i][j] == '1' && !visited[i][j]) {
                                DFS(grid, i, j, visited);
                                count++;
                        }
                }
        }
        return count;

}

public boolean isSafe(char[][] grid, int r, int c, boolean[][] visited) {
        return (r >= 0) && (r< row) && (c >= 0) && (c < col) && (!visited[r][c]) && (grid[r][c] == '1');
}

public void DFS(char[][] grid, int r, int c, boolean[][] visited) {
        visited[r][c] = true;
        int[] rowNum = {-1, 1, 0, 0};
        int[] colNum = {0, 0, -1, 1};
        for (int k = 0; k < 4; k++) {
                if (isSafe(grid, r + rowNum[k], c + colNum[k], visited)) {
                        DFS(grid, r + rowNum[k], c + colNum[k], visited);
                }
        }

}
}

// Count number of encodings of a given digit sequence. Recursive
int countDecoding(String digits) {
        int n = digits.length();
        if (n == 0 || n == 1) {
                return 1;
        }
        int count = 0;
        // look at the last digit and recur by taking that out.
        if (digits.charAt(n-1) == '1') {
                count = countDecoding(digits.substring(0, n - 1));
        }
        // look at the last 2 digits and recur over that.
        if ((digits.charAt(n-2) < '2') || (digits.charAt(n-2) == '2' && digits.charAt(n-1) < '7')) {
                count += countDecoding(digits.substring(0, n - 2));
        }
        return count;
}

// count number of encodings DP solution.
int countDecodingDP(String digits) {
        int n = digits.length();
        if (n == 0) {return 0;}
        if (s.charAt(0) == '0') { return 0; }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
                if (digits.charAt(n-1) == '1') {
                        dp[i] += dp[i - 1];
                }
                if (digits.charAt(n-2) < '2' || (digits.charAt(n-2) == '2') && digits.charAt(n-1) < '7') {
                        dp[i] += dp[i - 2];
                }
        }
        return dp[n];
}

static int editDistanceRescursive(String s1, String s2) {
  return editDistanceHelper(s1, s2, s1.length(), s2.length());
}

static int editDistanceHelper(String s1, String s2, int n, int m) {
  if (m == 0) {
    return n;
  }
  if (n == 0) {
    return m;
  }
  if (s1.charAt(n-1) == s2.charAt(m-1)) {
    return editDistanceHelper(s1, s2, n-1, m-1);
  }
  return 1 + min(editDistanceHelper(s1, s2, n-1, m),
  editDistanceHelper(s1, s2, n, m-1),
  editDistanceHelper(s1, s2, n-1, m-1));
}

// find min number of operations to transform string str1 -> str2
static int editDistance (String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int dp [][] = new int[m + 1][n+1];
        for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                        if (i == 0) {
                                // if no string 1, then insert everything.
                                dp[i][j] = j;
                                // if no string 2, then remove everything.
                        } else if (j == 0) {
                                dp[i][j] = i;
                                // if last characters are the same ignore it, and recur from the rest.
                        } else if (str1.charAt(i-1) == str2.chartAt(j-1)) {
                                dp[i][j] = dp[i-1][j-1];
                                // consider insert remove and replace and take the min.
                        } else {
                                dp[i][j] = 1 + min(dp[i][j-1], //Insert
                                               dp[i-1][j], // Remove
                                               dp[i-1][j-1]); // Replace
                        }
                }
        }
        return dp[m][n];
}

static int min (int x, int y, int z) {
        if (x < y && x < z) {
                return x;
        }
        if (y < x && y < z) {
                return y;
        } else {
                return z;
        }
}

// returns the length of the longest Palindrome Subsequence.
static int longestPalindromeSubsequence(String s) {
    // recursive
    return resursiveLPS(s, 0, s.length()-1);
}

static int recursiveLPS(String s, int start, int end) {
    if (start == end) {
        return 1;
    }
    if (start + 1 == end && s.charAt(start) == s.charAt(end)) {
        return 2;
    }
    if (s.charAt(start) == s.charAt(end)) {
        return recursiveLPS(s, start + 1, end - 1) + 2;
    }
    return Math.max(recursiveLPS(s, start + 1, end), recursiveLPS(s, start, end - 1));
}

static int[][] dpLPS(String s) {
    int n = s.length()
    int [][] dp = new int[n][n];
    for (int i = 0; i < n; i++) {
        dp[i][i] = 1;
    }
    for (int substring = 2; i <= n; substring++) {
        for (int start = 0; start < n - substring + 1; start++) {
            int end = start + substring - 1;
            if (c1 == 2 && s.charAt(start) == s.charAt(end)) {
                dp[start][end] = 2;
            }
            else if (s.charAt(start) == s.charAt(end)) {
                dp[start][end] = dp[start + 1][end - 1] + 2;
            } else {
                dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
            }

        }
    }
    return dp;
}
