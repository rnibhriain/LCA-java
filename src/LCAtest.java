import static org.junit.Assert.*;

import org.junit.Test;

public class LCAtest {


	// tests if the common ancestor returned is null when there is no input
	@Test
	public void testNull() {
		LCA tree = new LCA();
		int result = tree.findLCA(0, 3);
		assertEquals("Checking if the lowest common ancestor works for null:", result, -1);
	}

	// tests a basic tree
	@Test
	public void testBasic() {
		LCA tree = new LCA();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        
        int result = tree.findLCA(4, 5);
		assertEquals("Checking if the lowest common ancestor works for 4 & 5:", result, 2);
		
		result = tree.findLCA(4, 6);
		assertEquals("Checking if the lowest common ancestor works for 4 & 6:", result, 1);
		
		result = tree.findLCA(3, 4);
		assertEquals("Checking if the lowest common ancestor works for 3 & 4:", result, 1);
		
		result = tree.findLCA(2, 4);
		assertEquals("Checking if the lowest common ancestor works for 2 & 4:", result, 2);
		
		result = tree.findLCA(6, 7);
		assertEquals("Checking if the lowest common ancestor works for 6 & 7:", result, 3);
	}

}
