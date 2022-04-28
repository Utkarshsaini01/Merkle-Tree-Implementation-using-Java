# Merkle-Tree-Implementation-using-Java
Merkle tree also known as hash tree is a data structure used for data verification and synchronization.  
It is a tree data structure where each non-leaf node is a hash of it’s child nodes.  
All the leaf nodes are at the same depth and are as far left as possible. It maintains data integrity and uses hash functions for this purpose. 



## Algorithm 

In cryptography and software engineering a hash tree or Merkle tree is a tree in the each
non-leaf hub is named with the hash of the marks of its youngsters hubs. Hash trees are helpful
since they permit effective and secure check of the items in bigger information structures. Hash
trees are a speculation of hash records and hash chains. Showing that a leaf hub is a piece of
the given hash tree requires handling a measure of information corresponding to the logarithm of the
number of hubs of the tree this differences with hash records, they sum is corresponding to the
number of hubs. A hash tree is a tree of hashes in the leaves is hashes of information blocks in, for
example, a record or set of documents. Hubs further up in the tree are the hashes of their individual
youngsters. For instance in the image hash 0 is the consequence of hashing the outcome
of connecting hash 0-0 and hash 0-1. That is, hash 0 = hash( hash 0-0 + hash 0-1 ) where +
signifies connection. Most hash tree executions are parallel (two kid hubs under each
hub) however they can similarly also utilize a lot more kid hubs under every hub.

Generally, a cryptographic hash capacity, for example, SHA-2 or SHA-3 is utilized for the hashing. Assuming the hash tree
just has to safeguard against accidental harm, substantially less secure checksums such
as CRs can be utilized.

In the highest point of a hash tree there is a top hash (or root hash or expert hash). The document on a
p2p network, as a rule the top hash is procured from a confided in source, for example a companion or
a site that is known to have great proposals of documents. The top hash is accessible, the
hash tree can be gotten from any non-confided in source, similar to any companion in the p2p network. Then,
the got hash tree is checked against the confided in top hash, and the hash tree is harmed or
phony, another hash tree from one more source will be attempted until the program observes one that matches
the top hash. The primary contrast from a hash list is that one part of the hash tree can be
downloaded at a time and the uprightness of each branch can be checked right away, despite the fact that
the entire tree isn't accessible yet. For instance, in the image, the trustworthiness of information block 2 can
be checked right away in the event that the tree as of now contains hash 0-0 and hash 1 by hashing the information
block and iteratively consolidating the outcome with hash 0-0 and afterward hash 1 lastly looking at
the outcome with the top hash. Essentially, the uprightness of information block 3 can be checked if the tree
as of now has hash 1-1 and hash 0. This can be a benefit since it is proficient to separate records in
tiny information impedes so that possibly little squares must be re-downloaded assuming they get harmed.
Assuming the hashed document is exceptionally enormous, such a hash tree or hash list turns out to be genuinely large. However, assuming it is a tree,
one little branch can be downloaded rapidly, the uprightness of the branch can be checked, and
then, at that point, the downloading of information squares can begin. There are a few extra deceives, benefits and
insights about hash trees. See the references and outside joins underneath for more inside and out
data.

