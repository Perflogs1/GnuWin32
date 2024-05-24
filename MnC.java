import java.util.*;
class State
{
    int LM;
    int LC;
    int RM;
    int RC;
    int boat;
    String side;
    State parent;
    State(int LM,int LC,int RM,int RC,int boat,String side,State parent)
    {
        this.LM=LM;
        this.LC=LC;
        this.RM=RM;
        this.RC=RC;
        this.boat=boat;
        this.side=side;
        this.parent=parent;
    }
}

public class MnC
{
    public static void getpath(State s)
    {
        Stack<State> stack=new Stack<>();
        stack.push(s);
        while(s!=null)
        {
            stack.push(s);
            s=s.parent;
        }
        while(!stack.isEmpty())
        {
            State a=stack.pop();
            System.out.println("Left "+a.LM+" "+a.LC+" "+a.boat+" Right"+a.RM+" "+a.RC+" "+a.side);
        }
    }
    
    public static boolean checkState(State s)
    {
        if((s.LM>=0 && s.LC>=0 && s.RM>=0 && s.RC>=0)&&(s.LM==0 ||s.LM>=s.LC)&&(s.RM==0 || s.RM>=s.RC))
        {
            return true;
        }
        return false;
    }
    
    public static void DFS(Stack<State> stack,int visited[][][])
    {
        
        stack.push(new State(3,3,0,0,1,"left",null));
        while(!stack.isEmpty())
        {
            State currState=stack.pop();
            visited[currState.LM][currState.LC][currState.boat]=1;
            if(currState.LM==0 && currState.LC==0 && currState.boat==0)
            {
                System.out.println("Goal state reached "+currState.LM+" "+currState.LC+" "+currState.boat);
                getpath(currState);
                break;
            }
            if(currState.boat==1)
            {
                System.out.println("Going from left to right");
                System.out.println("Current State "+currState.LM+ " "+currState.LC+" "+currState.RM+" "+currState.RC+" "+currState.boat);
                for(int i=0;i<currState.LM;i++)
                {
                    for(int j=0;j<currState.LC;j++)
                    {
                        if((i+j)>0 && (i+j)<=2)
                        {
                            State newState=new State(currState.LM-i,currState.LC-j,currState.RM+i,currState.RC+j,0,"right",currState);
                            if(checkState(newState))
                            {
                                if(visited[newState.LM][newState.LC][newState.boat]!=1)
                                {
                                    stack.push(newState);
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                System.out.println("Going from right to left");
                System.out.println("Current State "+currState.LM+ " "+currState.LC+" "+currState.RM+" "+currState.RC+" "+currState.boat);
                for(int i=0;i<currState.RM;i++)
                {
                    for(int j=0;j<currState.RC;j++)
                    {
                        if((i+j)<=2 &&(i==0||j==0))
                        {
                            State newState=new State(currState.LM+i,currState.LC+j,currState.RM-i,currState.RC-j,1,"left",currState);
                            if(checkState(newState))
                            {
                                if(visited[newState.LM][newState.LC][newState.boat]!=1)
                                {
                                    stack.push(newState);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public static void main(String args[])
    {
        Stack<State> stack=new Stack<>();
        int visited[][][]=new int[4][4][2];
        DFS(stack,visited);
    }
}