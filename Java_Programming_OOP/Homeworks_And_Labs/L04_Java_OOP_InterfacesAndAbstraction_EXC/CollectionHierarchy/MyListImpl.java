package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.CollectionHierarchy;

public class MyListImpl extends Collection implements Addable, AddRemovable, MyList {

    @Override
    public String remove() {
        return super.removeFirst();
    }

    @Override
    public int add(String item) {
        super.addFirst(item);
        return 0;
    }

    @Override
    public int getUsed() {
        return super.getItems().size();
    }
}