class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return res;
        }

        Map<String, List<String>> parents = new HashMap<>();
        Set<String> currentLevel = new HashSet<>();
        currentLevel.add(beginWord);
        dict.remove(beginWord);
        boolean found = false;

        while (!currentLevel.isEmpty() && !found) {
            Set<String> nextLevel = new HashSet<>();
            for (String word : currentLevel) {
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char original = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        chars[i] = c;
                        String nextWord = new String(chars);
                        if (dict.contains(nextWord)) {
                            if (nextWord.equals(endWord)) {
                                found = true;
                            }
                            nextLevel.add(nextWord);
                            parents.computeIfAbsent(nextWord, k -> new ArrayList<>()).add(word);
                        }
                    }
                    chars[i] = original;
                }
            }
            dict.removeAll(nextLevel);
            currentLevel = nextLevel;
        }

        if (found) {
            LinkedList<String> path = new LinkedList<>();
            path.add(endWord);
            backtrack(endWord, beginWord, parents, path, res);
        }

        return res;
    }

    private void backtrack(String word, String beginWord, Map<String, List<String>> parents,
                           LinkedList<String> path, List<List<String>> res) {
        if (word.equals(beginWord)) {
            res.add(new ArrayList<>(path));
            return;
        }

        List<String> prevWords = parents.get(word);
        if (prevWords != null) {
            for (String prev : prevWords) {
                path.addFirst(prev);
                backtrack(prev, beginWord, parents, path, res);
                path.removeFirst();
            }
        }
    }
}