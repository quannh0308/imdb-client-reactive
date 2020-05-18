package com.viettel.imdb.core;

import org.pmw.tinylog.Logger;

import java.security.SecureRandom;

public class ReactorConnection extends Connection {
    // todo physical connection

    @Override
    public void send() {
        Logger.info("Send message >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // inner process here
        byte[] requestBytes = request.getSerializedData();

        try {
            Thread.sleep(new SecureRandom().nextInt(10000));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // receive will be triggered later


        if (new SecureRandom().nextBoolean())
            receiveValue();
        else
            receiveException();
    }
}
