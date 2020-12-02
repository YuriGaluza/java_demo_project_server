package net.javademo.project.recruitment.model;

import net.javademo.project.recruitment.server.ServerErrorCode;
import net.javademo.project.recruitment.server.ServerException;

import java.util.Objects;

public class Skill {

    private String name;
    private Integer level;
    private static final int MAX_LEVEL_VALUE = 5;
    private static final int MIN_LEVEL_VALUE = 1;

    public Skill(String name, Integer level) throws ServerException {
        setName(name);
        setLevel(level);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ServerException {
        if (name.equals(null) || name.equals(""))
            throw new ServerException(ServerErrorCode.NAME_IS_EMPTY);
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) throws ServerException {
        if (level < MIN_LEVEL_VALUE || level > MAX_LEVEL_VALUE)
            throw new ServerException(ServerErrorCode.WRONG_LEVEL);
        this.level = level;
    }

    public static int getMaxLevelValue() {
        return MAX_LEVEL_VALUE;
    }

    public static int getMinLevelValue() {
        return MIN_LEVEL_VALUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(name, skill.name) &&
                Objects.equals(level, skill.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }
}
