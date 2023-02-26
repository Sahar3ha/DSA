package DSA.Assignment;

import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Question2 {
    public int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }

    public void dfs(int[] nums, LinkedList<Integer>[] tree, int depth, int node, boolean[] visited, int[] ans, Map<Integer,int[]> map, boolean[][] poss) {
        if(visited[node]) return;
        visited[node] = true;
        int ancestor = -1;
        int d = Integer.MAX_VALUE;
        for(int i = 1; i < 51; i++) {
            if(poss[nums[node]][i] && map.containsKey(i)) {
                if(depth - map.get(i)[0] <= d) {
                    d = depth - map.get(i)[0];
                    ancestor = map.get(i)[1];
                }
            }
        }
        ans[node] = ancestor;
        int[] exist = (map.containsKey(nums[node])) ? map.get(nums[node]) :  new int[]{-1,-1};
        map.put(nums[node],new int[]{depth,node});
        for(int child : tree[node]) {
            if(visited[child]) continue;
            dfs(nums, tree, depth+1, child, visited, ans, map, poss);
        }
        if(exist[0] != -1) map.put(nums[node], exist);
        else map.remove(nums[node]);

        return;
    }

    public int[] getCoprimes(int[] nums, int[][] edges) {
        boolean[][] poss = new boolean[51][51];
        for(int i = 1; i < 51; i++) {
            for(int j = 1; j < 51; j++) {
                if(gcd(i,j) == 1) {
                    poss[i][j] = true;
                    poss[j][i] = true;
                }
            }
        }
        int n = nums.length;
        LinkedList<Integer>[] tree = new LinkedList[n];

        for(int i =0 ; i < tree.length; i++) tree[i] = new LinkedList<>();
        for(int edge[] : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = -1;
        Map<Integer,int[]> map = new HashMap<>();

        boolean[] visited = new boolean[n];
        dfs(nums, tree, 0, 0, visited, ans, map, poss);
        System.out.println(Arrays.toString(ans));
        return ans;
    }



        private static class TreeNode {
            int val;
            TreeNode left, right;

            TreeNode(int val) {
                this.val = val;
                this.left = null;
                this.right = null;
            }
        }

        public static int minServiceCenters(TreeNode root) {
            int[] result = helper(root);
            return result[0];
        }

        private static int[] helper(TreeNode node) {
            if (node == null) {
                return new int[]{0, 0};
            }

            int[] leftResult = helper(node.left);
            int[] rightResult = helper(node.right);

            int numCenters = leftResult[0] + rightResult[0];

            if (leftResult[1] == 1 || rightResult[1] == 1) {
                numCenters++;
                return new int[]{numCenters, 2};
            } else {
                return new int[]{numCenters, 1};
            }
        }

        public static void main(String[] args) {
            TreeNode root = new TreeNode(0);
            root.left = new TreeNode(0);
            root.right = new TreeNode(0);
            root.left.left = new TreeNode(0);
            root.right.left = new TreeNode(0);

            System.out.println(minServiceCenters(root));
        }

}
