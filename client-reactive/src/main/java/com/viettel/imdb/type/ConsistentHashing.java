package com.viettel.imdb.type;

import com.viettel.imdb.core.Cluster;
import com.viettel.imdb.core.Hashing;
import com.viettel.imdb.core.Node;

public class ConsistentHashing implements Hashing {
    private static ConsistentHashing instance;
    public static ConsistentHashing getInstance() {
        if(instance == null)
            instance = new ConsistentHashing();
        return instance;
    }
    private ConsistentHashing() {

    }

    @Override
    public Node getNode(Cluster cluster, TableAndKey key) {
        return cluster.getLeader(); // todo here
    }
}
