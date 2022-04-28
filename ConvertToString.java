public class ConvertToString implements AppTraverser
{
    StringBuffer string = new StringBuffer(getClass().getName() + "{");
    StringBuffer spaces = new StringBuffer();
    int depth = 0;
 
    public void addNode(Object key, MerkleTree subTree)
    {
        depth++;
        string.append("\n" + getSpaces() + key + " {");
    }
 
    public void subtractNode()
    {
        string.append("\n" + getSpaces() + "}");
        depth--;
    }
 
    public void processPath()
    {
    }
 
    public String toString()
    {
        string.append("\n}");
        return string.toString();
    }
 
    private String getSpaces()
    {
        if (spaces.length() < depth * 2)
        {
            while (spaces.length() < depth * 2)
            {
                spaces.append("  ");
            }
        }
        else if (spaces.length() > depth * 2)
        {
            spaces.setLength(depth * 2);
        }
        return spaces.toString();
    }
}
