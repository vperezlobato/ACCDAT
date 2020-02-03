package clase;

import java.util.Comparator;

public class ComparatorRoll implements Comparator<TipoPersona>
{
    public int compare(TipoPersona a, TipoPersona b)
    {
        return a.id - b.id;
    }
}
