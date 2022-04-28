import java.util.Arrays;
import java.util.Collection;

public class App {
    public static void main(String args[])
    {
        Collection treePath = Arrays.asList(new String[] { "1", "2", "3", "4" });
        MerkleTree tree = new MerkleTree();
        tree.add(treePath, "value");
        MerkleTree tree1 = new MerkleTree("abcd");
        MerkleTree tree2 = new MerkleTree("abcd");
        MerkleTree tree3 = new MerkleTree("abcde");
        MerkleTree tree4 = new MerkleTree("abcde");
        System.out.println("Is tree1 equals tree2: " + tree1.equals(tree1));
        System.out.println("Is hashcodes of tree1 and tree2 are equal: "
                + (tree1.hashCode() == tree2.hashCode()));
        System.out.println("Is tree3 equals tree3: " + tree3.equals(tree3));
        tree1.add("abcd", tree3);
        System.out.println("Is modified tree1 is equal to tree3: "
                + tree1.equals(tree2));
        tree2.add("abcd", tree4);
        System.out.println("Is modified tree2 equals tree1: "
                + tree1.equals(tree2));
        System.out.println("Is hashcodes are equal: "
                + (tree1.hashCode() == tree2.hashCode()));
    }
}
