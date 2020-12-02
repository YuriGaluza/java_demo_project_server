package net.javademo.project.recruitment.model;

import net.javademo.project.recruitment.server.ServerException;

import java.util.Objects;

public class Requirement extends Skill {

    private boolean mandatory;

    public Requirement(String name, Integer level, boolean mandatory) throws ServerException {
        super(name, level);
        this.mandatory = mandatory;
    }

    public Requirement(String name, Integer level) throws ServerException {
        super(name, level);
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Requirement that = (Requirement) o;
        return mandatory == that.mandatory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mandatory);
    }
}


