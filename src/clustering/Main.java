package clustering;

import java.awt.Point;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Point> list = new ArrayList<Point>();
		ArrayList<Point> cluster1 = new ArrayList<Point>();
		ArrayList<Point> cluster2 = new ArrayList<Point>();

		list.add(new Point(4, 5));
		list.add(new Point(10, 5));
		list.add(new Point(25, 4));
		list.add(new Point(65, 74));
		list.add(new Point(8, 22));
		list.add(new Point(55, 14));
		list.add(new Point(23, 41));
		list.add(new Point(8, 6));
		list.add(new Point(29, 75));
		list.add(new Point(35, 24));
		list.add(new Point(66, 44));
		list.add(new Point(42, 84));
		list.add(new Point(78, 33));
		list.add(new Point(81, 65));
		list.add(new Point(9, 17));

		// put first in list to cluster1
		cluster1.add(list.get(0));
		list.remove(0);
		addFurthestPointToOtherCluster(list, cluster1, cluster2);
		// CAN ONLY DO ONE AT A TIME BECAUSE I DON'T REFILL THE LIST
		// singleLinkage(list, cluster1, cluster2);
		// completeLinkage(list, cluster1, cluster2);
		averageLinkage(list, cluster1, cluster2);

		System.out.println("Cluster1: " + cluster1.size());
		for (Point p : cluster1) {
			System.out.println(p);
		}
		System.out.println("Cluster2: " + cluster2.size());
		for (Point p : cluster2) {
			System.out.println(p);
		}
	}

	private static void averageLinkage(ArrayList<Point> list, ArrayList<Point> cluster1, ArrayList<Point> cluster2) {
		// Average: collect total distance, divide by number of points put it in
		// lower
		while (!list.isEmpty()) {
			Point a = list.get(0);
			double average1 = 0;
			double average2 = 0;
			for (int i = 0; i < cluster1.size(); i++) {
				double distance = a.distance(cluster1.get(i));
				average1 += distance;
			}
			for (int i = 0; i < cluster2.size(); i++) {
				double distance = a.distance(cluster2.get(i));
				average2 += distance;
			}
			if (average1 / cluster1.size() < average2 / cluster2.size()) {
				cluster1.add(a);
			} else {
				cluster2.add(a);
			}
			list.remove(a);
		}
	}

	private static void completeLinkage(ArrayList<Point> list, ArrayList<Point> cluster1, ArrayList<Point> cluster2) {
		// opposite of single- go by farthest
		while (!list.isEmpty()) {
			Point a = list.get(0);
			double far1 = 0;
			double far2 = 0;
			for (int i = 0; i < cluster1.size(); i++) {
				double distance = a.distance(cluster1.get(i));
				if (i == 0) {
					far1 = distance;
				} else if (distance > far1) {
					far1 = distance;
				}
			}
			for (int i = 0; i < cluster2.size(); i++) {
				double distance = a.distance(cluster2.get(i));
				if (i == 0) {
					far2 = distance;
				} else if (distance > far2) {
					far2 = distance;
				}
			}
			if (far1 > far2) {
				cluster1.add(a);
			} else {
				cluster2.add(a);
			}
			list.remove(a);
		}

	}

	private static void addFurthestPointToOtherCluster(ArrayList<Point> list, ArrayList<Point> cluster1,
			ArrayList<Point> cluster2) {
		/*
		 * compare only point in cluster1 to every point in list using point's
		 * distance formula. Put farthest away into clusters
		 */
		double far = 0;
		int farIndex = 0;
		Point a = cluster1.get(0);
		for (int i = 0; i < list.size(); i++) {
			double distance = list.get(i).distance(a);
			if (i == 0) {
				far = distance;
			} else if (distance > far) {
				far = distance;
				farIndex = i;
			}
		}
		cluster2.add(list.get(farIndex));
		list.remove(list.get(farIndex));
	}

	private static void singleLinkage(ArrayList<Point> list, ArrayList<Point> cluster1, ArrayList<Point> cluster2) {
		/*
		 * single- go to next in list, check how distance compares to whatever
		 * is in each cluster. store smallest distance for each cluster. put
		 * point in the cluster that has lower distance
		 */

		while (!list.isEmpty()) {
			Point a = list.get(0);
			double close1 = 0;
			double close2 = 0;
			for (int i = 0; i < cluster1.size(); i++) {
				double distance = a.distance(cluster1.get(i));
				if (i == 0) {
					close1 = distance;
				} else if (distance < close1) {
					close1 = distance;
				}
			}
			for (int i = 0; i < cluster2.size(); i++) {
				double distance = a.distance(cluster2.get(i));
				if (i == 0) {
					close2 = distance;
				} else if (distance < close2) {
					close2 = distance;
				}
			}
			if (close1 < close2) {
				cluster1.add(a);
			} else {
				cluster2.add(a);
			}
			list.remove(a);
		}

	}
}
