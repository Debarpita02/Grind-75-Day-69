import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // Sort the intervals based on their start times
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> mergedIntervals = new ArrayList<>();
        int[] currentInterval = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] nextInterval = intervals[i];

            // Check if the current interval overlaps with the next interval
            if (currentInterval[1] >= nextInterval[0]) {
                // Merge the intervals by updating the end time of the current interval
                currentInterval[1] = Math.max(currentInterval[1], nextInterval[1]);
            } else {
                // If no overlap, add the current interval to the result list
                mergedIntervals.add(currentInterval);
                // Update the current interval to the next interval
                currentInterval = nextInterval;
            }
        }

        // Add the last merged interval to the result list
        mergedIntervals.add(currentInterval);

        // Convert the list of merged intervals to a 2D array
        int[][] result = new int[mergedIntervals.size()][2];
        for (int i = 0; i < mergedIntervals.size(); i++) {
            result[i] = mergedIntervals.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result1 = solution.merge(intervals1);
        System.out.println(Arrays.deepToString(result1)); // Output: [[1,6],[8,10],[15,18]]

        int[][] intervals2 = {{1, 4}, {4, 5}};
        int[][] result2 = solution.merge(intervals2);
        System.out.println(Arrays.deepToString(result2)); // Output: [[1,5]]
    }
}
