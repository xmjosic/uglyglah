package hr.xmjosic.uglyglah.model;

import hr.xmjosic.uglyglah.exceptions.UglyglahException;

import java.util.Arrays;

public enum VoteType {

    UPVOTE(1),
    DOWNVOTE(-1);

    private int direction;

    VoteType(int direction) {
    }

    public static VoteType lookup(Integer direction) {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new UglyglahException("Vote not found!"));
    }

    private Integer getDirection() {
        return direction;
    }
}
