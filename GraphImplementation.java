import java.util.ArrayList;
import java.util.List;

public class GraphImplementation implements Graph {

    int verticies;
    int matrix[][];

    public GraphImplementation(int verticies) {
        this.verticies = verticies;
        matrix = new int[verticies][verticies];
    }

    /**
     * Adds a directed edge between two vertices.
     * */
    @Override
    public void addEdge(int v1, int v2) throws Exception {
        matrix[v1][v2] = 1;
    }

    /**
     * Prints the ordering of vertices.
     * */
    @Override
    public List<Integer> topologicalSort() {
        /*Intializing everything and setting it to 0*/
        int [] incident = new int[verticies];
        for(int i = 0; i < verticies; i++)
        {
            incident[i] = 0;
        }

        /*Counts every connections in the adjacent matrix by columns*/
        for (int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix.length; j++){
                incident[j] += matrix[i][j];
            }
        }

        /*Orders everything in the graph based on the connections of the vertices*/
        List<Integer> schedule = new ArrayList<>();
        for(int i = 0 ; i < verticies; i++){
            for(int j = 0; j < incident.length; j++){
                if(incident[j] == 0){
                    try{
                    List<Integer> neighbors = neighbors(j);
                    for (int k = 0; k < neighbors.size(); k++)
                    {
                        incident[neighbors.get(k)] -= 1;
                    }
                    schedule.add(j);
                    incident[j] = -1;
                    } catch(Exception e){
                        System.out.println("Broke");
                    }
                }
            }
        }

        return schedule;
    }


    /**
     * Returns a list of vertix IDs.
     * */
    @Override
    public List<Integer> neighbors(int vertex) throws Exception {
        /*Defining the neighbors of the vertices*/
        List<Integer> neighbor = new ArrayList<>();
        for (int i = 0; i < verticies;i++)
        {
            if (matrix[vertex][i] > 0) {
                neighbor.add(i);
            }
        }

        return neighbor;
    }
}
