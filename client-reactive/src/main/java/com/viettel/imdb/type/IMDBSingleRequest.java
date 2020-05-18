package com.viettel.imdb.type;

import com.viettel.imdb.core.*;
import com.viettel.imdb.util.compressor.Compressor;
import com.viettel.imdb.util.compressor.CompressorFactory;
import com.viettel.imdb.util.compressor.CompressorType;
import com.viettel.imdb.util.serializer.DataSerializer;
import net.openhft.chronicle.bytes.Bytes;
import reactor.core.publisher.Mono;

public abstract class IMDBSingleRequest<T extends IMDBSingleResponse> {
    private Mono<T> mono;
    private Engine engine;

    private Connection connection; // result of route(). connection has information of EventLoop it will be executed
    private AuthToken token; // result of auth()
    private Bytes buffer;
    // private byte[] serializedData;

    public Mono<T> getMono() {
        return mono;
    }

    public IMDBSingleRequest() {
        engine = Engine.just();
    }

    public IMDBSingleRequest<T> route() {
        // acquire connection
        connection = engine.acquireConnection(this::getNode);
        return this;
    }

    /*public IMDBSingleRequest<T> auth() {
        // no need this one
        token = engine.getAuth().getToken(connection);
        return this;
    }*/

    public IMDBSingleRequest<T> send() {
        mono = Mono.create(sink -> {
            connection
                    .attachRequest(this, sink)
                    .send();
        });
        return this;
    }

    public abstract Node getNode(Cluster cluster);

    public IMDBSingleRequest<T> pack() {
        serializeHeader();
        serialize(buffer);
        // serializedData = serialize();
        compress();
        return this;
    }

    private void serializeHeader() {
        buffer.clearAndPad(Protocol.FLAG_HEADER_SIZE);
        DataSerializer.writeToken(buffer, token);
        // todo write header here :-?
    }

    // public abstract byte[] serialize();
    public abstract void serialize(Bytes data); // todo Bytes or IMDBBytes for general, not depends on OpenHFT bytes


    private void compress() {
        Bytes compressedBuffer = null; // todo here
        // get the real size
        int size = (int)(buffer.readLimit() - Protocol.FLAG_HEADER_SIZE);
        if(size < Protocol.MAX_SIZE_TO_COMPRESS) {
            buffer.prewriteInt(size);
            // write message ID, leving 0 for later write
            buffer.prewriteInt(0);
            // write method ID
            buffer.prewriteShort(getMethodID().get());
            // write Flag
            buffer.prewriteShort(CompressorType.NO_COMPRESSOR.getValue());
        } else {
            // compress
            Compressor compressor = CompressorFactory.getCompressor(CompressorType.LZ4);
            compressedBuffer.clearAndPad(Protocol.FLAG_HEADER_SIZE + Protocol.COMPRESS_SIZE);
            int compressedSize = compressor.compress(buffer, compressedBuffer);
            compressedBuffer.prewriteInt(compressedSize);
            compressedBuffer.prewriteInt(size);
            // write messageID, leaving 0 for later write
            compressedBuffer.prewriteInt(0);
            // write methodID
            compressedBuffer.prewriteShort(getMethodID().get());
            // write Flag
            compressedBuffer.prewriteShort(CompressorType.LZ4.getValue());
        }

    }



    public byte[] getSerializedData() {
        return buffer.toByteArray(); // todo - or copy directly to network card
    }



    public abstract T deserializeValue();

    public abstract Throwable deserializeException();

    public abstract MethodID getMethodID();
}
