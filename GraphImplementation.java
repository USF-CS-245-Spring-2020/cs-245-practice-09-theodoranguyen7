import java.util.ArrayList;
import java.util.List;

public class GraphImplementation implements Graph {

    int verticies;
    int matrix[][];

    public GraphImplementation(int verticies) {
        this.verticies = verticies;
        matrix = new int[verticies][verticies];
    }

    @Override
    public void addEdge(int v1, int v2) throws Exception {
        matrix[v1][v2] = 1;
    }

    @Override
    public List<Integer> topologicalSort() {
        int [] incident = new int[verticies];
        for(int i = 0; i < verticies; i++)
        {
            incident[i] = 0;
        }

        for (int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix.length; j++){
                incident[j] += matrix[i][j];
            }
        }
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

    @Override
    public List<Integer> neighbors(int vertex) throws Exception {
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
