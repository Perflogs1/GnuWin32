 import java.util.*;
 class Pair
 {
     int jar1;
     int jar2;
     ArrayList<Pair> path;
     Pair(int jar1,int jar2)
     {
        this.jar1=jar1;
        this.jar2=jar2;
        path=new ArrayList<>();
     }
     Pair(int jar1,int jar2,ArrayList<Pair> path1)
     {
         this.jar1=jar1;
         this.jar2=jar2;
         path=new ArrayList<>();
         path.addAll(path1);
         path.add(new Pair(this.jar1,this.jar2));
     }
 }
 
 public class WaterJarDFS
 {
     public static void getpath(int jug1,int jug2,int target)
     {
         Stack<Pair> stack=new Stack<>();
         boolean visited[][]=new boolean[jug1+1][jug2+1];
         Pair initialState=new Pair(0,0);
         initialState.path.add(new Pair(0,0));
         stack.push(initialState);
         while(!stack.isEmpty())
         {
             Pair curr=stack.pop();
             if(curr.jar1>jug1 || curr.jar2>jug2 ||visited[curr.jar1][curr.jar2])
             {
                 continue;
             }
             visited[curr.jar1][curr.jar2]=true;
             if(curr.jar1==target || curr.jar2==target)
             {
                if(curr.jar1==target)
                {
                    curr.path.add(new Pair(curr.jar1,0));
                }
                else if(curr.jar2==target)
                {
                    curr.path.add(new Pair(0,curr.jar2));
                }
                System.out.println("Visited "+curr.jar1+" "+curr.jar2);
                int count=curr.path.size();
                for(int i=0;i<count;i++)
                {
                    System.out.println(curr.path.get(i).jar1+" "+curr.path.get(i).jar2);
                }
                return;
             }
             
             stack.push(new Pair(jug1,0,curr.path));
             stack.push(new Pair(0,jug2,curr.path));
             
             stack.push(new Pair(curr.jar1,0,curr.path));
             stack.push(new Pair(0,curr.jar2,curr.path));
             
             stack.push(new Pair(jug1,curr.jar2,curr.path));
             stack.push(new Pair(curr.jar1,jug2,curr.path));
             
             int emptyjug=jug2-curr.jar2;
             int minamount=Math.min(emptyjug,curr.jar1);
             int jar2=curr.jar2+minamount;
             int jar1=curr.jar1-minamount;
             stack.push(new Pair(jar1,jar2,curr.path));
             
              emptyjug=jug1-curr.jar1;
              minamount=Math.min(emptyjug,curr.jar2);
              jar2=curr.jar2-minamount;
             jar1=curr.jar1+minamount;
             stack.push(new Pair(jar1,jar2,curr.path));
         }
         System.out.println("NO GOAL SOLUTION REACHED ");
     }
     
     public static void main(String args[])
     {
         getpath(4,3,2);
     }
 }