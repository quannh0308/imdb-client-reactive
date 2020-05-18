package com.viettel.imdb.core;

import com.viettel.imdb.type.ClientConfig;
import com.viettel.imdb.type.ClusterRequest;
import com.viettel.imdb.type.ClusterResponse;
import com.viettel.imdb.type.exception.ErrorCode;
import com.viettel.imdb.type.exception.IMDBException;
import org.pmw.tinylog.Logger;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.viettel.imdb.core.Transporter.sendSingleRequest;
import static com.viettel.imdb.util.validator.Validator.throwIMDBException;

public class Cluster {
    // todo: Note: Work as both Cluster and ClusterTask in current imdb-client implementation
    ClientConfig config;
    private List<String> hosts;
    private List<Node> nodes;
    private Node leader;

    public static Cluster just() {
        return new Cluster();
    }

    public Cluster() {
        config = ClientConfig.just();
        this.hosts = config.getHosts();
        nodes = new ArrayList<>();
        hosts.forEach(host -> nodes.add(new Node(host)));
        leader = nodes.get(0);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Node getLeader() {
        return leader;
    }

    public void connect() {
        getClusterInfo()
                .map(this::applyCluster)
                .map(this::scheduleUpdateCluster).subscribe(); // todo: trigger immediately and blocked
    }

    public Mono<ClusterResponse> getClusterInfo() {
        // todo validate client state READY :-?
        // todo recursive Tailrec in case of failure :-?, not only leader
        return sendSingleRequest(new ClusterRequest(leader.getHost()));
    }

    public ClusterResponse applyCluster(ClusterResponse response) {
        // todo apply here
        return response;
    }

    public ClusterResponse scheduleUpdateCluster(ClusterResponse clusterResponse) {
        // todo schedule here - timer
        return clusterResponse;
    }

    public Node getNodeByHost(String host) throws IMDBException {
        try {
            return nodes.stream().filter(node -> node.getHost().equals(host)).collect(Collectors.toList()).get(0);
        } catch (Exception ex) {
            Logger.error("Node {} does not exist in cluster {}", host, nodes.stream().map(Node::getHost).collect(Collectors.toList()));
            ex.printStackTrace();
            throwIMDBException(ErrorCode.INVALID_ERROR, ex);
            return null;
        }
    }
}
