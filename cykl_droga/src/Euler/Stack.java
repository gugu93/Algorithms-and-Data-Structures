
package Euler;

public class Stack {
    private Node root;
    
    Stack() 
    {
        root=null;
    }
    
    public void add(Node tmp) 
    {
        if(root == null) 
        {
            root=tmp;
        }
        else
        {
            tmp.nastepny=root;
            root=tmp;
        }
    }
    
    public Node wez() 
    {
        if (root == null) return null;
        else 
        {
            Node tmp=root;
            root=root.nastepny;
            return tmp;
        }
    }
    
    public Node root()
    {
        return root;
    }
    
    public void write()
    {
        Node tmp=root;
        while(tmp!=null)
        {
            System.out.print(((char)(65+tmp.wartosc))+",");
            tmp=tmp.nastepny;
        }
        System.out.println();
    }
    
    public void clear()
    {
        root=null;
    }

    public boolean search(int x)
    {
        if(root == null) return false;
        Node tmp=root;
        
        while(tmp != null)
        {
            if(tmp.wartosc == x) return true;
            tmp = tmp.nastepny;
        }
        return false;
    }

}
