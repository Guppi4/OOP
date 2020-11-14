package ex1;
import ex1.WGraph_DS.NodeInfo;

import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms {
    private WGraph_DS gr;//

    public WGraph_Algo(weighted_graph_algorithms grap) {
        this.init((weighted_graph) grap);
    }


    public WGraph_Algo() {
        WGraph_DS gr = new WGraph_DS();
        this.init(gr);
    }

    public void init(weighted_graph g) {
        if (g instanceof WGraph_DS) {
            this.gr = (WGraph_DS) g;
        }

    }

    @Override
    public weighted_graph getGraph() {
        return null;
    }

    public weighted_graph copy() {//deep copy of graph

        weighted_graph gk = new WGraph_DS();


        for (node_info n : gr.getV()) {
            //System.out.println(n);
            node_info no = new NodeInfo(n);

            gk.addNode(no.getKey());




        }

        for (node_info na : gr.getV()) {
            NodeInfo naa= (NodeInfo) na;
            for(node_info na2:naa.getNi()){
                //System.out.println(na2);
                NodeInfo naa2= (NodeInfo) na2;

                gk.connect(na.getKey(),na2.getKey(),gr.getEdge(naa.getKey(),naa2.getKey()));
            }
        }





        return gk;
    }


    public boolean isConnected() {   //cheaking using afinction BFS if graph connected
        int m=0;
        int m2=1;
        int m3=0;
        int m4=0;
        //System.out.println(gr.nodeSize());
        for (node_info n : gr.getV()) {

            m4++;



        }

        if(gr.nodeSize()==0){

            return true;
        }



        if(gr.nodeSize()==1){
            // System.out.println(gr.nodeSize());
            return true;
        }



        //System.out.println(gr.nodeSize());
        for (node_info n : gr.getV()) {

            m3=n.getKey();


            break;
        }
        if(gr.getNode(m3)==null){
            return true;
        }
        if(BFS2(m3,gr)==m4){

            //
            return true;
        }
        else
            return  false;



    }


    public double shortestPathDist(int src, int dest) {
        if(src==dest){
            return 0;
        }

       Dijkstra(src,gr);
        node_info  n=gr.getNode(dest);
        double s=n.getTag();
        //System.out.println(s);
        if(s==0){
            return -1;
        }

        return s;
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */

    public List<node_info> shortestPath(int src, int dest) {
        if(src==dest){
            return null;
        }
        if(gr.getNode(src)==null ||gr.getNode(dest)==null){
            return null;
        }

        LinkedList<node_info> list = new LinkedList<node_info>();
        Dijkstra(src,gr);
        //System.out.println(gr.getNode(dest).getKey()+" "+gr.getNode(dest).getTag());
        NodeInfo k= (NodeInfo) gr.getNode(dest);
        list.addFirst(k);

        while(k.getTag()!=0){
           int k1=k.getpred();
            k= (NodeInfo) gr.getNode(k1);
           list.addFirst(k);

        }
        return (List<node_info>)list;
    }






        //System.out.println(list.size());




    public boolean save(String file) {
        try {
            FileOutputStream f= new FileOutputStream(file);
            ObjectOutputStream o=new ObjectOutputStream(f);
            o.writeObject(gr);
            o.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

       return true;
    }


    public boolean load(String file) {

        try {
            FileInputStream i=new FileInputStream(file);
            ObjectInputStream o=new ObjectInputStream(i);
            WGraph_DS g= (WGraph_DS) o.readObject();

        }

         catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
      return true;


    }

    /**
     * algoritm BFS for funtion "isconnected"
     *
     * @param src - start node
     * @param g - end (target) node
     * @return
     */

    public int BFS2(int src,weighted_graph g){

        for (node_info n : g.getV()) {
            // System.out.println(n);
            n.setInfo("white");
            n.setTag(0);

        }
        int numNode = 0;

        //System.out.println(gr.getNode(src));
        gr.getNode(src).setInfo("gray");
        Queue<node_info> q = new LinkedList<node_info>();
        q.offer(gr.getNode(src));

        while (!q.isEmpty()) {
            NodeInfo qq = (NodeInfo) q.remove();
            for(node_info a:qq.getNi()){
                if(a.getInfo()=="white"){
                    double scrr=qq.getTag();
                    a.setTag(scrr+1);

                    node_info k=g.getNode(a.getKey());
                    k.setInfo("grey");
                    q.offer(k);
                }
            }
            qq.setInfo("black");
            numNode++;
        }

        for (node_info n : g.getV()) {
            n.setInfo("");
            n.setTag(0);

        }
        return numNode;

    }

    /**
     * algoritm BFS for funtion "shortestPath" and "shortestpathdist"
     *
     * @param src - start node
     * @param g - end (target) node
     * @return
     */


    public void BFS(int src,weighted_graph g){
        for (node_info n : g.getV()) {
            n.setInfo("white");
            n.setTag(0);

        }
        // int numNode = 0;

        gr.getNode(src).setInfo("gray");
        Queue<node_info> q = new LinkedList<node_info>();
        q.offer(gr.getNode(src));

        while (!q.isEmpty()) {
            NodeInfo qq = (NodeInfo) q.remove();
            for(node_info a:qq.getNi()){
                if(a.getInfo()=="white"){
                    double scrr=qq.getTag();
                    a.setTag(scrr+1);

                    node_info k=g.getNode(a.getKey());
                    k.setInfo("grey");
                    q.offer(k);

                }
            }
            qq.setInfo("black");
            //numNode++;
        }




    }
public void Dijkstra(int src,weighted_graph g){//dggf
    PriorityQueue<NodeInfo> q = new
            PriorityQueue<NodeInfo>();
        NodeInfo n= (NodeInfo) g.getNode(src);

    for (node_info t : g.getV()) {
       // System.out.println(t.getKey());
        t.setTag(Double.POSITIVE_INFINITY);
        t.setInfo("white");
        q.add((NodeInfo) t);
    }
    n.setTag(0);
 while(!q.isEmpty()){
    NodeInfo u=q.remove();
    //System.out.println(u.getKey());
    for(node_info a:u.getNi()){
       NodeInfo a2=(NodeInfo) a;
        if(a.getInfo()!="red"){
           // System.out.println(u.getKey());
            double t=u.getTag()+ g.getEdge(a.getKey(),u.getKey());

           if(a.getTag()>t){

               //System.out.println(a.getTag());
                  a2.setTag(t);
                  a2.setpred(u.getKey());
                  q.remove(a);
                  q.add(a2);
               //System.out.println(a.getTag());
             }
        }
         u.setInfo("red");
     }

 }



    }


}


