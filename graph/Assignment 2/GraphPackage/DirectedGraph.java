package GraphPackage;

import java.util.Iterator;
import java.util.Random;

import ADTPackage.*; // Classes that implement various ADTs

/**
 * A class that implements the ADT directed graph.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.1
 */
public class DirectedGraph<T> implements GraphInterface<T> {
	private DictionaryInterface<T, VertexInterface<T>> vertices;
	private int edgeCount;

	public DirectedGraph() {
		vertices = new UnsortedLinkedDictionary<>();
		edgeCount = 0;
	} // end default constructor

	public boolean addVertex(T vertexLabel) {
		VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
		return addOutcome == null; // Was addition to dictionary successful?
	} // end addVertex

	public boolean addEdge(T begin, T end, double edgeWeight) {
		boolean result = false;
		VertexInterface<T> beginVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);
		if ((beginVertex != null) && (endVertex != null))
			result = beginVertex.connect(endVertex, edgeWeight);
		if (result)
			edgeCount++;
		return result;
	} // end addEdge

	public boolean addEdge(T begin, T end) {
		return addEdge(begin, end, 0);
	} // end addEdge

	public boolean hasEdge(T begin, T end) {
		boolean found = false;
		VertexInterface<T> beginVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);
		if ((beginVertex != null) && (endVertex != null)) {
			Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
			while (!found && neighbors.hasNext()) {
				VertexInterface<T> nextNeighbor = neighbors.next();
				if (endVertex.equals(nextNeighbor))
					found = true;
			} // end while
		} // end if

		return found;
	} // end hasEdge

	public boolean isEmpty() {
		return vertices.isEmpty();
	} // end isEmpty

	public void clear() {
		vertices.clear();
		edgeCount = 0;
	} // end clear

	public int getNumberOfVertices() {
		return vertices.getSize();
	} // end getNumberOfVertices

	public int getNumberOfEdges() {
		return edgeCount;
	} // end getNumberOfEdges

	protected void resetVertices() {
		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
		while (vertexIterator.hasNext()) {
			VertexInterface<T> nextVertex = vertexIterator.next();
			nextVertex.unvisit();
			nextVertex.setCost(0);
			nextVertex.setPredecessor(null);
		} // end while
	} // end resetVertices

	public StackInterface<T> getTopologicalOrder() {
		resetVertices();

		StackInterface<T> vertexStack = new LinkedStack<>();
		int numberOfVertices = getNumberOfVertices();
		for (int counter = 1; counter <= numberOfVertices; counter++) {
			VertexInterface<T> nextVertex = findTerminal();
			nextVertex.visit();
			vertexStack.push(nextVertex.getLabel());
		} // end for

		return vertexStack;
	} // end getTopologicalOrder

	// ###########################ALGORITMS START
	// HERE############################################
	public QueueInterface<T> getBreadthFirstTraversal(T origin, T end) {
		VertexInterface<T> originVertex = vertices.getValue(origin);
		VertexInterface<T> endVertex = vertices.getValue(end);

		boolean found = false;

		QueueInterface<T> traversalOrder = new LinkedQueue<>();
		QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();

		originVertex.visit();
		traversalOrder.enqueue(origin);
		vertexQueue.enqueue(originVertex);
		VertexInterface<T> frontVertex = null, nextNeighbour = null;
		while (!vertexQueue.isEmpty()) {
			frontVertex = vertexQueue.dequeue();

			while (frontVertex.hasNeighbor()) {
				nextNeighbour = frontVertex.getUnvisitedNeighbor();
				if (nextNeighbour == null)
					break;

				nextNeighbour.visit();
				traversalOrder.enqueue(nextNeighbour.getLabel());
				vertexQueue.enqueue(nextNeighbour);

				if (nextNeighbour == endVertex) {
					found = true;
					break;
				}
			}
			if (found)
				break;
		}

		resetVertices();
		return traversalOrder;
	}

	public QueueInterface<T> getDepthFirstTraversal(T origin, T end) {
		QueueInterface<T> traversalOrder = new LinkedQueue<>();
		StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();

		VertexInterface<T> originVertex = vertices.getValue(origin);
		VertexInterface<T> endVertex = vertices.getValue(end);

		originVertex.visit();
		traversalOrder.enqueue(origin);
		vertexStack.push(originVertex);

		VertexInterface<T> topVertex = null, nextNeighbour = null;
		while (!vertexStack.isEmpty()) {
			topVertex = vertexStack.peek();
			nextNeighbour = topVertex.getUnvisitedNeighbor();
			if (nextNeighbour == null)
				vertexStack.pop();
			else {
				nextNeighbour.visit();
				traversalOrder.enqueue(nextNeighbour.getLabel());
				vertexStack.push(nextNeighbour);
			}

			if (endVertex == nextNeighbour)
				break;
		}

		resetVertices();
		return traversalOrder;

	}

	public int getShortestPath(T begin, T end, StackInterface<T> path) {
		boolean finnished = false;
		int pathLength = 1;
		QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
		VertexInterface<T> beginVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);
		beginVertex.visit();
		vertexQueue.enqueue(beginVertex);

		VertexInterface<T> frontVertex = null, nextNeighbour = null;
		while (!finnished && !vertexQueue.isEmpty()) {
			frontVertex = vertexQueue.dequeue();
			while (!finnished && frontVertex.hasNeighbor()) {
				nextNeighbour = frontVertex.getUnvisitedNeighbor();
				if (nextNeighbour == null)
					break;
				if (nextNeighbour != null) {
					nextNeighbour.visit();
					nextNeighbour.setPredecessor(frontVertex);
					vertexQueue.enqueue(nextNeighbour);
				}

				if (nextNeighbour == endVertex)
					finnished = true;
			}
		}

		path.push(end);

		VertexInterface<T> tempVertex = endVertex;
		while (tempVertex.hasPredecessor()) {
			tempVertex = tempVertex.getPredecessor();
			path.push(tempVertex.getLabel());
			pathLength++;
		}
		resetVertices();
		return pathLength;

	}

	public double getCheapestPath(T begin, T end, StackInterface<T> path) {
		boolean finnished = false;
		VertexInterface<T> beginVertex = vertices.getValue(begin), endVertex = vertices.getValue(end);
		int pathCost = 0;
		PriorityQueueInterface<EntryPQ> priorityQueue = new HeapPriorityQueue<>();
		priorityQueue.add(new EntryPQ(beginVertex, 0, null));

		EntryPQ frontEntry = null;
		VertexInterface<T> frontVertex = null, nextNeighbour = null;
		while (!finnished && !priorityQueue.isEmpty()) {
			frontEntry = priorityQueue.remove();
			frontVertex = frontEntry.getVertex();

			if (!frontVertex.isVisited()) {
				frontVertex.visit();
				pathCost += frontVertex.getCost();
				frontVertex.setPredecessor(frontEntry.getPredecessor());

				if (frontVertex == endVertex)
					finnished = true;

				else {
					if (frontVertex.hasNeighbor()) {
						Iterator<VertexInterface<T>> iterator = frontVertex.getNeighborIterator();
						while (iterator.hasNext()) {
							nextNeighbour = iterator.next();

							if (!nextNeighbour.isVisited())
								priorityQueue
										.add(new EntryPQ(nextNeighbour,
												(nextNeighbour.getCost() + frontEntry.getCost()),
												frontVertex));

						}
					}
				}
			}
		}

		path.push(end);

		VertexInterface<T> tempVertex = endVertex;
		while (tempVertex.hasPredecessor()) {
			tempVertex = tempVertex.getPredecessor();
			path.push(tempVertex.getLabel());
		}

		resetVertices();
		return pathCost;

	}
	// ##################ALGORITHMS END HERE######################

	protected VertexInterface<T> findTerminal() {
		boolean found = false;
		VertexInterface<T> result = null;

		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();

		while (!found && vertexIterator.hasNext()) {
			VertexInterface<T> nextVertex = vertexIterator.next();

			// If nextVertex is unvisited AND has only visited neighbors)
			if (!nextVertex.isVisited()) {
				if (nextVertex.getUnvisitedNeighbor() == null) {
					found = true;
					result = nextVertex;
				} // end if
			} // end if
		} // end while

		return result;
	} // end findTerminal

	// Used for testing
	public void displayEdges() {
		System.out.println("\nEdges exist from the first vertex in each line to the other vertices in the line.");
		System.out.println("(Edge weights are given; weights are zero for unweighted graphs):\n");
		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
		while (vertexIterator.hasNext()) {
			((Vertex<T>) (vertexIterator.next())).display();
		} // end while
	} // end displayEdges

	private class EntryPQ implements Comparable<EntryPQ> {
		private VertexInterface<T> vertex;
		private VertexInterface<T> previousVertex;
		private double cost; // cost to nextVertex

		private EntryPQ(VertexInterface<T> vertex, double cost, VertexInterface<T> previousVertex) {
			this.vertex = vertex;
			this.previousVertex = previousVertex;
			this.cost = cost;
		} // end constructor

		public VertexInterface<T> getVertex() {
			return vertex;
		} // end getVertex

		public VertexInterface<T> getPredecessor() {
			return previousVertex;
		} // end getPredecessor

		public double getCost() {
			return cost;
		} // end getCost

		public int compareTo(EntryPQ otherEntry) {
			// Using opposite of reality since our priority queue uses a maxHeap;
			// could revise using a minheap
			return (int) Math.signum(otherEntry.cost - cost);
		} // end compareTo

		public String toString() {
			return vertex.toString() + " " + cost;
		} // end toString
	} // end EntryPQ

	// ########################################################################################
	// My Functions
	public VertexInterface<T> getVertex(T label) {
		return vertices.getValue(label);
	}

	public boolean contains(T label) {
		return vertices.contains(label);
	}

	public Iterator<VertexInterface<T>> Iterator() {
		Iterator<VertexInterface<T>> iterator = vertices.getValueIterator();
		return iterator;
	}

	public int[][] getAdjencyMatrix() {
		int[][] matrix = new int[vertices.getSize()][vertices.getSize()];
		Iterator<VertexInterface<T>> row = vertices.getValueIterator(), column = null;

		int rowIndex = 0, columnIndex = 0, hasEdge = 0;
		T rowLabel = null;
		while (row.hasNext()) {
			column = vertices.getValueIterator();
			rowLabel = row.next().getLabel();
			while (column.hasNext()) {
				hasEdge = 0;
				if (hasEdge(rowLabel, column.next().getLabel()))
					hasEdge = 1;

				matrix[rowIndex][columnIndex] = hasEdge;
				columnIndex++;
			}
			columnIndex = 0;
			rowIndex++;
		}

		return matrix;
	}

	public void printAdjencyMatrix() {
		int[][] matrix = getAdjencyMatrix();
		Iterator<VertexInterface<T>> row = vertices.getValueIterator();

		System.out.println("\nAdjency Matrix\n");
		System.out.print("|" + String.format("%5s", " "));
		while (row.hasNext()) {
			System.out.print("|" + String.format("%5s", row.next().toString()));
		}
		System.out.println("|");

		row = vertices.getValueIterator();
		for (int i = 0; i < matrix.length; i++) {
			System.out.print("|" + String.format("%5s", row.next().toString()));
			for (int j = 0; j < matrix[i].length; j++) {
				int value = matrix[i][j];
				if (value == 0)
					System.out.print("|" + String.format("%5s", " "));
				else
					System.out.print("|" + String.format("%5s", matrix[i][j]));
			}

			System.out.println("|");
		}
	}

	public void addCost() {
		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
		Random rnd = new Random();
		while (vertexIterator.hasNext()) {
			VertexInterface<T> vertex = vertexIterator.next();
			vertex.setCost(rnd.nextInt(5));
		}
	}
	// ##########################END OF MY FUNCTİONS##############################
}
// end DirectedGraph
