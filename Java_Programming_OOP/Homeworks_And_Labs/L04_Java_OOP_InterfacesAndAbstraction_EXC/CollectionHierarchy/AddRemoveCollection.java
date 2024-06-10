package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.CollectionHierarchy;

public class AddRemoveCollection extends Collection implements AddRemovable, Addable {
    @Override
    public String remove() {
        return super.removeLast();
    }

    @Override
    public int add(String item) {
        super.addFirst(item);
        return 0;
    }
}
