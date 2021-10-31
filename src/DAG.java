public class DAG{
	int vertices; // number of vertices
	int edges; // number of edges
	private int[][] adj; //adjacency list for a vertex
	private int[] outdegree;// outdegree of a vertex 
	private int[] indegree; // indegree of a vertex 
	private int[] visited;  // visited vertices

	// initialise the graph with v number of vertices
	public DAG(int V){
		if(V<0){
			throw new IllegalArgumentException("Number of vertices in the DAG must be greater than 0.");
		}
		else{
			this.vertices = V;
			this.edges = 0;
			indegree = new int[V];
			outdegree = new int[V];
			visited = new int[V];
			adj = new int[V][V];
			
			// empty graph setup
			for(int i = 0; i<V; i++){
				for(int j=0;j<V;j++){
					adj[i][j] = 0;
				}
			}
		}
	}
	
	//throws illegal exception if the vertex put in, is out of bounds
	private void validateVertex(int v){
		if((v  <0) || (v >= vertices)){
			throw new IllegalArgumentException("Edgesless than one means no verices are joined, and if there is a cycle it cannot be moved");
		}
	}

	//adds directed edge from v to w
	public void addEdge(int v, int w){
		validateVertex(v);
		validateVertex(w);
		adj[v][w]=1;
		indegree[w]++;
		outdegree[v]++;
		edges++;
	}

	//Removes an edge from v to w
	public void removeEdge(int v, int w){
		validateVertex(v);
		validateVertex(w);
		adj[v][w]=0;
		indegree[w]--;
		outdegree[v]--;
		edges--;
	}

	//returns the number of directed edges out of vertex v
	public int outdegree(int v){
		validateVertex(v);
		return outdegree[v];
	}

	//returns the number of directed edges into vertex v
	public int indegree(int v){
		validateVertex(v);
		return indegree[v];
	}

	//returns the vertices adjacent to v
	public int[] adj(int v){
		validateVertex(v);
		int[] temp = new int[outdegree[v]];
		int count =0;
		for(int i=0; i < vertices;i++){
			if(adj[v][i]==1){
				temp[count]=i;
				count++;
			}
		}
		return temp;
	}

	// if there is a cycle it will return true otherwise false
	public boolean hasCycle(){
		boolean hasCycle=false;
		int count = 0;
		for(int i =0; i < vertices;i++){
			visited[count]=i;
			for(int j = 0; j < vertices;j++){
				for(int k=0; k < vertices;k++){
					if(visited[k]==j && adj[i][j]==1){
						hasCycle=true;
						return hasCycle;
					}
				}	
			}
			count++;
		}
		return hasCycle;
	}


	// finding lowest common ancestor in a directed acyclic graph
	public int findLCA(int v, int w){
		validateVertex(v);
		validateVertex(w);
		hasCycle();
		// as long as there are edges and it does not have a cycle
		if(edges > 0 && !hasCycle()){
			return LCAUtil(v,w);
		}
		else{
			throw new IllegalArgumentException("This graph is not acyclical");
		}
	}

	//helper function for LCA
	private int LCAUtil(int v, int w){
		int[] vertices1 = new int[edges];
		int[] vertices2= new int[edges];
		boolean[] vMarked = new boolean[vertices];
		boolean[] wMarked = new boolean[vertices];
		int vCount =0;
		int wCount = 0;
		vertices1[vCount]=v;
		vertices2[wCount]=w;
		
		// marking vertices as not visited
		for(int j=0; j < vertices;j++){
			vMarked[j]=false;
			wMarked[j]=false;
		}
		
		for(int i =0; i < vertices;i++){
			vMarked[v] =true;
			wMarked[w] =true;
			for(int j = 0; j < vertices;j++){
				if(adj[i][j]==1 && vMarked[i]){
					vCount++;
					vertices1[vCount]=j;
					vMarked[j]=true;
				}
				if(adj[i][j]==1 && wMarked[i]){
					wCount++;
					vertices2[wCount]=j;
					wMarked[j]=true;
				}
				if(vertices2[wCount]==vertices1[vCount]){
					return vertices2[wCount];
				}
			}
		}
		return -1;//returns -1 if no ancestor found
	}  
}