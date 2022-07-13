package non_recurring;

public class EvilMyImpl implements MyInterface<AnotherClass>{
    @Override
    public AnotherClass newInstance() {
        return new AnotherClass();
    }
}
