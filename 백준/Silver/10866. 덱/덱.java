import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        Deque deque = new Deque();
        for(int i = 0; i < N; i++){
            String[] line = br.readLine().trim().split(" ");
            switch(line[0]){
                case "push_back":
                    deque.pushBack(Integer.parseInt(line[1]));
                    break;
                case "push_front":
                    deque.pushFront(Integer.parseInt(line[1]));
                    break;
                case "pop_back":
                    System.out.println(deque.popBack());
                    break;
                case "pop_front":
                    System.out.println(deque.popFront());
                    break;
                case "size":
                    System.out.println(deque.size());
                    break;
                case "front":
                    System.out.println(deque.peekFront());
                    break;
                case "back":
                    System.out.println(deque.peekBack());
                    break;
                case "empty":
                    System.out.println(deque.isEmpty());
                    break;
            }
        }
    }
    public static class Node{
        private int data;
        private Node next;
        private Node prev;
        public Node(int data,Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
        public Node getPrev() {
            return prev;
        }
        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }
    public static class Deque{
        private Node head;
        private Node front;
        private Node rear;
        private int size;
        public Deque() {
            head = new Node(0,null, null);
            this.front = null;
            this.rear = null;
        }
        public int isEmpty() {
            return front == null ? 1 : 0;
        }
        public void pushFront(int x) {
            size++;
            if(isEmpty() == 1) {
                head.setNext(new Node(x, head, null));
                front = head.getNext();
                front.setPrev(head);
                rear = front;
                return;
            }
            Node newNode = new Node(x, head, front);
            front.setPrev(newNode);
            head.getNext().setPrev(newNode);
            head.setNext(newNode);
            front = newNode;
        }
        public void pushBack(int x) {
            size++;
            if(isEmpty() == 1) {
                head.setNext(new Node(x, head, null));
                front = head.getNext();
                front.setPrev(head);
                rear = front;
                return;
            }
            rear.setNext(new Node(x, rear, null));
            rear = rear.getNext();
        }
        public int popFront() {
            if(isEmpty() == 1) {
                return -1;
            }
            size--;
            int temp = front.getData();
            if(front == rear) {
                head.setNext(null);
                front = null;
                rear = null;
                return temp;
            }
            head.setNext(front.getNext());
            front.getNext().setPrev(head);
            front = head.getNext();
            return temp;
        }
        public int popBack() {
            if(isEmpty() == 1) {
                return -1;
            }
            size--;
            int temp = rear.getData();
            if(rear==front) {
                head.setNext(null);
                front = null;
                rear = null;
                return temp;
            }
            rear = rear.getPrev();
            rear.setNext(null);
            return temp;
        }
        public int size() {
            return size;
        }
        public int peekFront() {
            if(isEmpty() == 1) {
                return -1;
            }
            return front.getData();
        }
        public int peekBack() {
            if(isEmpty() == 1) {
                return -1;
            }
            return rear.getData();
        }

    }
}
