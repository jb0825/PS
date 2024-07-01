package com.ps.algorithm;

import java.util.Arrays;

public class Sort {

    public static void main (String[] args) {
        Sort sort = new Sort();
        int[] arr = {9, 33, 12, 123, 3, 3, 4, 7, 1};

        System.out.println("정렬 전: " + Arrays.toString(arr));

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
}
