
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
 

 
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MerkleTree implements Serializable, Map
{
    private static final long serialVersionUID = 5526070397395889719L;
 
    public MerkleTree()
    {
        data = new HashMap();
    }
 
    public MerkleTree(Object key)
    {
        data = new HashMap();
        data.put(key, new MerkleTree());
    }
 
    public void putAll(Map map)
    {
        if (map instanceof MerkleTree)
        {
            this.add((MerkleTree) map);
        }
        else
        {
            throw new UnsupportedOperationException(
                    "Unsupported data found");
        }
    }
 
    public Set entrySet()
    {
        return data.entrySet();
    }
 
    public boolean containsValue(Object value)
    {
        return data.containsValue(value);
    }
 
    public Object put(Object key, Object value)
    {
        Object previous = data.get(key);
        add(key, value);
        return previous;
    }
 
    public void clear()
    {
        data.clear();
    }
 
    public Collection values()
    {
        return data.values();
    }
 
    public void add(Object key, MerkleTree subTree)
    {
        add(key);
        getTree(key).add(subTree);
    }
 
    public void add(MerkleTree newTree)
    {
        Iterator iter = newTree.list().iterator();
        while (iter.hasNext())
        {
            Object item = iter.next();
            add(item);
            getTree(item).add(newTree.getTree(item));
        }
    }
 
    public MerkleTree(Collection keys)
    {
        data = new HashMap();
        Iterator it = keys.iterator();
        while (it.hasNext())
        {
            data.put(it.next(), new MerkleTree());
        }
    }
 
    public MerkleTree(Object[] keys)
    {
        data = new HashMap();
        for (int x = 0; x < keys.length; x++)
        {
            data.put(keys[x], new MerkleTree());
        }
    }
 
    public boolean containsKey(Object o)
    {
        return data.containsKey(o);
    }
 
    public boolean isEmpty()
    {
        return data.isEmpty();
    }
 
    public void set(Object key, Object value)
    {
        data.put(key, createNewTree(value));
    }
 
    public void set(Object key, MerkleTree t)
    {
        data.put(key, t);
    }
 
    public void set(Object key, Object[] values)
    {
        data.put(key, createNewTree(Arrays.asList(values)));
    }
 
    public void set(Object key, Collection values)
    {
        data.put(key, createNewTree(values));
    }
 
    public void set(Object[] treePath, Object[] values)
    {
        if (treePath != null && values != null)
        {
            set(Arrays.asList(treePath), Arrays.asList(values));
        }
    }
 
    public void set(Object[] treePath, Collection values)
    {
        if (treePath != null)
        {
            set(Arrays.asList(treePath), values);
        }
    }
 
    public void set(Collection treePath, Object[] values)
    {
        MerkleTree tree = addTreePath(treePath);
        tree.set(Arrays.asList(values));
    }
 
    public void set(Collection values)
    {
        clear();
        this.add(values);
    }
 
    public void set(Collection treePath, Collection values)
    {
        MerkleTree tree = addTreePath(treePath);
        tree.set(values);
    }
 
    public MerkleTree add(Object key)
    {
        if (!data.containsKey(key))
        {
            MerkleTree newTree = createNewTree();
            data.put(key, newTree);
            return newTree;
        }
        else
        {
            return getTree(key);
        }
    }
 
    public void add(Object[] keys)
    {
        for (int x = 0; x < keys.length; x++)
        {
            add(keys[x]);
        }
    }
 
    public void add(Collection keys)
    {
        Iterator it = keys.iterator();
        while (it.hasNext())
        {
            add(it.next());
        }
    }
 
    public MerkleTree add(Object key, Object value)
    {
        add(key);
        return getTree(key).add(value);
    }
 
    public void add(Object key, Object[] values)
    {
        add(key);
        getTree(key).add(values);
    }
 
    public void add(Object key, Collection values)
    {
        add(key);
        getTree(key).add(values);
    }
 
    public void add(Object[] treePath, Object[] values)
    {
        if (treePath != null)
        {
            add(Arrays.asList(treePath), Arrays.asList(values));
        }
    }
 
    public void add(Object[] treePath, Collection values)
    {
        if (treePath != null)
        {
            add(Arrays.asList(treePath), values);
        }
    }
 
    public MerkleTree add(Object[] treePath, Object value)
    {
        return add(Arrays.asList(treePath), value);
    }
 
    public void add(Collection treePath, Object[] values)
    {
        MerkleTree tree = addTreePath(treePath);
        tree.add(Arrays.asList(values));
    }
 
    public MerkleTree add(Collection treePath, Object value)
    {
        MerkleTree tree = addTreePath(treePath);
        return tree.add(value);
    }
 
    public void add(Collection treePath, Collection values)
    {
        MerkleTree tree = addTreePath(treePath);
        tree.add(values);
    }
 
    protected MerkleTree addTreePath(Collection treePath)
    {
        MerkleTree tree = this;
        Iterator iter = treePath.iterator();
        while (iter.hasNext())
        {
            Object temp = iter.next();
            tree.add(temp);
            tree = tree.getTree(temp);
        }
        return tree;
    }
 
    public MerkleTree getTree(Object key)
    {
        return (MerkleTree) data.get(key);
    }
 
    public Object get(Object key)
    {
        return getTree(key);
    }
 
    public MerkleTree getTree(Object[] treePath)
    {
        if (treePath != null)
        {
            return getTree(Arrays.asList(treePath));
        }
        else
        {
            return this;
        }
    }
 
    public Object clone()
    {
        MerkleTree newTree = new MerkleTree();
        cloneTree(newTree);
        return newTree;
    }
 
    protected void cloneTree(MerkleTree newTree)
    {
        Iterator iter = list().iterator();
        while (iter.hasNext())
        {
            Object key = iter.next();
            newTree.set(key, (MerkleTree) getTree(key).clone());
        }
    }
 
    protected MerkleTree createNewTree()
    {
        return new MerkleTree();
    }
 
    protected MerkleTree createNewTree(Object key)
    {
        return new MerkleTree(key);
    }
 
    protected MerkleTree createNewTree(Collection values)
    {
        return new MerkleTree(values);
    }
 
    public MerkleTree getTree(Collection treePath)
    {
        return getTreePath(treePath);
    }
 
    public Collection list()
    {
        return data.keySet();
    }
 
    public Collection list(Object key)
    {
        MerkleTree temp = (MerkleTree) data.get(key);
        if (temp != null)
        {
            return temp.list();
        }
        else
        {
            return null;
        }
    }
 
    public Object remove(Object key)
    {
        return data.remove(key);
    }
 
    public Collection list(Object[] treePath)
    {
        if (treePath != null)
        {
            return list(Arrays.asList(treePath));
        }
        else
        {
            return list();
        }
    }
 
    public Collection list(Collection treePath)
    {
        return getTreePath(treePath).list();
    }
 
    public Object replace(Object currentKey, Object newKey)
    {
        MerkleTree tree = getTree(currentKey);
        data.remove(currentKey);
        data.put(newKey, tree);
        return null;
    }
 
    public Object[] getArray()
    {
        return data.keySet().toArray();
    }
 
    public Object[] getArray(Object key)
    {
        return getTree(key).getArray();
    }
 
    public Object[] getArray(Object[] treePath)
    {
        if (treePath != null)
        {
            return getArray(Arrays.asList(treePath));
        }
        else
        {
            return getArray();
        }
    }
 
    public Object[] getArray(Collection treePath)
    {
        MerkleTree tree = getTreePath(treePath);
        return tree.getArray();
    }
 
    protected MerkleTree getTreePath(Collection treePath)
    {
        MerkleTree tree = this;
        Iterator iter = treePath.iterator();
        while (iter.hasNext())
        {
            Object temp = iter.next();
            tree = tree.getTree(temp);
        }
        return tree;
    }
 
    public int hashCode()
    {
        return data.hashCode() * 7;
    }
 
    public boolean equals(Object o)
    {
        if (!(o instanceof MerkleTree))
            return false;
        MerkleTree oo = (MerkleTree) o;
        if (oo.size() != this.size())
            return false;
        return data.equals(oo.data);
    }
 
    public Set keySet()
    {
        return data.keySet();
    }
 
    public MerkleTree search(Object key)
    {
        MerkleTree result = getTree(key);
        if (result != null)
        {
            return result;
        }
        TreeSearcher searcher = new TreeSearcher(key);
        try
        {
            traverse(searcher);
        }
        catch (Exception e)
        {
            // do nothing - means object is found
        }
        return searcher.getResult();
    }
 
    void readObject(ObjectInputStream ois) throws ClassNotFoundException,
            IOException
    {
        ois.defaultReadObject();
    }
 
    void writeObject(ObjectOutputStream oos) throws IOException
    {
        oos.defaultWriteObject();
    }
 
    public int size()
    {
        return data.size();
    }
 
    public void traverse(AppTraverser visitor)
    {
        Iterator iter = list().iterator();
        while (iter.hasNext())
        {
            Object item = iter.next();
            visitor.addNode(item, getTree(item));
            getTree(item).traverseInto(visitor);
        }
    }
 
    private void traverseInto(AppTraverser visitor)
    {
        if (list().size() == 0)
        {
            visitor.processPath();
        }
        else
        {
            Iterator iter = list().iterator();
            while (iter.hasNext())
            {
                Object item = iter.next();
                visitor.addNode(item, getTree(item));
                getTree(item).traverseInto(visitor);
            }
        }
        visitor.subtractNode();
    }
 
    public String toString()
    {
        ConvertToString converter = new ConvertToString();
        traverse(converter);
        return converter.toString();
    }
 
    protected Map data;
 
    
}