package com.ps.algorithm;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sort {

    public static void main (String[] args) {
        Sort sort = new Sort();
        int[] arr = {8, 1, 4, 2, 7, 6, 3, 5};

        System.out.println("정렬 전: " + Arrays.toString(arr));

        /*
        // Bubble sort (가장 큰 값부터 정렬)
        System.out.println("\n *** Bubble Sort *** ");
        System.out.println("정렬 완료: " + Arrays.toString(sort.bubbleSort(arr)));

        // Bubble sort (가장 작은 값부터 정렬)
        System.out.println("\n *** Bubble Sort 2 ***");
        System.out.println("정렬 완료: " + Arrays.toString(sort.bubbleSort2(arr)));

        // Straight selection sort
        System.out.println("\n *** Straight Selection Sort ***");
        System.out.println("정렬 완료: " + Arrays.toString(sort.straightSelectionSort(arr)));

        // Straight insertion sort
        System.out.println("\n *** Straight Insertion Sort ***");
        System.out.println("정렬 완료 : " + Arrays.toString(sort.straightInsertionSort(arr)));
         */

        // Shell sort
        System.out.println("\n *** Shell Sort ***");
        System.out.println("정렬 완료 : " + Arrays.toString(sort.shellSort(arr)));

        // Shell sort (Knuth 간격 수열 사용)
        System.out.println("\n *** Shell Sort (using Knuth sequences) ***");
        System.out.println("정렬 완료 : " + Arrays.toString(sort.shellSortKnuth(arr)));

        // Shell sort (Pratt 간격 수열 사용)
        System.out.println("\n *** Shell Sort (using Pratt sequences) ***");
        System.out.println(Arrays.toString(sort.shellSortPratt(arr)));
    }

    public void printArr (int[] arr, int pointIdx) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            boolean point = pointIdx == i;
            System.out.print((point ? "(" : "") + arr[i] + (point ? ")" : ""));
            if (i + 1 < arr.length) System.out.print(", ");
        }
        System.out.println("]");
    }

    /**
     * 버블 정렬 (Bubble sort)
     * 배열에서 이웃한 두 요소의 대소관계를 비교하여 교환을 반복하는 정렬
     * 시간 복잡도 : O(n²)
     */
    public int[] bubbleSort (int[] unsortedArr) {
        // 정렬 전 배열 (unsortedArr) 을 바꾸지 않기 위해 Arrays.copyOf 사용
        int[] arr = Arrays.copyOf(unsortedArr, unsortedArr.length);
        int n = arr.length - 1;
        boolean swapped;

        for (int i = 0; i < n; i++) {
            swapped = false;
            // 한 사이클이 끝나면 맨 끝 요소가 정렬되므로 n - i 번 반복
            // (정렬되지 않은 부분만 반복)
            for (int j = 0; j < n - i; j++) {
                // 요소가 뒷 요소보다 크다면 두 요소를 교환
                if (arr[j] > arr[j + 1]) {
                    printArr(arr, j);
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // 만약 요소가 교환되지 않았다면, 배열이 이미 정렬된 상태이므로 return
            if (!swapped) break;
        }
        return arr;
    }

    /**
     * 가장 작은 값부터 정렬하는 버블 정렬
     */
    public int[] bubbleSort2 (int[] unsortedArr) {
        int[] arr = Arrays.copyOf(unsortedArr, unsortedArr.length);
        int n = arr.length - 1;
        boolean swapped;

        for (int i = 0; i < n; i++) {
            swapped = false;
            for (int j = n; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    printArr(arr, j);
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return arr;
    }

    /**
     * 단순 선택 정렬 (Straight selection sort)
     * 가장 작은 요소부터 선택해 정렬되지 않은 부분의 가장 앞쪽으로 옮겨서 순서대로 정렬하는 알고리즘
     * 시간 복잡도 : O(n²)
     */
    public int[] straightSelectionSort (int[] unsortedArr) {
        int[] arr = Arrays.copyOf(unsortedArr, unsortedArr.length);

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[min]) min = j;
            printArr(arr, min);
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return arr;
    }

    /**
     * 단순 삽입 정렬 (Straight insertion sort)
     * 정렬되지 않은 부분의 첫 번째 요소를 정렬된 부분의 '알맞은 위치' 로 옮기는 작업을 반복하며 정렬
     * 시간 복잡도 : O(n²)
     */
    public int[] straightInsertionSort (int[] unsortedArr) {
        int[] arr = Arrays.copyOf(unsortedArr, unsortedArr.length);
        int i, j;

        for (i = 0; i < arr.length; i++) {
            int temp = arr[i];
            // 정렬되지 않은 부분 (i ~ arr.length) 의 첫 번째 요소 (arr[i]) 보다 작거나 같은 값이 나올 때까지
            // 정렬된 부분 (0 ~ i) 의 요소를 한 칸씩 앞으로 배치
            for (j = i; j > 0 && arr[j - 1] > temp; j--)
                arr[j] = arr[j - 1];
            printArr(arr, j);
            // 알맞은 위치에 정렬되지 않은 부분의 첫번째 요소 배치
            arr[j] = temp;
        }
        return arr;
    }

    /**
     * 셸 정렬 (Shell sort)
     * 정렬할 배열의 요소를 그룹으로 나눠 각 그룹별로 단순 삽입 정렬을 수행하고 그룹을 다시 나누면서 정렬을 반복
     * (h개 요소에 대해 h-정렬을 반복)
     * e.g. 배열의 크기가 8일 때, 4-정렬, 2-정렬, 1-정렬을 반복하며 정렬함
     * 시간 복잡도 : O(n²)
     */
    public int[] shellSort (int[] unsortedArr) {
        int n = unsortedArr.length;

        // h-정렬을 수행하기 위한 간격 수열 생성
        // e.g. n = 8, [4, 2, 1]
        int h = n / 2;
        ArrayList<Integer> sequence = new ArrayList<>();
        while (h > 0) {
            sequence.add(h);
            h /= 2;
        }

        // 셸 정렬 수행
        return doShellSort(unsortedArr, sequence.stream().mapToInt(i -> i).toArray());
    }

    /**
     * 셸 정렬 수행하는 함수
     */
    public int[] doShellSort (int[] unsortedArr, int[] sequence) {
        int n = unsortedArr.length;
        int[] arr = Arrays.copyOf(unsortedArr, n);

        // 간격 수열에 따라 반복
        for (int h : sequence) {
            System.out.println(h + "-정렬");
            // 각 부분 배열에서 삽입 정렬 수행
            for (int i = h; i < n; i++) {
                int temp = arr[i];
                int j;
                System.out.println("삽입할 요소 (arr[" + i + "]) :" + temp);
                for (j = i; j >= h && arr[j - h] > temp; j -= h) {
                    // arr[i] 보다 큰 요소를 오른쪽으로 이동시킴
                    arr[j] = arr[j - h];
                    printArr(arr, j);
                }
                // arr[i] 를 부분 배열의 적절한 위치에 삽입함
                arr[j] = temp;
                printArr(arr, j);
            }
        }

        return arr;
    }


    /**
     * Knuth 간격 수열을 사용하는 셸 정렬
     *
     * 기존 셸 정렬의 경우, n 이 배열의 크기이고 h 가 간격이라고 한다면 [h = n / 2^k] 와 같은 수열을 이용하여 정렬한다. (k는 1부터 시작하는 정수)
     * 이 때 알고리즘의 평균 시간 복잡도는 O(n²) 이다.
     * 셸 정렬에서 어떤 간격 수열을 사용하는지에 따라 알고리즘의 성능을 향상시킬 수 있다.
     *
     * Knuth 간격 수열은 [h = 3k + 1] 와 같은 규칙을 따른다. (k는 0부터 시작하는 정수) h값이 서로 배수가 되지 않는 수열이다.
     * (h = ..., 121, 40, 13, 4, 1)
     *
     * 시간 복잡도 : O(n^3/2)
     */
    public int[] shellSortKnuth (int[] unsortedArr) {
        ArrayList<Integer> sequence = new ArrayList<>();
        int h = 1;

        // Knuth 간격 수열 생성 [h = 3k + 1]
        while (h < unsortedArr.length) {
            sequence.add(h);
            h = 3 * h + 1;
        }
        Collections.reverse(sequence);

        // 셸 정렬 수행
        return doShellSort(unsortedArr, sequence.stream().mapToInt(i -> i).toArray());
    }

    /**
     * Pratt 간격 수열을 사용하는 셸 정렬
     *
     * 셸 정렬의 간격 수열 중 평균 시간 복잡도가 가장 적은 간격 수열이다.
     * Pratt 간격 수열은 [h = 2^i * 3^j] 와 같은 규칙을 따른다. (i, j 는 0 이상의 정수)
     * 즉, Pratt 간격 수열은 2와 3의 거듭제곱의 곱으로 이루어진다.
     *
     * 시간 복잡도 : O(n \ log² n)
     */
    public int[] shellSortPratt (int[] unsortedArr) {
        ArrayList<Integer> sequence = new ArrayList<>();
        int n = unsortedArr.length;
        int h = 1;

        // Pratt 간격 수열 생성 [h = 2^i * 3^j]
        while (h <= n) {
            int q = h;
            while (q <= n) {
                sequence.add(q);
                q *= 3;
            }
            h *= 2;
        }
        sequence.sort(Collections.reverseOrder());

        // 셸 정렬 수행
        return doShellSort(unsortedArr, sequence.stream().mapToInt(Integer::intValue).toArray());
    }
}
