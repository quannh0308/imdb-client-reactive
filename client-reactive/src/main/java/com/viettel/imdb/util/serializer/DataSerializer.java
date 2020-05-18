package com.viettel.imdb.util.serializer;

import com.viettel.imdb.core.AuthToken;
import net.openhft.chronicle.bytes.Bytes;

public class DataSerializer {
    // todo functions can return Bytes to support code with REACTIVE STYLE
    // todo static function or singleton instance :-?
    public static void writeToken(Bytes buffer, AuthToken data) {
        writeString(buffer, data.getToken());
    }
    public static void writeString(Bytes buffer, String data) {
        buffer.writeInt(data.length());
        buffer.write(data.getBytes());
    }
    public static void writeBytes(Bytes buffer, byte[] data) {
        buffer.writeInt(data.length);
        buffer.write(data);
    }
}
