public class RedBlackTree <T extends Comparable<T>>{
    private Node root;

    class Node {
        Node left, right;
        T value;
        ColorNode color;

        Node(T data) {
            this.value = data;
            left = null;
            right = null;
            color = ColorNode.RED;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "left=" + left +
                    ", right=" + right +
                    ", value=" + value +
                    ", color=" + color +
                    '}';
        }
    }

    private Node rotateLeft(Node myNode) {
        Node child = myNode.right;
        Node childLeft = child.left;
        child.left = myNode;
        myNode.right = childLeft;
        return child;
    }

    private Node rotateRight(Node myNode) {
        Node child = myNode.left;
        Node childRight = child.right;
        child.left = myNode;
        myNode.left = childRight;
        return child;
    }

    private boolean isRed(Node myNode) {
        return myNode != null && myNode.color.equals(ColorNode.RED);
    }

    private void swapColors(Node node1, Node node2) {
        ColorNode temp = node1.color;
        node1.color = node2.color;
        node2.color = temp;
    }

    public boolean insert(T data) {
        Node node;
        if (root != null) {
            node = insert(root, data);
            if (node == null) {
                return false;
            }
        } else {
            node = new Node(data);
        }
        root = node;
        root.color = ColorNode.BLACK;
        return true;
    }

    private Node insert(Node myNode, T data) {
        if (myNode == null) {
            return new Node(data);
        }
        if (myNode.value.compareTo(data) > 0) {
            myNode.left = insert(myNode.left, data);
        } else if (myNode.value.compareTo(data) < 0) {
            myNode.right = insert(myNode.right, data);
        } else
            return null;
        return balanced(myNode);
    }

    private Node balanced(Node myNode) {
        if (isRed(myNode.right) && !isRed(myNode.left)) {
            myNode = rotateLeft(myNode);
            swapColors(myNode, myNode.left);
        }
        if (isRed(myNode.left) && isRed(myNode.left.left)) {
            myNode = rotateRight(myNode);
            swapColors(myNode, myNode.left);
        }
        if (isRed(myNode.left) && isRed(myNode.right)) {
            myNode.color = ColorNode.RED;
            myNode.left.color = ColorNode.BLACK;
            myNode.right.color = ColorNode.BLACK;
        }
        return myNode;
    }


    public void incorder(Node node) {
        if (node != null) {
            incorder(node.left);
            System.out.println(node.value + " " + node.color);
            incorder(node.right);
        }
    }

    public void incorder() {
        incorder(root);
    }

}
