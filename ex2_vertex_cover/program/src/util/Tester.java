package util;

import java.util.ArrayList;
import java.util.List;

import models.Solution;
public class Tester {

	public static void main(String[] args) {
		
		List<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		List<Integer> b = new ArrayList<Integer>();
		b.add(0);
		b.add(2);
		List<Integer> c = new ArrayList<Integer>();
		c.add(0);
		c.add(1);
		List<List<Integer>> adj = new ArrayList<List<Integer>>();
		adj.add(a);
		adj.add(b);
		adj.add(c);
		
		Solution s = new Solution(3, adj);
		
		// System.out.println(s.getAdjacencyList());
		System.out.println(s.getCost()+" "+s.getVertexCover()+" "+s.isValid());
		s.pickVertex(1);
		s.pickVertex(2);
		System.out.println("\n");
		System.out.println(s.getCost()+" "+s.getVertexCover()+" "+s.isValid());
		
		Solution sc = s.copy();
		System.out.println(sc.getCost()+" "+sc.getVertexCover()+" "+sc.isValid());
		
		s.pickVertex(0);
		System.out.println("\n");
		System.out.println(s.getCost()+" "+s.getVertexCover()+" "+s.isValid());
		System.out.println(sc.getCost()+" "+sc.getVertexCover()+" "+sc.isValid());
		
		/*
		Solution initialSolution = new Solution(8, null);
		System.out.println(initialSolution);
		*/
		
	}

}
