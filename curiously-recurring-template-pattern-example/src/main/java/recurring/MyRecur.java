package recurring;

public interface MyRecur<SELF extends MyRecur<SELF>>{
    SELF newInstance();
}
