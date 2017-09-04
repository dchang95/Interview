// find a needle in a haystack (Brute Force).
public int strStr(final String haystack, final String needle) {
        if (needle == null) {
                return -1;
        }
        int index = 0;
        int j = 0;
        boolean firstIndex = true;
        boolean correct = false;
        boolean done = false;
        for (int i = 0; i < haystack.length(); i++) {
                if (haystack.charAt(i) == needle.charAt(j)) {
                        correct = true;
                        if (firstIndex) {
                                index = i;
                                firstIndex = false;
                        }
                        if (j + 1 >= needle.length() && correct) {
                                done = true;
                                break;
                        }
                        j++;
                } else {
                        if (correct) { i = index; }
                        j = 0;
                        firstIndex = true;
                        correct = false;
                }
        }

        return done ? index : -1;
}

// Reverse words Ex. The Sky -> Sky The.
public String reverseWords(String a) {
        // trims leading/trailing spaces.
        a = a.trim();
        a = a.replaceAll(" +", " ");
        String res = "";
        String[] x = a.split(" ");
        for (int i = 0; i < x.length/2; i++) {
                String temp = x[i];
                x[i] = x[x.length-i-1];
                x[x.length-i-1] = temp;
        }
        for (int j = 0; j < x.length; j++) {
                if (x.length == 1 || j == x.length -1) {
                        res += x[j];
                        break;
                }

                res += x[j] + " ";
        }

        return res;
}
