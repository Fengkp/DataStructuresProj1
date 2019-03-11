public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public void insert(Node<T> spot) {
        if (isEmpty()) {
            head = spot;
            size++;
        }
        else {
            Node newHead;
            Term spotTerm = (Term) spot.getData();
            Term pointerTerm = (Term) head.getData();

            if (pointerTerm.compareTo(spotTerm) == 0) {
                spot.setNext(head);
                newHead = spot;
            }
            else {
                newHead = head;
                Node pointer = newHead;
                while (pointer.getNext() != null) {
                    pointerTerm = (Term) pointer.getNext().getData();
                    if (pointerTerm.compareTo(spotTerm) == 0)
                        break;
                    pointer = pointer.getNext();
                }
                spot.setNext(pointer.getNext());
                pointer.setNext(spot);
            }
            head = newHead;
            size++;
        }
    }

    public Node getHead() {
        return head;
    }

    public void removeHead() {
        head = head.getNext();
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }
}