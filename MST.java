package apps;

import structures.*;
import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		PartialTreeList L=new PartialTreeList();
		for(int i=0;i<graph.vertices.length;i++){
			Vertex v=graph.vertices[i];
			PartialTree T=new PartialTree(v);
			for(Vertex.Neighbor nbr=v.neighbors;nbr!=null; nbr=nbr.next){
				MinHeap<PartialTree.Arc> P=T.getArcs();
				PartialTree.Arc arc=new PartialTree.Arc(v,nbr.vertex,nbr.weight);
				//System.out.println(arc);
				P.insert(arc);
			}
			//System.out.println(T);
			L.append(T);
		}
		return L;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		ArrayList<PartialTree.Arc> ans=new ArrayList<PartialTree.Arc>();
		while(ptlist.size()>1){
			PartialTree PTX=ptlist.remove();
			MinHeap<PartialTree.Arc> PQX=PTX.getArcs();
			PartialTree.Arc a=PQX.deleteMin();
			//System.out.println(a);
			String v1root=a.v1.getRoot().name,v2root=a.v2.getRoot().name;
			while(v1root==v2root){
				a=PQX.deleteMin();
				v1root=a.v1.getRoot().name;
				v2root=a.v2.getRoot().name;
				if(PQX.isEmpty()){
					break;
				}
			}
			ans.add(a);
			PartialTree PTY=ptlist.removeTreeContaining(a.v2);
			//System.out.println(PTY);
			PTX.merge(PTY);
			ptlist.append(PTX);
		}
		return ans;
	}
}
