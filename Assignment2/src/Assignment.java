import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;  
//import java.util.List;
//import java.util.Map;  


public class Assignment implements PrefixMap {

	
	private class Node {
		
		 private String node_value;
	     private int dumpli_num;
	     private int prefix_num; 
	     private Node childs[];
	     private boolean isLeaf;  
	     
	     public Node(){ 
	    	 node_value=null;
	         dumpli_num=0;  
	         prefix_num=0;  
	         isLeaf=false;  
	         childs=new Node[4];  
	     }  
	}
	
	 private Node root;///root 
	/*
	 * The default constructor will be called by the tests on Ed
	 */
	public Assignment() {
		
		root=new Node();  
	}

	@Override
	public int size() {
		
		int number_of_key=0;
	 	 HashMap<String,String> PreTraversalMap=preTraversal(this.root, "");
	 	 
	 	 for(String key:PreTraversalMap.keySet()){  
	 		 number_of_key++;
	      }  
	 	 
			return number_of_key;

	}

	@Override
	public boolean isEmpty() {
		 if(this.root==null)
			 return true;
		 else
			 return false;
	}
	
	public int indexGenerator(char c){
		 int index=-1;
		 if(c=='A')
			 index=0;
		 else if(c=='C')
			 index=1;
		 else if(c=='G')
			 index=2;
		 else if(c=='T')
			 index=3;
		 else index=-1;
		 return index;
		 
	 }
	 
	 public char characterGenerator(int index){
		 char chr='N';
		 if(index==0)
			 chr='A';
		else if (index==1)
				 chr='C';
		else if(index==2)
			    chr='G';
		else if(index==3)
			chr='T';
		else chr='N';
	    return chr;
		 
		 
		 
	 }
	
	 private  HashMap<String,String> preTraversal(Node root,String prefixs){  
         HashMap<String, String> map=new HashMap<String, String>();  
           
         if(root!=null){  
               
             if(root.isLeaf==true){  
             ////当前即为一个单词  
                 map.put(prefixs, root.node_value);
                
             }  
               
             for(int i=0,length=root.childs.length; i<length;i++){  
                 if(root.childs[i]!=null){  
                     char ch=characterGenerator(i);  
                     ////recursion: pre-order traversal
                     String tempStr=prefixs+ch;  
                     map.putAll(preTraversal(root.childs[i], tempStr));  
                 }  
             }  
         }         
           
         return map;  
     }  
	
	

	@Override
	public String get(String key) {
		 HashMap<String,String> PreTraversalMap=preTraversal(this.root, "");
		   for(String keyinmap:PreTraversalMap.keySet()){  
	  		 if(keyinmap.equals(key))
	  			 return PreTraversalMap.get(keyinmap);
	       } 
			return null;
	}

	@Override
	public String put(String key, String value) {
		
		 return insert(this.root, key, value); 
	}

	@Override
	public String remove(String key) {
		
		return insert(this.root, key, null); 
	}

	
	private String insert(Node root,String words,String n_value){  
		 
		 String return_value=null;
		 
	     words=words.toUpperCase();////turn to upper case  
	     char[] chrs=words.toCharArray();  
	       
	     for(int i=0,length=chrs.length; i<length; i++){  
	         
	    	 ///record the value  
	    	 //Generate index
	         int index=indexGenerator(chrs[i]); 
	         
	         //check if the node exist
	         if(root.childs[index]!=null){  
	             ////if exists，prefix_num of the node++  
	             root.childs[index].prefix_num++;  
	         }
	         else{  
	             ///if not exists,create a new node 
	             root.childs[index]=new Node();  
	             root.childs[index].prefix_num++;                  
	         }     
	           
	         ///Mark the String when it goes to the end  
	         if(i==length-1){  
	        	 
	             root.childs[index].isLeaf=true;  
	             root.childs[index].dumpli_num++; 
	             
	             //if first time, set the value and return null
	             if(root.childs[index].dumpli_num==1){
	             root.childs[index].node_value=n_value;
	             
	             }//if
	             
	             else
	             {
	            	 return_value=root.childs[index].node_value;
	            	 root.childs[index].node_value=n_value;
	            	 
	            	 
	             }
	         }  
	         ///from root to its child，and proceed  
	         root=root.childs[index];  
	     }  
	     return return_value;
	 }
	
	
	public boolean containPrefix(String prefix,String target){
		 char[] chr_prefix=prefix.toCharArray();
		 char[] chr_target=target.toCharArray();
		 
		 if(chr_target.length <chr_prefix.length)
			 return false;
		 
		 else{
		 for(int i=0;i<chr_prefix.length;i++){
			 if(chr_prefix[i]!=chr_target[i])
				 return false;
			 }
		 }//else
		 return true;
		 
	 }
	
	
	@Override
	public int countKeysMatchingPrefix(String prefix) {
		 int countkey=0;
		 HashMap<String,String> PreTraversalMap=preTraversal(this.root, "");
		   for(String keyinmap:PreTraversalMap.keySet()){  
			 if(containPrefix(prefix,keyinmap))
				 countkey++;
				 
	     } 
		   return countkey;
	}

	@Override
	public List<String> getKeysMatchingPrefix(String prefix) {
		 List<String> targetlist= new ArrayList<String>();
		 HashMap<String,String> PreTraversalMap=preTraversal(this.root, "");
		   for(String keyinmap:PreTraversalMap.keySet()){  
			 if(containPrefix(prefix,keyinmap))
				 targetlist.add(keyinmap);
				 
	     } 
		   return targetlist;
	}
	
	 public boolean isMember(String s, List<String> Slist){
		 for(String str: Slist){
			 if(s.equals(str))
				 return true;
		 }
		 return false;
		 
	 }

	@Override
	public int countPrefixes() {
		 List<String> UniquePrefix= new ArrayList<String>();
		 HashMap<String,String> PreTraversalMap=preTraversal(this.root, "");
		   
		 for(String keyinmap:PreTraversalMap.keySet()){ 
			   char[] chr_keyinmap=keyinmap.toCharArray();
			   String prefix=null;
			   for(int i=0;i<chr_keyinmap.length;i++){
				   prefix=prefix+chr_keyinmap[i];
				   if(!isMember(prefix,UniquePrefix))
					   UniquePrefix.add(prefix);
			   }//for
			   
		   }//for
		 
		 return UniquePrefix.size();
	}

	@Override
	public int sumKeyLengths() {
		 int sumkeylength=0;
		 List<String> UniquePrefix= new ArrayList<String>();
		 HashMap<String,String> PreTraversalMap=preTraversal(this.root, "");
		 
		 for(String keyinmap:PreTraversalMap.keySet()){ 
			 sumkeylength=sumkeylength+keyinmap.length(); 
		 }
		 
		 return sumkeylength;
	}
	
	
	 public HashMap<String,String> getAllWords(){  
          return preTraversal(this.root, "");  
     }  
	
	
	public static void main(String[] args) {
		
		 
		Assignment trie = new Assignment();  
		 
		 //Test put
		 System.out.println("Insert C NULL, return "+trie.put("C",null));
		 System.out.println("Insert GA NULL, return "+trie.put("GA",null));
		 System.out.println("Insert CG Fred, return "+trie.put("CG","Fred"));  
		 System.out.println("Insert CT Jane, return "+trie.put("CT","Jane")); 
		 System.out.println("Insert G Suzy, return "+trie.put("G","Suzy")); 
		 System.out.println("Insert GAC Bill, return "+trie.put("GAC","Bill")); 
		 System.out.println("Insert GAT Kate, return "+trie.put("GAT","Kate")); 
		 System.out.println("Insert CGA Lucy, return "+trie.put("CGA","Lucy")); 
		 
		 
		 //Test Size
		 System.out.println("Size= "+trie.size());
		 
		 
		 //////Output
	     HashMap<String,String> map=trie.getAllWords();
	     System.out.println("*********************Current Hash map*****************");
	     for(String key:map.keySet()){  
	            System.out.println(key+"     "+ map.get(key));  
	        }  
	     
	     //Test Get
	     System.out.println("The value of G is "+ trie.get("G") );
	     
	     //Test Remove
	     System.out.println("*********************************************************");
	     System.out.println("Remove G Suzy, return "+trie.remove("G"));
	       
		 
	     System.out.println("*********************Current Hash map*****************");
	      HashMap<String,String> map2=trie.getAllWords();
		     for(String key:map2.keySet()){  
		            System.out.println(key+"     "+ map2.get(key));  
		        }  
		     
		     System.out.println("Size= "+trie.size());
		     System.out.println("The value of G is "+ trie.get("G") );
		     
		     
		  		     
		     //Test: countKeysMatchingPrefix
		     System.out.println("Number of Prefix GAC is "+trie.countKeysMatchingPrefix("GA"));
		     
		     //Test:getKeysMatchingPrefix
		     System.out.println("Show Prefix C ");
		     for(String s:trie.getKeysMatchingPrefix("C")){
		    	 System.out.println(s);
		     }
		     
		     //Test:countPrefixes();
		     System.out.println("Number of Unique Prefix is: "+ trie.countPrefixes());
		     
		     //Test:sumKeyLengths()
		    System.out.println("Sum length of Prefix is: "+ trie.sumKeyLengths());
		    
	       
	}
	
	
}
