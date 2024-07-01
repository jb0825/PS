package com.ps.algorithm;

public class Search {
    public static void main(String[] args) {
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Linear search
        System.out.println("*** LINEAR SEARCH ***");
        System.out.println(linearSearch(array, 4));

        // Binary search
        System.out.println("*** BINARY SEARCH ***");
        System.out.println(binarySearch(array, 4));
        System.out.println(binarySearch2(array, 4, 0, array.length));
    }

    /**
     * 선형 검색 (Linear search)
     * 원하는 키 값을 갖는 요소를 만날 떄까지 맨 앞부터 순서대로 요소를 검색.
     * 시간 복잡도: O(n)
     */
    static int linearSearch (int[] array, int key) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == key) return i;

        return -1;
    }

    /**
     * 이진 검색 (Binary search)
     * 이미 정렬이 완료된 배열에서 검색 범위를 절반씩 좁혀가며 요소를 검색.
     * 시간 복잡도: O(log n)
     */
    static int binarySearch (int[] array, int key) {
        int pl = 0;
        int pr = array.length - 1;

        do {
            int pc = (pl + pr) / 2;
            if (array[pc] == key) return pc;
            else if (array[pc] < key) pl = pc + 1;
            else if (array[pc] > key) pr = pc - 1;
        } while (pl <= pr);

        return -1;
    }

    /**
     * 이진 검색을 재귀 형태로 구현
     */
    static int binarySearch2 (int[] array, int key, int pl, int pr) {
        if (pl >= pr) return -1;

        int pc = (pl + pr) / 2;

        if (array[pc] == key) return pc;
        else if (array[pc] < key) return binarySearch2(array, key, pc + 1, pr);
        else return binarySearch2(array, key, pl, pc - 1);
    }
}


