
// returns the fib sequence for a given number n.
public static int fib (int n) {
        if (n <= 1) { return n; }
        else return fib (n - 1) + fib (n - 2);

}

// dynamic program version of fib sequence.
public static int fib2(int n) {
        int f[] = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
                f[i] = f[i-1] + f[i-2];
        }
        return f[n];
}

// Reverse Integer. 123 -> 321.
public int reverse(int a) {
        boolean neg = false;
        ArrayList<Integer> i = new ArrayList<Integer>();
        if (a < 0) {
                neg = true;
                a *= -1;
        }
        while (a > 9) {
                i.add(a % 10);
                a /= 10;
        }
        i.add(a);

        a = i.get(0);
        if (neg) {
                a *= -1;
        }
        for (int j = 1; j < i.size(); j++) {
                int prev = a;
                if (neg) {
                        a = a * 10 - i.get(j);
                        if (prev != a / 10) {
                                return 0;
                        }
                } else {
                        a = a * 10 + i.get(j);
                        if (prev != a / 10) {
                                return 0;
                        }
                }
        }
        return a;
}


// divide two numbers without '/'
public static void divide(int dividend, int divider) {
        int quotient = 0;
        while (dividend >= divider) {
                dividend = dividend - divider;
                quotient++;
        }
        return quotient;
}

// returns the first bad version O(logn).
// Given n is the latest commit.
public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while (start < end) {
                // takes care of integer overflow.
                int mid = start + (end-start) / 2;
                if (isBadVersion(mid)) {
                        end = mid;
                } else {
                        start = mid + 1;
                }
        }
        return start;
}

// recursive version of first bad version.
public int firstBadVersion(int n) {
  return firstBadVersionTail(1, n);
}

public int firstBadVersionTail(int start, int end) {
  int mid = start + (end-start) / 2;
  if (start >= end) {
    return start;
  }
  if (isBadVersion(mid)) {
    return firstBadVersionTail(start, mid)
  } else {
    return firstBadVersionTail(mid + 1, end);
  }
}
