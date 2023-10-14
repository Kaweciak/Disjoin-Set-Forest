package dsaa.lab09;

public class DisjointSetLinkedList implements DisjointSetDataStructure {

	private class Element{
		int representant;
		int next;
		int length;
		int last;
	}
	
	private static final int NULL=-1;
	
	Element arr[];
	
	public DisjointSetLinkedList(int size) {
		arr = new Element[size];
	}


	@Override
	public void makeSet(int item) {
		Element newSet = new Element();
		arr[item] = newSet;
		newSet.representant = item;
		newSet.next = NULL;
		newSet.length = 1;
		newSet.last = item;
	}

	@Override
	public int findSet(int item) {
		if(item >= arr.length || item < 0) return NULL;
		return arr[item].representant;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		int setA = findSet(itemA), setB = findSet(itemB);
		if(setA == NULL || setB == NULL || setA == setB) return false;
		if(arr[setA].length < arr[setB].length)
		{
			int temp = setA;
			setA = setB;
			setB = temp;
		}
		arr[arr[setA].last].next = setB;
		arr[setA].last = arr[setB].last;
		int lengthB = arr[setB].length;
		int curr = setB;
		arr[setA].length += lengthB;
		for(int i = 0; i < lengthB; i++)
		{
			arr[curr].representant = setA;
			curr = arr[curr].next;
		}
		return true;
	}

	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder("Disjoint sets as linked list:");
		int current;
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i].representant == i)
			{
				res.append("\n");
				current = i;
				while(arr[current].next != NULL)
				{
					res.append(current).append(", ");
					current = arr[current].next;
				}
				res.append(current);
			}
		}
		return res.toString();
	}

}
