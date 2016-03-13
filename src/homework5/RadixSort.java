package homework5;

import java.util.Random;

public class RadixSort {

	public static void sort(int[] a) {
		int i, m = a[0], exp = 1, n = a.length;
		int[] b = new int[10];
		for (i = 1; i < n; i++)
			if (a[i] > m)
				m = a[i];
		while (m / exp > 0) {
			int[] bucket = new int[10];

			for (i = 0; i < n; i++)
				bucket[(a[i] / exp) % 10]++;
			for (i = 1; i < 10; i++)
				bucket[i] += bucket[i - 1];
			for (i = n - 1; i >= 0; i--)
				b[--bucket[(a[i] / exp) % 10]] = a[i];
			for (i = 0; i < n; i++)
				a[i] = b[i];
			exp *= 10;
		}
	}

	public static void main(String[] args) {
		int size = 10;
		Random random = new Random();

		int arr[] = new int[size];

		for (int i = 0; i < size; i++)
			arr[i] = random.nextInt(1000);

		sort(arr);

		System.out.println("sorted ");
		for (int i = 0; i < size; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
}
