import java.util.*;

public class ShortestBestFirstSearch
{
    static Stack<Integer> st=new Stack<>();
    public static class State implements Comparable<State>
    {
        int index;
        int heuristics;
        State parent;
        int cost;
        public State(int index,int heuristics,State parent,int cost)
        {
            this.index=index;
            this.heuristics=heuristics;
            this.parent=parent;
            this.cost=cost;
        }
        @Override
        public int compareTo(State o)
        {
            return this.heuristics-o.heuristics;
        }
    }
    
    public static int dfs(int adjMatrix[][],int s,int d,HashMap<Integer,Integer> map)
    {
        PriorityQueue<State> q=new PriorityQueue<>();
        HashSet<Integer> set=new HashSet<>();
        int h=map.get(s);
        q.add(new State(s,h,null,0));
        int path=0;
        while(!q.isEmpty())
        {
            int count=q.size();
            for(int i=0;i<count;i++)
            {
                State currState=q.poll();
                int curridx=currState.index;
                if(curridx==d)
                {
                    System.out.println("Goal solution reached");
                    State temp=currState;
                    while(temp!=null)
                    {
                        st.push(temp.index);
                        temp=temp.parent;
                    }
                    int n=st.size();
                    System.out.println("Stack size "+n);
                    System.out.println("Route is ");
                    while(!st.isEmpty())
                    {
                        System.out.print(st.pop()+" ");
                    }
                    return currState.cost;
                }
                
                if(set.contains(curridx))
                {
                    continue;
                }
                set.add(curridx);
                for(int j=0;j<adjMatrix.length;j++)
                {
                    if(adjMatrix[curridx][j]!=0 && adjMatrix[curridx][j]!=9999)
                    {
                        int heu=map.get(j);
                        q.add(new State(j,heu,currState,currState.cost+adjMatrix[curridx][j]));
                    }
                }
            }
        }
        return path;
    }
    
    public static void main(String args[])
    {
        int adjMatrix[][]= {
				{0,75,9999,140,9999,9999,9999,9999},
				{75,0,71,9999,9999,9999,9999,9999},
				{9999,71,0,151,9999,9999,9999,9999},
				{140,9999,151,0,80,99,9999,9999},
				{9999,9999,9999,80,0,9999,97,9999},
				{9999,9999,9999,99,9999,0,9999,211},
				{9999,9999,9999,9999,97,9999,0,101},
				{9999,9999,9999,9999,9999,211,101,0}
		};
		//Source - 0, Destination - 7
		HashMap<Integer,Integer> map = new HashMap<>();
		//Heuristics(stright line distances from each city to 7)
		map.put(0, 366);
		map.put(1, 374);
		map.put(2, 380);
		map.put(3, 253);
		map.put(4, 193);
		map.put(5, 178);
		map.put(6, 98);
		map.put(7, 0);
		int cost = dfs(adjMatrix,0,7,map);
		System.out.println();
		System.out.println("Path cost is "+cost);
    }
}