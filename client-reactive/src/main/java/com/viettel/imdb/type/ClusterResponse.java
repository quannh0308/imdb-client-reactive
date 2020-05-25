package com.viettel.imdb.type;

import java.util.List;

public class ClusterResponse extends IMDBSingleResponse {
    private final int replication;
    private final long index;
    private final long term;
    private final String leader; // type Host
    private final List<String> hosts; // type List<Host>
    private final boolean isSecondaryHostsChange;

    public ClusterResponse(int replication, long index, long term, String leader, List<String> hosts, boolean isSecondaryHostsChange) {
        this.replication = replication;
        this.index = index;
        this.term = term;
        this.leader = leader;
        this.hosts = hosts;
        this.isSecondaryHostsChange = isSecondaryHostsChange;
    }

    public int getReplication() {
        return replication;
    }

    public long getIndex() {
        return index;
    }

    public long getTerm() {
        return term;
    }

    public String getLeader() {
        return leader;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public boolean isSecondaryHostsChange() {
        return isSecondaryHostsChange;
    }

    @Override
    public String toString() {
        return "ClusterResponse{" +
                "replication=" + replication +
                ", index=" + index +
                ", term=" + term +
                ", leader='" + leader + '\'' +
                ", hosts=" + hosts +
                ", isSecondaryHostsChange=" + isSecondaryHostsChange +
                '}';
    }
}
