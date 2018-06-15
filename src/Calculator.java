/**
 * The main class of a simple calculator. Create one of these and you'll
 * get the calculator on screen.
 *
 * @author David Panagiotopulos and Bernhard Hoffmann | Original: David J. Barnes and Michael Kolling
 * @version 2018.05.24
 */
public class Calculator
{
    public static void main(String[] args) {
        new Calculator();
    }
    /**
     * Create a new calculator and show it.
     */
    private Calculator()
    {
        CalcEngineSet engine = new CalcEngineSet();
        new UserInterfaceSet(engine);
    }
}
