package recurring;

public class MyRecurImpl implements MyRecur<MyRecurImpl>{
    @Override
    public MyRecurImpl newInstance() {
        return new MyRecurImpl();
    }
}
