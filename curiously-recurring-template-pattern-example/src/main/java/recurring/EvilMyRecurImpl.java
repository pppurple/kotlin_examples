package recurring;

import non_recurring.AnotherClass;

public class EvilMyRecurImpl implements MyRecur<AnotherClass>{
    @Override
    public AnotherClass newInstance() {
        return new AnotherClass();
    }
}
