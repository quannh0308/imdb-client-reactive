package com.viettel.imdb.core;

import com.viettel.imdb.type.TableAndKey;

public interface Hashing {
    Node getNode(Cluster cluster, TableAndKey key);
}
