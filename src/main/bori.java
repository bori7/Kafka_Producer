public class bori {

    public List<Integer> idealDays(int[] days, int k) {

        int n = days.length;
        List<Integer> ideals = new ArrayList<>();
        int[] preIdealDays = new int[n];
        int[] postIdealDays = new int[n];

        for (int i = 1; i < n; i++) {
            if (days[i] <= days[i - 1]) {
                preIdealDays[i] = preIdealDays[i-1] + 1;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (days[i] <= days[i + 1]) {
                postIdealDays[i] = postIdealDays[i+1] + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            if (preIdealDays[i] >= k && postIdealDays[i] >= k) {
                ideals.add(i + 1);
            }
        }
        return ideals;
    }

}

def idealDays(days, k):
n = len(days)
result = []
leftIdeal = [0 for _ in range(n)]
rightIdeal = [0 for _ in range(n)]
for i in range(1, n):
if days[i] <= days[i-1]:
leftIdeal[i] = leftIdeal[i-1] + 1
for i in reversed(range(n-1)):
if days[i] <= days[i+1]:
rightIdeal[i] = rightIdeal[i+1] + 1
for i in range(n):
if leftIdeal[i] >= k and rightIdeal[i] >= k:
result.append(i+1)
return result