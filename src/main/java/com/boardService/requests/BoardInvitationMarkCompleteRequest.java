package com.boardService.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BoardInvitationMarkCompleteRequest {
    @JsonProperty("email")
    @NonNull
    private String email;

    @JsonProperty("board_sid")
    @NonNull
    private String boardSid;

    @JsonProperty("name")
    @NonNull
    private String name;
}
