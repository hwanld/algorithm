import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        Node head = new Node (null, 0, null);
        Node cur = head;
        for (int i=1; i<n; i++) {
            Node newNode = new Node(cur, i, null);
            cur.next = newNode;
            cur = newNode;
        }
        cur = head;
        for (int i=0; i<k; i++) {
            cur = cur.next;
        }
        
        Stack<Node> st = new Stack<>();
        
        for (int j=0; j<cmd.length; j++) {
            String[] cmdline = cmd[j].split(" ");
            char command = cmdline[0].charAt(0);
            int count = 0;
            if (cmdline.length==2) count = Integer.parseInt(cmdline[1]);
            
            switch (command) {
                case 'U':
                    for (int i=0; i<count; i++)
                        cur = cur.past;
                    break;
                case 'D':
                    for (int i=0; i<count; i++)
                        cur = cur.next;
                    break;
                case 'C':
                    st.push(cur);
                    if (cur.past == null) {
                        cur.next.past = null;
                        cur = cur.next;
                        head = cur;
                    }
                    else if (cur.next == null) {
                        cur.past.next = null;
                        cur = cur.past;
                    }
                    else {
                        cur.past.next = cur.next;
                        cur.next.past = cur.past;
                        cur = cur.next;
                    }
                    break;
                case 'Z':
                    Node newNode = st.pop();
                    if (newNode.past == null) {
                        newNode.next.past = newNode;
                        head = newNode;
                    }
                    else if (newNode.next == null) {
                        newNode.past.next = newNode;
                    }
                    else {
                        newNode.past.next = newNode;
                        newNode.next.past = newNode;
                    }
                    break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        cur = head;
        for (int i=0; i<n; i++) {
            if (cur == null) {
                sb.append("X");
                continue;
            }
            if (cur.data == i) {
                sb.append("O");
                cur = cur.next;
            }
            else sb.append("X");
        }
        return sb.toString();
    }
}

class Node {
    Node past;
    int data;
    Node next;
    
    public Node (Node past, int data, Node next) {
        this.past = past;
        this.data = data;
        this.next = next;
    }
}