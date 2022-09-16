package br.com.tiagopedroso.moonprobe.model;

public enum Orientation {

    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static Orientation getNext(boolean rotateLikeClock, Orientation current) {
        var orientations = Orientation.values();

        for (int index = 0; index < orientations.length; index++) {
            if (current == orientations[index]) {

                if (rotateLikeClock) {
                    index++;
                    index = index >= orientations.length ? 0 : index;
                } else {
                    index--;
                    index = index < 0 ? orientations.length -1 : index;
                }

                current = orientations[index];
                break;
            }
        }

        return current;
    }

}
