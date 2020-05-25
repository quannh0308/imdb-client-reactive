package com.viettel.imdb.core;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import org.pmw.tinylog.Logger;
import reactor.core.publisher.Mono;
import reactor.netty.tcp.TcpClient;

import java.security.SecureRandom;

public class ReactorConnection extends Connection {
    // todo not only host-port, but other kind of addresses
    private String host;
    private int port;
    private reactor.netty.Connection physicalConnection;
    // todo physical connection

    public ReactorConnection(String inputHost) {
        this.host = inputHost.substring(0, inputHost.indexOf(":"));
        this.port = Integer.valueOf(inputHost.substring(inputHost.indexOf(":") + 1));
        Logger.error("Try to connect to " + inputHost);
        physicalConnection = TcpClient.create()
                .host(host)
                .port(port)
                .connectNow();
    }

    @Override
    public void send() {

        Logger.info("Send message >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // inner process here
        byte[] requestBytes = request.getSerializedData();
        physicalConnection.outbound().sendByteArray(subscriber -> subscriber.onNext(requestBytes));
        String response = new String(physicalConnection.inbound().receive().asByteArray().blockFirst());
        System.out.println("Response: " + response);

        /*try {
            Thread.sleep(new SecureRandom().nextInt(10000));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // receive will be triggered later


        if (new SecureRandom().nextBoolean())
            receiveValue();
        else
            receiveException();*/
    }
}
