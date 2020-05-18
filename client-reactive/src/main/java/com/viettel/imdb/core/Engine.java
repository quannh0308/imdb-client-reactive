package com.viettel.imdb.core;

import com.viettel.imdb.type.ClientConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

// import reactor.core.scheduler.Scheduler;

public class Engine {
    // todo work as seastar::distributed<IMDBScheduler>
    // todo should support action like executing in one IMDBScheduler and apply result to other Schedulers
    // todo actions like: ClusterTask, AuthTask
    public static final AtomicInteger CURRENT_SCHEDULER_IDX = new AtomicInteger(); // round-robin to choose scheduler
    // todo: Singleton. Use getInstance() instead of just()
    ClientConfig config;
    List<IMDBScheduler> schedulers;


    public static Engine just() {
        return new Engine();
    }

    private Engine() {
        config = ClientConfig.just();
        schedulers = new ArrayList<>();
        schedulers.add(IMDBScheduler.just()); // todo config number of schedulers
    }

    public Engine connect() {
        schedulers.forEach(IMDBScheduler::connect); // todo should be one, after that, invokeOnAll to apply to other
        return this;
    }

    public Engine authen() {
        schedulers.forEach(IMDBScheduler::authen); // todo should be one, after that, invokeOnAll to apply to other
        return this;
    }

    public Engine start() {
        schedulers.forEach(IMDBScheduler::start); // todo should be one, after that, invokeOnAll to apply to other
        return this;
    }

    public Connection acquireConnection(Function<Cluster, Node> routeFunction) {
        IMDBScheduler nextScheduler = nextScheduler(); // round-robin scheduler
        Cluster nextCluster = nextScheduler.getCluster(); // cluster with round robin :-?. Or just ONE :-?. Or just CONFIGURATION Cluster. Not real Cluster
        Node node = routeFunction.apply(nextCluster); // get node of that cluster. just CONFIGURATION
        Connection connection = node.nextConnection(); // get a node from ConnectionPool of that node
        connection.setScheduler(nextScheduler); // assign the connection to be processed by the specific scheduler
        return connection;
    }

    private IMDBScheduler nextScheduler() {
        return schedulers.get(CURRENT_SCHEDULER_IDX.getAndIncrement() % schedulers.size()); // round-robin. Todo : other :-?
    }
}
