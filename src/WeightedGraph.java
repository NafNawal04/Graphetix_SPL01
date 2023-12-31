import Utility.CustomLInkedList;
public class WeightedGraph implements IGraph
{
    public  int node;
    public int num_of_new_nodes;
    public CustomLInkedList<int[]>[] GraphRepresentationList;

    public WeightedGraph(int node)
    {
        this.node = node;
        GraphRepresentationList = new CustomLInkedList[node];
        for (int i = 0; i < node; i++) {
            GraphRepresentationList[i] = new CustomLInkedList<>();
        }
    }

    public void addWeightedEdge(int source, int dest, int weight)
    {
        int[] edge = {dest, weight};
        int[] edge2 ={source,weight};
        GraphRepresentationList[source].addFirst(edge);
        GraphRepresentationList[dest].addFirst(edge2);
        System.out.println("Added a weighted edge between " + source + " & " + dest+" having weight: "+weight);
    }




    public void removeWeightedEdge(int source, int dest, int weight)
    {
        for(int j=0;j<GraphRepresentationList[source].length();j++)
        {
            int[] edge = GraphRepresentationList[source].get(j);
            if(dest == edge[0] && weight == edge[1])
            {
                GraphRepresentationList[source].removeValue(edge);
            }
        }
        for(int j=0;j<GraphRepresentationList[dest].length();j++)
        {
            int[] edge = GraphRepresentationList[dest].get(j);
            if(source == edge[0] && weight == edge[1])
            {
                GraphRepresentationList[dest].removeValue(edge);
            }
        }

        System.out.println("Removed a weighted edge between " + source + " & " + dest+" having weight: "+weight);

    }



    @Override
    public void addEdge(int source,int dest)
    {

    }
    @Override
    public void removeEdge(int source,int dest)
    {


    }

    @Override
    public void addNode(int node_num)
    {
        num_of_new_nodes = node + node_num;
        CustomLInkedList<int[]>[] newGraphRepresentList = new CustomLInkedList[num_of_new_nodes];
        for(int i = 0; i < node; i++)
        {
            newGraphRepresentList[i] = GraphRepresentationList[i];
        }

        for (int i = node; i < num_of_new_nodes; i++)
        {
            newGraphRepresentList[i] = new CustomLInkedList<>();
        }

        node = num_of_new_nodes;
        GraphRepresentationList = newGraphRepresentList;
        System.out.println("Added "+ node_num+ " number of nodes in the Weighted Graph.");
    }

    @Override
    public void removeNode(int node_num)
    {
        GraphRepresentationList[node_num].clear();

        for (int i=0;i<node;i++)
        {
            for(int j=0;j<GraphRepresentationList[i].length();j++)
            {
                int[] edge = GraphRepresentationList[i].get(j);
                if(node_num == edge[0])
                {
                    GraphRepresentationList[i].removeValue(edge);
                }

            }
        }
        System.out.println("Removed the node "+ node_num + " from the weighted graph.");

    }

    @Override
    public void GraphRepresentation()
    {
        System.out.println("List Representation for Weighted graph:");
        for (int i = 0; i < node; i++)
        {
            System.out.print("Vertex " + i + " is connected to: ");
            int f = GraphRepresentationList[i].length();
            if(f==0)
            {
                System.out.println("Empty List");
                continue;
            }
            for (int j = 0; j < f; j++)
            {

                int[] edge = GraphRepresentationList[i].get(j);
                System.out.print("(" + edge[0] + ", Weight: " + edge[1] + ") ");
            }

            System.out.println();
        }


    }



}
