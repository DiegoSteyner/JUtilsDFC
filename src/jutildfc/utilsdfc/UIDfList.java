package jutildfc.utilsdfc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.documentum.fc.common.DfId;
import com.documentum.fc.common.IDfId;

@SuppressWarnings({ "rawtypes" })
public class UIDfList
{
    public static List<IDfId> getList(String... objects) throws Exception
    {
        List<IDfId> retorno = new ArrayList<IDfId>();

        for (String id : objects)
        {
            retorno.add(new DfId(id));
        }
        return (retorno);
    }

    public static Map<String, Object> getMap(String[] keys, String values[]) throws Exception
    {
        Map<String, Object> retorno = new HashMap<String, Object>();

        for (int i = 0; i < keys.length; i++)
        {
            retorno.put(keys[i], values[i]);
        }
        return (retorno);
    }

    public static Map<String, IDfId> getMap(String[] keys, IDfId values[]) throws Exception
    {
        Map<String, IDfId> retorno = new HashMap<String, IDfId>();

        for (int i = 0; i < keys.length; i++)
        {
            retorno.put(keys[i], values[i]);
        }
        return (retorno);
    }

    public static void printList(List list) throws Exception
    {
        for (Object obj : list)
        {
            System.out.println(obj.toString());
        }
    }
}
