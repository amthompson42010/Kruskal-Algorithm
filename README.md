#Introduction 

Your task is to implement Kruskal's algorithm using disjoint sets. The disjoint set version must implement union by rank as well as path compression during find-set. 

#Input 

Your program will process a file containing a description of a graph. A graph description contains an arbitrary number of edge descriptions. An edge description consists of two vertex descriptions (optionally followed by a weight) followed by a semicolon. A vertex description is simply a non-negative integer. If a weight is omitted, a weight of 1 should be assumed. A weight is a positive integer. 

The file should be free format; whitespace may appear anywhere. Here a sample graph description: 
        
        1 5 ;
        2
        10
        23 ;

        214 33 1
        ;

which is equivalent to: 
        
        1 5;
        2 10 23 ;
        214 33 1 ;

In this example there are six vertices, named 1, 2, 5, 10, 33, and 214, and three edges, 1 to 5, 2 to 10 and 214 to 33, with weights 1, 23, and 1, respectively. 

The name of your executable must be kruskal and the name of the file describing the graph will be passed to your program as a command line argument, as in: 
        
        $ cat g1
        1 2 1 ;
        2 3 2 ;
        3 1 3 ;
        $ kruskal g1
        [output appears here]

Your program should be capable of interpreting the graph description as an undirected graph. 

Your program should report a minimum spanning tree covering all vertices reachable from the root. The root is specified by the -r option. For example, to specify vertex 10 as the root, kruskal would be called similarly to: 

        $ kruskal -r 10 graph

where graph is the name of the file containing the graph description. If the -r option is not given, the root should default to the first vertex mentioned in the graph description. Options are given prior to the file name of the graph and can occur in any order. 

#Output 

The output of your program should be a spanning tree displayed as an adjacency list. The adjacency list should be sorted both vertically and horizontally. If some vertices are not reachable, your program should report this situation with the message: 

        n vertices were not reachable

with n being the number of unreachable vertices. The total weight of the tree should also be displayed. Here is an example display (note, the actual weights are not given, an x is shown instead): 

        $ cat g2
        10 0 x;
        6 7 x;
        5 9 x;
        4 8 x;
        1 6 x;
        0 6 x ; 0 5 x ; 0 4 x; 0 3 x; 0 1 x; 
        $ kruskal -r 0 g2
        A minimum spanning tree is:
        0: 1(0)x 3(0)x 4(0)x 5(0)x
        1: 6(1)x 7(5)x
        3: 8(5)x
        The total weight of the tree is 47.
        3 vertices were not reachable from root 0.
        $

You should perform a breadth-first (level-order) search from the starting vertex. Each level should be preceded by the level number, followed by the vertices at the level. Each vertex in a level is followed by its predecessor (in parentheses) followed by the weight of the edge from the predecessor to the vertex in question. The vertices in a level should be ordered by increasing edge weight. You must follow the format exactly as diff will be used to assess your output. 

If all vertices are reachable, the message should be similar to: 
        
        All vertices were reachable from root 0.

#Program organization 

You must implement the disjoint set code as a separate class or module. You must also name your disjoint set methods or functions as follows: 
        
        findSet
        union
        makeSet

#Other details 

You may implement your program in any language you wish, as long as I can compile or interpret your source code on beastie.cs.ua.edu. Only the most foolish student would not recompile and thoroughly test the implementation on a Linux system. 

You must provide a makefile which responds properly to the commands make make test, and make clean. The make command must compile your program with no errors or warnings and it must compile with the highest level of error checking (the -Wall option for gcc and g++). The make test command should run your program through some test files of your choosing. The make clean command should remove all intermediate files, such as .class, .o, and .pyc files. 

Only the most foolish student would not recompile and thoroughly test the implementation on a Linux system. If the language you chose does not provide for a direct executable, provide an appropriate shellscript named kruskal which runs your program. Here is an example for Java: 
    
        $ echo java -classpath classfiles main.Kruskal \$\* > kruskal
        $ chmod +x kruskal

and here is an example for Python: 
    
        $ echo python3 kruskal.py \$\* > kruskal
        $ chmod +x kruskal

In order for your program to run on a randomly created graph, if an edge is given more than once, ignore subsequent occurrences. For undirected graphs, if a u	,	v edge is given, ignore subsequent u	,	v and v	,	u edges, since the presence of a u	,	v edge implies the presence of a v	,	u edge. 