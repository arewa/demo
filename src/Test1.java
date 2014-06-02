class Test1 
{
    public int value;
    public int hashCode() { return 42; }
}

class Test2 
{
    public int value;
    public int hashcode() { return (int)(value^5); }
}
