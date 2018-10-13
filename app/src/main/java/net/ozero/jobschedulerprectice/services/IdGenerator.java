package net.ozero.jobschedulerprectice.services;

public class IdGenerator {

    public static final IdGenerator INSTANCE = new IdGenerator();
    private int id = 0;

    private IdGenerator() {}

    public int getNextId() {
        return ++id;
    }
}
