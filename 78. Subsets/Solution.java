
import java.util.*;

class Solution {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> current = new ArrayList<>();

        backtrack(nums, 0, current, result);

        return result;
    }

    public void backtrack(int[] nums, int index,
                          List<Integer> current,
                          List<List<Integer>> result) {

        // Store the current subset
        result.add(new ArrayList<>(current));

        // Explore remaining elements
        for (int i = index; i < nums.length; i++) {

            // Choose
            current.add(nums[i]);

            // Explore
            backtrack(nums, i + 1, current, result);

            // Undo choice (Backtracking)
            current.remove(current.size() - 1);
        }
    }
}
