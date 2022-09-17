package br.com.tiagopedroso.moonprobe.model;

public enum Command {
    /**
     * M = move forward 1 position
     */
    M,

    /**
     * L = turns left (90°)
     */
    L,

    /**
     * R = turns right (90°)
     */
    R,

    /**
     * BACK = for cases of return to previous positions
     */
    BACK,

    /**
     * NULL = for cases of invalid commands
     */
    NULL
}
