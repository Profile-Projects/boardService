package com.boardService.models;

public enum BoardMemberPermission {
    WRITE("WRITE"),
    REVIEW("REVIEW"),
    VIEW("VIEW");

    public final String label;

    private BoardMemberPermission(final String label) {
        this.label = label;
    }
}
