package com.viettel.imdb.util.compressor.lz4;

import net.jpountz.lz4.LZ4Factory;
import net.openhft.chronicle.bytes.Bytes;

import java.nio.ByteBuffer;

/**
 * Created by hai on 7/12/17.
 */
class LZ4NativeCompressor extends LZ4Compressor {
    private net.jpountz.lz4.LZ4Compressor compressor = LZ4Factory.fastestInstance().fastCompressor();
    private net.jpountz.lz4.LZ4FastDecompressor decompressor = LZ4Factory.fastestInstance().fastDecompressor();
    @Override
    public int compress(Bytes src, Bytes dst) {
        int size = (int)src.readRemaining();
        int bound = compressor.maxCompressedLength(size);
        dst.ensureCapacity(dst.writePosition() + bound);
        ByteBuffer srcBuffer = (ByteBuffer) src.underlyingObject();
        ByteBuffer dstBuffer = (ByteBuffer) dst.underlyingObject();
        int srcReadPosition = (int)src.readPosition();
        int dstWritePosition = (int)dst.writePosition();
        srcBuffer.limit(srcReadPosition + size);
        srcBuffer.position(srcReadPosition);
        dstBuffer.limit(dstWritePosition + bound);
        dstBuffer.position(dstWritePosition);
        int compressedSize = compressor.compress(srcBuffer, srcReadPosition, size,
                dstBuffer, dstWritePosition, bound);
        // Update position of src and dst
        src.readPosition(src.readLimit());
        dst.writePosition(dst.writePosition() + compressedSize);
        dst.readLimit(dst.writePosition());
        return compressedSize;
    }

    @Override
    public int decompress(Bytes src, Bytes dst, int originalSize) {
        dst.ensureCapacity(originalSize);
        dst.writeLimit(originalSize);
        ByteBuffer srcBuffer = (ByteBuffer) src.underlyingObject();
        ByteBuffer dstBuffer = (ByteBuffer) dst.underlyingObject();
        int srcWritePosition = (int)src.writePosition();
        int srcReadPosition = (int)src.readPosition();
        int dstWritePosition = (int)dst.writePosition();
        srcBuffer.limit(srcWritePosition);
        srcBuffer.position(srcReadPosition);
        dstBuffer.limit(dstWritePosition + originalSize);
        dstBuffer.position(dstWritePosition);
        int size = decompressor.decompress(srcBuffer, srcReadPosition,
                dstBuffer, dstWritePosition, originalSize);
        // Update position of src and dst
        src.readPosition(src.readPosition() + size);
        dst.writePosition(dst.writeLimit());
        //dst.readLimit(dstBuffer.limit());
        return size;
    }
}
