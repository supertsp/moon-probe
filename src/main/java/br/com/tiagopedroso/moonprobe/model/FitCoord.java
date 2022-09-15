package br.com.tiagopedroso.moonprobe.model;

import br.com.tiagopedroso.moonprobe.infra.exception.FitCoordException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class FitCoord {

    private Integer
            x = 0,
            y = 0;

    private Integer
            widthLimit = 0,
            heightLimit = 0;

    public FitCoord(Integer widthLimit, Integer heightLimit) {
        setWidthLimit(widthLimit);
        setHeightLimit(heightLimit);
    }

    public FitCoord(Integer x, Integer y, Integer widthLimit, Integer heightLimit) {
        setWidthLimit(widthLimit);
        setHeightLimit(heightLimit);
        setX(x);
        setY(y);
    }

    public void setX(Integer x) {
        if (x >= 0 && x <= widthLimit) {
            this.x = x;
        } else {
            throw new FitCoordException("x", x, "widthLimit", widthLimit);
        }
    }

    public void setY(Integer y) {
        if (y >= 0 && y <= heightLimit) {
            this.y = y;
        } else {
            throw new FitCoordException("y", y, "heightLimit", heightLimit);
        }
    }

    public void setWidthLimit(Integer widthLimit) {
        if (widthLimit >= 0) {
            this.widthLimit = widthLimit;

            if (x > widthLimit) {
                x = widthLimit;
            }
        } else {
            throw new FitCoordException("widthLimit", widthLimit);
        }
    }

    public void setHeightLimit(Integer heightLimit) {
        if (heightLimit >= 0) {
            this.heightLimit = heightLimit;

            if (y > heightLimit) {
                y = heightLimit;
            }
        } else {
            throw new FitCoordException("heightLimit", heightLimit);
        }
    }

    public void incrementX(Integer value) {
        x += value;

        if (x < 0) {
            x = 0;
        }

        if (x > widthLimit) {
            x = widthLimit;
        }
    }

    public void incrementY(Integer value) {
        y += value;

        if (y < 0) {
            y = 0;
        }

        if (y > heightLimit) {
            y = heightLimit;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getWidthLimit(), getHeightLimit());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FitCoord fitCoord)) return false;

        return Objects.equals(getX(), fitCoord.getX()) &&
                Objects.equals(getY(), fitCoord.getY()) &&
                Objects.equals(getWidthLimit(), fitCoord.getWidthLimit()) &&
                Objects.equals(getHeightLimit(), fitCoord.getHeightLimit());
    }

    @Override
    public String toString() {
        return """
                FitCoord {
                    "x": %d,
                    "y": %d,
                    "widthLimit": %d,
                    "heightLimit": %d
                }"""
                .formatted(
                        x,
                        y,
                        widthLimit,
                        heightLimit
                );
    }
}
