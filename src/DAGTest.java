import static org.junit.Assert.*;

import org.junit.Test;

public class DAGTest {

	@Test
	public void testNull() {
		DAG tree = new Dag();
		int result = tree.findLCA(0, 3);
		assertEquals("Checking if the lowest common ancestor works for null:", result, -1);
	}

	//tests for valid vertices
	@Test(expected = IllegalArgumentException.class)
	public void testValidVertex(){
		DAG test = new DAG(3);

		test.addEdge(-1, 2);
		test.addEdge(1, -2);
		test.addEdge(-1, -2);
		assertEquals("Should contain no edges", 0, test.E());

		test.addEdge(1, 2);
		assertEquals("Should contain 1 edge", 1, test.E());
	}


	//test the count of vertices for some diff dags
	@Test
	public void testVertices(){
		DAG dag =new DAG(8);//create an dag graph
		dag.addEdge(0, 1);
		dag.addEdge(1, 2);
		dag.addEdge(2, 3);
		dag.addEdge(3, 4);
		dag.addEdge(4, 5);
		dag.addEdge(5, 6);
		dag.addEdge(6, 7);

		assertEquals("Number of vertices: ", 8, dag.V());
		dag =new DAG(8);
		dag.addEdge(0, 1);
		dag.addEdge(0, 2);
		dag.addEdge(1, 3);
		dag.addEdge(2, 4);
		dag.addEdge(3, 5);
		dag.addEdge(4, 6);
		dag.addEdge(5, 7);
		dag.addEdge(6, 7);
		dag.addEdge(7, 8);

		assertEquals("Number of vertices: ", 9, dag.V());
		dag =new DAG(8);
		dag.addEdge(0, 1);
		dag.addEdge(0, 2);
		dag.addEdge(1, 2);
		dag.addEdge(2, 4);
		dag.addEdge(4, 3);
		dag.addEdge(3, 1);
		dag.addEdge(3, 6);
		dag.addEdge(6, 8);
		dag.addEdge(7, 8);

		assertEquals("Number of verices: ", 9, dag.V());
	}

	//Testing the LCA method, will test for various problems that may arise
	@Test(expected = IllegalArgumentException.class) 
	public void testFindLCA(){

		DAG dag =new DAG(8);//create an dag graph
		dag.addEdge(0, 1);
		dag.addEdge(1, 2);
		dag.addEdge(2, 3);
		dag.addEdge(3, 4);
		dag.addEdge(4, 5);
		dag.addEdge(5, 6);
		dag.addEdge(6, 7);
		assertEquals("Its own ancestor", 3, dag.findLCA(2, 3));
		assertEquals("Can be its own ancestor", 3, dag.findLCA(3, 3));

		dag =new DAG(8);
		dag.addEdge(0, 1);
		dag.addEdge(0, 2);
		dag.addEdge(1, 3);
		dag.addEdge(2, 4);
		dag.addEdge(3, 5);
		dag.addEdge(4, 6);
		dag.addEdge(5, 7);
		dag.addEdge(6, 7);
		dag.addEdge(7, 8);
		assertEquals("Cyclic so it will throw an exception", 1, dag.findLCA(1, 4));

		dag =new DAG(8);
		dag.addEdge(0, 1);
		dag.addEdge(0, 2);
		dag.addEdge(1, 2);
		dag.addEdge(2, 4);
		dag.addEdge(4, 3);
		dag.addEdge(3, 1);
		dag.addEdge(3, 6);
		dag.addEdge(6, 8);
		dag.addEdge(7, 8);
		assertEquals("", 7, dag.findLCA(3, 4));
		assertEquals("", 7, dag.findLCA(1, 4));
		assertEquals("", 7, dag.findLCA(5, 2));


		assertEquals("", 5, dag.findLCA(1, 5));
		assertEquals("", 5, dag.findLCA(5, 1));

	}



}
