class Playlist
{
    Node head = null;
    Node tail = null;

    class Node
    {
        Song data;
        Node next;
        Node prev;
        Node(Song d)
        {
            data = d;
            next = null;
            prev = null;
        }
    }

    public void insertToEnd(Song new_song) {
        // Your code here
    	if(new_song == null) {
    		throw new NullPointerException("Null song entry!");
    	}
    	else {
    		Node a = new Node(new_song);
    		int nodenumber = nodecounter();
    		if(nodenumber == 0) {
    			a.data = new_song;
    			a.next = a;
    			a.prev = a;
    			head = a;
    			tail = a;
    		}
    		else {
    			a.data = new_song;
    			a.next = head;
    			head.prev = a;
    			a.prev = tail;
    			tail.next = a;
    			tail = a;
    		}
    	}
    }

    public void insertToIndex(Song new_song, int index) {
        // Your code here
    	if(new_song == null) {
    		throw new NullPointerException("Null song entry!");
    	}
    	else {
    		int nodenumber = nodecounter();
    		if(index >= 1 && index <= nodenumber + 1) {
    			if(index == nodenumber + 1) {
    				insertToEnd(new_song);
    			}
    			else {
    				Node a = new Node(new_song);
    				a.data = new_song;
    				Node temp = head;
    				for(int i = 1; i < index - 1; i++) {
    					temp = temp.next;
    				}
    				if(index == 1) {
    					Node replacednode = temp;
        				temp = temp.prev;
        				Node previousnode = temp;
        				a.next = replacednode;
        				replacednode.prev = a;
        				a.prev = previousnode;
        				previousnode.next = a;
        				head = a;
    				}
    				else {
    					temp = temp.next;
    					Node replacednode = temp;
        				temp = temp.prev;
        				Node previousnode = temp;
        				a.next = replacednode;
        				replacednode.prev = a;
        				a.prev = previousnode;
        				previousnode.next = a;
    				}
    
    				
    			}
    		}
    		else {
    			
    		}
    		
    	}
    }

    public void removeSong(String song_name){
        // Your code here
    	Node temp = head;
    	Node empty = null;
    	Node removenode = empty;
    	while (temp.next != head)  
        {  
            if(temp.data.getName().equals(song_name)) {
            	removenode = temp;
            	break;
            }
            temp = temp.next;  
        }
    	if(tail.data.getName().equals(song_name)) {
        	removenode = tail;
    	}
    	if(removenode == empty) {
    		
    	}
    	else {
    		if(removenode == head) {
    			head = removenode.next;
    		}
    		if(removenode == tail) {
    			tail = removenode.prev;
    		}
    		(removenode.next).prev = removenode.prev;
    		(removenode.prev).next = removenode.next;
    		if(removenode == head && removenode == tail) {
    			head = null;
    		}
    	}
    }

    public void move(String song_name, int move_num ) {
        // Your code here
    	if(move_num >= 0) {
    		int counter = 1;
    		Node temp = head;
        	Node empty = null;
        	Node removenode = empty;
        	while (temp.next != head)  
            {  
        		counter++;
                if(temp.data.getName().equals(song_name)) {
                	removenode = temp;
                	break;
                }
                temp = temp.next;  
            }
        	if(tail.data.getName().equals(song_name)) {
            	removenode = tail;
        	}
        	if(move_num < counter) {
            	if(removenode == empty) {
            		
            	}
            	else {
            		if(removenode == head) {
            			head = removenode.next;
            			(removenode.next).prev = removenode.prev;
                		(removenode.prev).next = removenode.next;
            		}
            		else if(removenode == tail) {
            			tail = removenode.prev;
            			(removenode.next).prev = removenode.prev;
                		(removenode.prev).next = removenode.next;
            		}
            		else {
            			(removenode.next).prev = removenode.prev;
                		(removenode.prev).next = removenode.next;
            		}
            	}
            	int insertposition = counter - move_num - 1;
            	
            	insertToIndex(removenode.data, insertposition);
        	}
        	else {
        		
        	}
    	}
    }

    public void reverseSequence(int first_ind, int second_ind){
        //Your code here
    	if(first_ind >= 1 && second_ind >= 1 && first_ind < second_ind && second_ind <= nodecounter() && nodecounter() > 0) {
    		Node temp1 = head;
    		Node temp2 = head;
    		Node temp3 = head;
    		Node temp4 = head;
    		if(first_ind == 1) {
    			for(int i = 1; i < first_ind - 1; i++) {
    				temp1 = temp1.next;
    			}
    			for(int i = 1; i < second_ind; i++) {
    				temp2 = temp2.next;
    			}
    		}
    		else {
    			for(int i = 1; i < first_ind; i++) {
    				temp1 = temp1.next;
    			}
    			for(int i = 1; i < second_ind; i++) {
    				temp2 = temp2.next;
    			}
    		}
    		temp3 = temp1;
    		temp4 = temp2;
			
			int counter = 0;
			for( int i = second_ind ; i > first_ind + ((second_ind - first_ind)/2) ; i--) {
				temp1 = temp2.prev;
				temp4 = temp3.next;
				removeSong(temp2.data.getName());
				insertToIndex(temp2.data, first_ind + counter);
				removeSong(temp3.data.getName());
				insertToIndex(temp3.data, second_ind - counter);
				temp2 = temp1;
				temp3 = temp4;
				counter++;
			}
    	}
    }

    //Your code here if you use helper methods
    public int nodecounter() {
    	Node temp = head;

        if (temp == null){
            return 0;
        }
        int counter = 0;
        while (temp.next != head)
        {
            counter++;
            temp = temp.next;
        }
        counter++;
        return counter;
    }

    public void displayList() {
        Node temp = head;

        if (temp == null){
            System.out.println("Playlist is empty!");
            return;
        }

        while (temp.next != head)
        {
            temp.data.displaySong();
            temp = temp.next;
        }
        temp.data.displaySong();
    }
}
