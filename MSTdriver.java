package apps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import structures.*;
import apps.*;
import apps.PartialTree.Arc;


public class MSTdriver {

	public static void main(String[] args) {
		//Scanner input = new Scanner(System.in);
		//System.out.print("Enter graph: ");
		String filename = "test4.txt"; //input.nextLine();
		Graph g = null;
		try {
			g = new Graph(filename);
		} catch (IOException e) {
			System.out.println("bruh that aint no file");
		}
		//TEST PART 4
		PartialTreeList ptlist = MST.initialize(g);
		ArrayList<PartialTree.Arc> result = MST.execute(ptlist);
		System.out.println(result);

		//TEST PART 3
//		PartialTreeList ptlist = MST.initialize(g);
//		PartialTree p1;
//		p1 = ptlist.remove();
//		Arc arc1 = (Arc)p1.arcs.deleteMin();
//		p1 = ptlist.removeTreeContaining(arc1.v2);
//		PartialTreeList.print(ptlist);
		
	}

}
