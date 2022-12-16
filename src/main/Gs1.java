public class Gs1 {

    public int playlist(int[] songs) {

        int n = songs.length;
        int count = 0;

        for (int i = 0; i < n - 1; i++) {

            for (int j = i + 1; j < n; j++) {

                if ((songs[i] + songs[j]) == 60) {
                    count += 1;

                }
            }
        }

        return count;
    }

}

class Solution {
    public long numPairsDivisibleBy60(List<Integer> songs) {
        int[] hm = new int[61];
        int n = songs.size();
        for (int i = 0; i < n; i++) {
            songs.set(i, songs.get(i) % 60);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (hm[60 - songs.get(i)] != 0)
                res = res + hm[60 - songs.get(i)];
            if (songs.get(i) == 0)
                hm[60] += 1;
            else
                hm[songs.get(i)] += 1;
        }
        return res;
    }
}