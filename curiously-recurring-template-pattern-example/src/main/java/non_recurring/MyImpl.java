package non_recurring;

public class MyImpl implements MyInterface<MyImpl>{
    @Override
    public MyImpl newInstance() {
        return new MyImpl();
    }
}
