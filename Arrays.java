// returns the index in the array such that the elements on the left sum is equal to the sum of the elements on the right.
public static int equilibrium(int[] arr) {
    int length = arr.length();
    for (int i = 1; i < length; i++) {
        int leftSum = 0;
        int rightSum = 0;
        for (int j = 0; j < i; j++) {
            leftSum += arr[j];
        }
        for (int k = i + 1; k < length; k++) {
            rightSum += arr[k];
        }
        if (rightSum == leftSum) {
            return i;
        }
    }
    return -1;
}

// find the max contiguous sum.
public static int maxContiguous(ArrayList<Integer> arr) {
        int result = Integer.MIN_VALUE;
        int curr = 0;
        for (Integer i : arr) {
                curr += i;
                result = Math.max(curr, result);
                // reset if negative.
                if (curr < 0) {
                        curr = 0;
                }
        }
        return result;
}

// returns true if there is a contiguous subarray that sums to target val.
boolean subArraySum(int[] x, int target) {
        // take the first index.
        int curr_sum = x[0];
        int start = 0;
        for (int i = 1; i < x.length; i++) {
                // if curr_sum is greater than target sum, we remove from the beginning since we are looking for contiguous sum
                while (curr_sum > target && start < i - 1) {
                        curr_sum = curr_sum - x[start];
                        start++;
                }
                // if we find the target, return true;
                if (curr_sum == target) {
                        return true;
                }
                // keep adding the numbers.
                if (i < x.length) {
                        curr_sum += x[i];
                }
        }
        return false;
}

// rotate array by k

public static void rotate(int[] nums, int k) {
        // accounts for wrap around.
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);

}

public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
        }
}

// Given an array S of n integers, are there 3 elements a, b, c such that a+b+c = 0
// Find All triplets. (must be unique)
public ArrayList<ArrayList<Integer> > threeSum(ArrayList<Integer> a) {
        ArrayList<ArrayList<Integer> > result = new ArrayList<ArrayList<Integer> >();
        Collections.sort(a);
        for (int i = 0; i < a.size() - 2; i++) {
                if (i > 0 && a.get(i).equals(a.get(i-1))) {
                        continue;
                }
                int next = i+1;
                int end = a.size()-1;
                while (next < end) {
                        if (a.get(i) + a.get(next) + a.get(end) == 0) {
                                ArrayList<Integer> curr = new ArrayList<Integer>();
                                curr.add(a.get(i));
                                curr.add(a.get(next));
                                curr.add(a.get(end));
                                result.add(curr);
                                // duplicate check
                                while (next < end && a.get(next).equals(a.get(next+1))) {
                                        next++;
                                }
                                while (next < end && a.get(end).equals(a.get(end-1))) {
                                        end--;
                                }
                                next++;
                                end--;
                        } else if (a.get(i) + a.get(next) + a.get(end) < 0) {
                                next++;
                        } else {
                                end--;
                        }
                }
        }
        return result;
}

/*
   Anti diagonals.

   Input:

   1 2 3
   4 5 6
   7 8 9

   Return the following :

   [
   [1],
   [2, 4],
   [3, 5, 7],
   [6, 8],
   [9]
   ]
 */

public ArrayList<ArrayList<Integer> > diagonal (ArrayList<ArrayList<Integer> > a) {
        ArrayList<ArrayList<Integer> > result = new ArrayList<ArrayList<Integer> >();
        int size = a.size();
        // row + columns - 1 is the number of diagonals.
        int diagonals = size * 2 - 1;
        for (int i = 0; i < diagonals; i++) {
                getDiagonal(a, i);
        }
        return result;
}

public ArrayList<Integer> getDiagonal(ArrayList<ArrayList<Integer> > a, int i) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int j = 0; j <= i; j++) {
                // grabbing the count of the elements in a diagonal.
                int count = i - j;
                if (count < a.size() && j < a.size()) {
                        result.add(a.get(j).get(count));
                }
        }
        return result;
}

// find the largest common prefix in an array of Strings. Time Complexity O(s) where s is the sum of all characters in the string.
public String longestCommonPrefix(ArrayList<String> a) {
  // horizontal scanning.
        if (a.size() == 0) {
                return "";
        }
        String result = a.get(0);
        for (int i = 1; i < a.size(); i++) {
                while (a.get(i).indexOf(result) != 0) {
                        result = result.substring(0, result.length()-1);
                        if (result.isEmpty()) { return "";}
                }
        }
        return result;
}

// Generate Pascals Triangle based on a.
public ArrayList<ArrayList<Integer>> generate(int a) {
        ArrayList<ArrayList<Integer> > result = new ArrayList<ArrayList<Integer> >();
        for (int i = 0; i < a; i++) {
                ArrayList<Integer> currRow = new ArrayList<Integer>();
                // base case add 1.
                if (result.size() == 0) {
                        currRow.add(1);
                } else {
                        // number of rows for the triangle (j <= i).
                        for (int j = 0; j <= i; j++) {
                                // get previous row.
                                ArrayList<Integer> x = result.get(i-1);
                                // check boundaries.
                                if ((j - 1 >= 0) && (j < x.size())) {
                                        // add c-1, c.
                                        currRow.add(x.get(j - 1) + x.get(j));
                                } else {
                                        // is past boundary, add 1.
                                        currRow.add(1);
                                }
                        }

                }
                result.add(currRow);

        }
        return result;
}

// merge intervals. O(nlogn)
public List<Interval> merge(List<Interval> intervals) {
    if (intervals.size() <= 1)
        return intervals;

    // Sort by ascending starting point using an anonymous Comparator
    intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

    List<Interval> result = new LinkedList<Interval>();
    int start = intervals.get(0).start;
    int end = intervals.get(0).end;

    for (Interval interval : intervals) {
        if (interval.start <= end) // Overlapping intervals, move the end if needed
            end = Math.max(end, interval.end);
        else {                     // Disjoint intervals, add the previous one and reset bounds
            result.add(new Interval(start, end));
            start = interval.start;
            end = interval.end;
        }
    }

    // Add the last interval
    result.add(new Interval(start, end));
    return result;
}

// retrun longest substring without repeating characters. O(2n) each char visited twice by i and j.
public int lengthOfLongestSubstring(String s) {
       int n = s.length();
       Set<Character> set = new HashSet<>();
       int ans = 0, i = 0, j = 0;
       while (i < n && j < n) {
           // try to extend the range [i, j]
           if (!set.contains(s.charAt(j))){
               set.add(s.charAt(j++));
               ans = Math.max(ans, j - i);
           }
           else {
               set.remove(s.charAt(i++));
           }
       }
       return ans;
   }

// array hopper
public int jump(int[] nums) {
     int maxJumps = 0;
     int stepCount = 0;
     int currJump = 0;
     for (int i = 0; i < nums.length-1; i++) {
         currJump = Math.max(currJump, i + nums[i]);
         if (i == maxJumps) {
             stepCount++;
             maxJumps = currJump;
         }
     }
     return stepCount;
 }
// given n non-negative integers, where the width of each digit is 1,
// compute how much water can fill it
 public int trappingRainWater(int[] height) {
        int leftMax = 0;
        int rightMax = 0;
        int max = 0;
        int start = 0;
        int end = height.length - 1;
        while (start <= end) {
            leftMax = Math.max(leftMax, height[start]);
            rightMax = Math.max(rightMax, height[end]);
            if (leftMax < rightMax) {
                max += (leftMax - height[start]);
                start++;
            } else {
                max += (rightMax - height[end]);
                end--;
            }
        }
        return max;

 }

 public List windowSum (List<Integer> list, int window) {
   List<Integer> result = new ArrayList<Integer>();
   int current = 0;
   for (int i = 0; i < window; i++) {
     current += list.get(i);
   }
   result.add(current);
   int numWindows = list.size() - windows + 1;
   for (int i = 1; numWindows; i++) {
     int sum = current + list.get(i + window - 1) - list.get(i-1);
     result.add(sum);
     current = sum;
   }
   return result;

 }

 private final String[] LessThanTwenty = ["", "One", "Two", ... "Nineteen"];
 private final String[] Tens = ["", "Ten", "Twenty"..., "Ninety"];
 private final String[] thousands = ["", "Thousand", "Million", "Billion"];

 public String numberToWords(int num) {
   if (num == 0) {
     return "Zero";
   }
   int i = 0;
   String words = "";
   while (num > 0) {
     if (num % 1000 != 0) {
       words = helper(num % 1000) + thousands[i] + " " + words;
     }
     num /= 1000;
     i++;
   }
   return words;

 }

 public String helper(int num) {
   if (num == 0) {
     return "";
   } else if (num < 20) {
     return LessThanTwenty[num] + " ";
   } else if (num < 100) {
     return Tens[num / 10] + " " + helper(num % 10);
   } else {
     return LessThanTwenty[num / 100] + " hundred " + helper(num % 100);
    }
 }
