package com.viettel.imdb.type;

public enum MethodID {
    /**
     * Cluster APIs
     */
    XDR_INFO        (50),
    CLUSTER_INFO    (7),
    CLUSTER_TERM    (8),
    ECHO            (10),

    /**
     * Table APIs
     */
    CREATE          (11),
    TRUNCATE        (12),
    DROP            (13),
    CREATE_INDEX    (14),
    DROP_INDEX      (15),
    SINGLE_NODE_CREATE          (18),
    SINGLE_NODE_DROP            (19),
    SINGLE_NODE_CREATE_INDEX    (180),
    SINGLE_NODE_DROP_INDEX      (181),

    /**
     * Data APIs
     */
    SELECT          (20),
    INSERT          (21),
    UPDATE          (22),
    UPSERT          (23),
    DELETE          (24),
    REPLACE         (25),
    BEGIN_SCAN      (16),
    SCAN            (17),
    /**
     * Data APIs - extension
     */
    INCREMENT             (31),
    ADD_TO_LIST           (32),
    REMOVE_IN_LIST        (33),
    VERSATILE_OPERATION   (34),
    ALTERNATIVE_WRITE     (39),
    WRITE_AND_GET           (35),
    ALTERNATIVE_WRITE_AND_GET  (40),
    /**
     * For transaction two phase commit
     */
    TRANSACTION_PREPARE         (41),
    TRANSACTION_PREPARE_FAST    (42),
    TRANSACTION_COMMIT          (43),
    TRANSACTION_READ            (44),
    /**
     * Security module
     */
    SECURITY_LOGIN                       (120),
    //    AUTHENTICATE                (121), // todo :-?
    SECURITY_CHANGE_PASSWORD             (122),
    SECURITY_CREATE_USER                 (123),
    SECURITY_UPDATE_USER                 (124),
    SECURITY_DELETE_USER                 (125),
    SECURITY_CREATE_ROLE                 (126),
    SECURITY_UPDATE_ROLE                 (127),
    SECURITY_DELETE_ROLE                 (128),
    SECURITY_READ_ROLE                   (129),
    SECURITY_REFRESH_TOKEN               (130),
    //    SECURITY_ROLE_UPDATE_USER            (131),
//    SECURITY_USER_UPDATE_ROLE            (132),
    SECURITY_READ_USER                   (133),
    SECURITY_GRANT                       (157),
    SECURITY_REVOKE                      (158),
    SECURITY_REVOKE_ALL_ROLE             (159),

    /**
     * Addition for REST APIs
     */
    GET_NAMESPACES                      (170),
    GET_TABLES                          (171),
    GET_RECORDS                         (172),
    GET_TABLE_INFO                      (173),
    GET_PROMETHEUS_ADDRESS              (202)


    ;
    private final short methodID;
    // Haint - use int to void convert from int to short
    MethodID(int methodID){
        this.methodID = (short)methodID;
    }
    public short get() {
        return methodID;
    }
}
