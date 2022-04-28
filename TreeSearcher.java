public class TreeSearcher implements AppTraverser
{
    Object target;
    MerkleTree result;
 
    public TreeSearcher(Object t)
    {
        target = t;
    }
 
    public MerkleTree getResult()
    {
        return result;
    }
 
    public void addNode(Object node, MerkleTree subTree)
    {
        result = subTree.getTree(target);
        if (result != null)
        {
            throw new RuntimeException("found"); // short circuit traversal
                                                 // when found
        }
    }
 
    @Override
    public void subtractNode()
    {
    }
 
    @Override
    public void processPath()
    {
    }
}
 
