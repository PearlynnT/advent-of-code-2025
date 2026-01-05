package day8;

import java.util.ArrayList;
import java.util.List;

public class UnionFind {
    int[] parent;
    int[] size;
    int numDisjoint;

    UnionFind(int n) {
        this.parent = new int[n];
        this.size = new int[n];
        this.numDisjoint = n;
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    public int find(int i) {
        if (this.parent[i] == i) {
            return i;
        }
        return find(this.parent[i]);
    }

    public void union(int i, int j) {
        int irep = find(i);
        int jrep = find(j);
        if (irep != jrep) {
            this.parent[irep] = jrep;
            this.size[jrep] += this.size[irep];
            this.numDisjoint--;
        }
    }

    public List<Integer> getComponentSizes() {
        List<Integer> componentSizes = new ArrayList<>();
        boolean[] isParentProcessed = new boolean[this.parent.length];
        for (int i = 0; i < this.parent.length; i++) {
            int rep = find(i);
            if (!isParentProcessed[rep]) {
                componentSizes.add(this.size[rep]);
                isParentProcessed[rep] = true;
            }
        }
        return componentSizes;
    }

    public int getNumDisjoint() {
        return this.numDisjoint;
    }
}
