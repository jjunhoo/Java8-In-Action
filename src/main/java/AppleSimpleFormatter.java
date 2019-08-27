public class AppleSimpleFormatter implements AppleFormater {
    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}
