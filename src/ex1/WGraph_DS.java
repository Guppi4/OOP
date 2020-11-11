
package ex1;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class WGraph_DS implements weighted_graph {
    public static class NodeInfo implements Comparable<NodeInfo>,node_info{
        private int id,pred;
        private  double tag;
        private String info;
        private static int c=0;
        private HashSet<node_info> neighbors;
        private HashMap< Integer,Double>  weight = new HashMap<Integer,Double>();

        public  void addW(node_info t,double w){
           this.weight.put(t.getKey(),w);
        }

        public NodeInfo(node_info n){
            this.id=n.getKey();
            this.info=n.getInfo();
            this.tag=n.getTag();
            this.neighbors=new HashSet<>();


        }
        public NodeInfo(int key){
            this.id=key;
            this.info="";
            this.tag=0;
            this.neighbors=new HashSet<>();

        }
        public void removeNode(node_info node) {
            this.neighbors.remove(node);
        }
        public Collection<node_info> getNi(){

            return this.neighbors;
        }
        public void addNi(node_info t){
            if(t!=null) {
                this.neighbors.add(t);
            }
        }


        public int getKey() {
            return this.id;
        }
        public String getInfo(){
            return this.info;
        }
        public void setInfo(String s){
            this.info=s;
        }
        public double getTag(){
            return this.tag;
        }

        @Override
        public void setTag(double t) {

        }

        public void setTag(int t){
            this.tag=t;
        }
    public int compareTo(NodeInfo v){
       int ans=0;
            if(this.tag-v.getTag()>0)  {
               ans=1;
            }
            else if (this.tag-v.getTag()<0){
                ans=-1;
            }
          return ans;
        }

    }






    private int eS=0;//number of eages in graph
    private int ms=0;//number of all actyvity in graph
    private HashMap< Integer,node_info> nodeMap = new HashMap<Integer,node_info>();// data struct for nodes in graph
    public WGraph_DS
            (HashMap< Integer,node_info> no){
        this.nodeMap=no;
    }



    public WGraph_DS() {

    }


    public node_info getNode(int key){
        if(this.nodeMap.get(key)!=null) {
            return this.nodeMap.get(key);
        }
        return null;
    }







    public boolean hasEdge(int node1, int node2) {
       NodeInfo a= (NodeInfo) getNode(node1);
        node_info b=getNode(node2);
        if(a!=null && b!=null && a.getNi().contains(b)) {
            return true;
        }
        return false;
    }


    public void addNode(int k) {
        if(nodeMap.get(k)!=null){
            return;
        }
        this.ms++;
        node_info n=new NodeInfo(k);
        this.nodeMap.put(n.getKey(),n);

    }


    public void connect(int node1, int node2,double w) { //connect node1 with node 2
        NodeInfo a= (NodeInfo) getNode(node1);
        NodeInfo b= (NodeInfo) getNode(node2);

        if(node1==node2){

            return;
        }

     if(w>=0) {
         if (a != null && b != null && !a.getNi().contains(b)) {

             a.addNi(b);
             b.addNi(a);
             a.addW(b,w);
             b.addW(a,w);
             this.eS++;
             this.ms++;
         }
     }
        else
            return;

    }


    public Collection<node_info> getV() {
        Collection<node_info> co = this.nodeMap.values();
        return co;
    }


    public Collection<node_info> getV(int node_id) {
        NodeInfo c= (NodeInfo) getNode(node_id);
        Collection<node_info>c2= c.getNi();
        return c2;
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key
     */
    public node_info removeNode(int key) {
        if(nodeMap.containsKey(key)==false){
            return null;
        }


        NodeInfo a= (NodeInfo) getNode(key);

        for (node_info b : a.getNi()) {
            //System.out.println(this.eS);
            //System.out.println(b.getKey());
           NodeInfo bb= (NodeInfo) b;
            bb.removeNode(a);
            this.eS--;

        }



        this.nodeMap.remove(key);
        this.ms++;
        return a;



    }


    public void removeEdge(int node1, int node2) {
        NodeInfo c= (NodeInfo) getNode(node1);
        NodeInfo c2= (NodeInfo) getNode(node2);
        if(c.getNi().contains(c2)){
            c.removeNode(c2);
            c2.removeNode(c);
            this.eS--;
            this.ms++;
        }


    }


    public int nodeSize() {
        return this.nodeMap.size();
    }


    public int edgeSize() {
        return this.eS;
    }


    public int getMC() {
        return this.ms;
    }

    public double getEdge(int node1, int node2){
        NodeInfo a= (NodeInfo) getNode(node1);
        NodeInfo b= (NodeInfo) getNode(node2);
       if(hasEdge(node1,node2)){

           return a.weight.get(b);
       }

       else
           return -1;
    }







}
