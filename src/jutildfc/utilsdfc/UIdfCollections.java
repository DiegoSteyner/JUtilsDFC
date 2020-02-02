package jutildfc.utilsdfc;

import java.util.ArrayList;
import java.util.List;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.common.IDfValue;

public class UIdfCollections
{

    /**
     * Metodo que retorna um atributo de uma colecao
     * 
     * @param collection A colecao que se deseja conseguir o objeto
     * @param attributeName O nome do atributo procurado
     * @param autoClose	Se True, A Coleção será fechada automaticamente
     * 
     * @return O IDfValue do atributo
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static IDfValue getAttributeValue(IDfCollection collection, String attributeName, boolean autoClose) throws Exception
    {
        if (collection.next())
        {
            IDfValue v = getAttributeValueAtInterator(collection, attributeName);

            if (autoClose)
            {
                closeCollection(collection);
            }

            return (v);
        }
        return (null);
    }

    /**
     * Metodo que retorna uma coluna da collection
     * em forma de lista
     * 
     * @param collection A collection
     * @param attributeName O Nome da coluna
     * @param autoClose	Se True, A Coleção será fechada automaticamente
     * 
     * @return A coluna em forma de lista
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static List<IDfValue> getListAtributeValue(IDfCollection collection, String attributeName, boolean autoClose) throws Exception
    {
        List<IDfValue> retorno = new ArrayList<IDfValue>();

        while (collection.next())
        {
            retorno.add(getAttributeValueAtInterator(collection, attributeName));
        }
        return (retorno);
    }

    /**
     * Metodo que retorna o valor de um atributo na posição atual
     * Obs: O Interador "Next()" deve ser chamado externamente.
     * 
     * @param collection A colecao
     * @param attributeName O nome do atributo
     * 
     * @return O IDfValue do atributo
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static IDfValue getAttributeValueAtInterator(IDfCollection collection, String attributeName) throws Exception
    {
        for (int i = 0; i < collection.getAttrCount(); i++)
        {
            if (collection.getAttr(i).getName().equals(attributeName))
            {
                return (collection.getValue(attributeName));
            }
        }
        return (null);
    }

    /**
     * Método que retorna as colunas que estão presentes na coleção
     * 
     * @param collection A coleção
     * @param autoClose Se True, A Colecao sera fechada automaticamente
     * 
     * @return Um List com os valores da coluna
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static List<String> getColumnsNames(IDfCollection collection, boolean autoClose) throws Exception
    {
        List<String> retorno = new ArrayList<String>();

        while (collection.next())
        {
            for (int i = 0; i < collection.getAttrCount(); i++)
            {
                retorno.add(collection.getAttr(i).getName());
            }
            break;
        }
        if (autoClose)
        {
            closeCollection(collection);
        }
        return (retorno);
    }

    /**
     * Metodo que mostra todos os resultados de uma collection
     * 
     * @param collection A collection que se deseja exibir os resultados
     * @param autoClose Se True, a coleção será fechada automaticamente.
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static void displayCollection(IDfCollection collection, boolean autoClose) throws Exception
    {
        int resItems = 1;
        String name = "";
        String valu = "";

        while (collection.next())
        {
            System.out.println("Result row: ".concat(String.valueOf(resItems++)));

            for (int i = 0; i < collection.getAttrCount(); i++)
            {
                name = collection.getAttr(i).getName().trim();
                name = "\t".concat(name).concat(formatStringWithSpace(name, 30)).concat(": ");

                if (collection.getAttr(i).isRepeating())
                {
                    System.out.println();
                    String v[] = collection.getAllRepeatingStrings(collection.getAttr(i).getName().trim(), "#").split("#");
                    String t = "[";

                    for (int k = 0; k < v.length; k++)
                    {
                        t += String.valueOf(k).concat("] ");
                        t += v[k];

                        valu += t.concat("\n").concat("\t").concat(formatStringWithSpace("", 30));
                        t = "[";
                    }
                }
                else
                {
                    valu = collection.getValueAt(i).asString();
                }

                System.out.println(name.concat(valu));
                valu = "";
                name = "";
            }

            System.out.println("--------------------------------------------------------------- \n");
        }
        if (autoClose)
        {
            closeCollection(collection);
        }
    }

    /**
     * Metodo que mostra todos os resultados para um determinado atributo de uma collection
     * 
     * @param collection A collection que se deseja exibir os resultados
     * @param attribute O atributo que se deseja imprimir
     * @param autoClose Se True, a coleção será fechada automaticamente.
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static void displayAttributeCollection(IDfCollection collection, String attribute, boolean autoClose) throws Exception
    {
        int resItems = 1;
        String name = "";
        String valu = "";
        String attr[] = attribute.split(",");

        while (collection.next())
        {
            System.out.println("Result row: ".concat(String.valueOf(resItems++)));

            for (int i = 0; i < collection.getAttrCount(); i++)
            {
                for (int j = 0; j < attr.length; j++)
                {
                    if ((name = collection.getAttr(i).getName().trim()).equals(attr[j]))
                    {
                        name = "\t".concat(name).concat(formatStringWithSpace(name, 30)).concat(": ");

                        if (collection.getAttr(i).isRepeating())
                        {
                            System.out.println();
                            String v[] = collection.getAllRepeatingStrings(collection.getAttr(i).getName().trim(), "#").split("#");
                            String t = "[";

                            for (int k = 0; k < v.length; k++)
                            {
                                t += String.valueOf(k).concat("] ");
                                t += v[k];

                                valu += t.concat("\n").concat("\t").concat(formatStringWithSpace("", 30));
                                t = "[";
                            }
                        }
                        else
                        {
                            valu = collection.getValueAt(i).asString();
                        }

                        System.out.println(name.concat(valu));
                        valu = "";
                        name = "";
                    }
                }
            }

            System.out.println("--------------------------------------------------------------- \n");
        }
        if (autoClose)
        {
            closeCollection(collection);
        }
    }

    /**
     * Método usado para formatar a String ao mostrar os resultados
     * 
     * @param s A String a ser formatada
     * @param n A quantidade de casas que deve ter
     * 
     * @return A String fomatada
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    private static String formatStringWithSpace(String s, int n) throws Exception
    {
        return (new StringBuilder().append(new char[(n - s.length())]).toString());
    }

    /**
     * Metodo que fecha uma colecao se ela nao estiver fechada
     * 
     * @param collection A colecao a ser fechada
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static void closeCollection(IDfCollection collection) throws Exception
    {
        if (canCloseCollection(collection))
        {
            collection.close();
        }
    }

    /**
     * Metodo que testa se uma colecao pode ser fechada
     * 
     * @param collection A colecao ser testada
     * 
     * @return	<blockquote>true, pode ser fechada</blockquote>
     * 			<blockquote>false, nao pode ser fechada</blockquote>
     * 			
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static boolean canCloseCollection(IDfCollection collection) throws Exception
    {
        return ((collection != null) && (collection.getState() != IDfCollection.DF_CLOSED_STATE));
    }

}
