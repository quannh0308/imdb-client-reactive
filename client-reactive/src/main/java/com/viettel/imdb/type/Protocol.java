package com.viettel.imdb.type;

public class Protocol {
    public static int FLAG_SIZE = 2;
    public static int BOOLEAN_SIZE = 1;
    public static int MESSAGE_SIZE = 4;
    public static int ERROR_SIZE = 2;
    public static int COMPRESS_SIZE = 4;
    public static int MSGID_TYPE = 4;
    public static int MSGID_OFFSET_SIZE = FLAG_SIZE + ERROR_SIZE;
    public static int FLAG_HEADER_SIZE = MSGID_OFFSET_SIZE + MSGID_TYPE + MESSAGE_SIZE; // 12 bytes
    public static int MULTI_HREADER_SIZE = BOOLEAN_SIZE + ERROR_SIZE + MSGID_TYPE + MESSAGE_SIZE; // 15 bytes
    public static int UNCOMPRESSED_HEADER_SIZE = ERROR_SIZE + MSGID_TYPE;
    public static int MAX_SIZE_TO_COMPRESS = 1 << 8;
    public static int MAX_REQUEST_SIZE = 1 << 16; // 64K
}
