import java.util.*;

class FindAllPpl {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // 1. Sort meetings by time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[2], b[2]));

        // 2. Initialize Union-Find
        UF uf = new UF(n);
        uf.union(0, firstPerson);

        int i = 0;
        int m = meetings.length;
        while (i < m) {
            int currentTime = meetings[i][2];
            Set<Integer> pool = new HashSet<>();
            
            // 3. Process all meetings at the same time
            int j = i;
            while (j < m && meetings[j][2] == currentTime) {
                uf.union(meetings[j][0], meetings[j][1]);
                pool.add(meetings[j][0]);
                pool.add(meetings[j][1]);
                j++;
            }

            // 4. Reset people who aren't connected to Person 0
            for (int person : pool) {
                if (!uf.connected(0, person)) {
                    uf.reset(person);
                }
            }
            i = j;
        }

        // 5. Collect all people connected to 0
        List<Integer> result = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            if (uf.connected(0, k)) {
                result.add(k);
            }
        }
        return result;
    }

    // Helper Union-Find Class
    class UF {
        int[] parent;
        public UF(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) parent[rootI] = rootJ;
        }
        public boolean connected(int i, int j) {
            return find(i) == find(j);
        }
        public void reset(int i) {
            parent[i] = i;
        }
    }

    void main() {
        FindAllPpl findAllPpl = new FindAllPpl();
        System.out.println(findAllPpl.findAllPeople(4, new int[][] { {3,1,3},{1,2,2},{0,3,3}}, 3));
        //ans should be [0, 1, 3]
    }
}