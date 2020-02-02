package jutildfc.utilsdfc;

import jutildfc.interfaces.AbstractUtilDfc;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;

public class UIdfQuery extends AbstractUtilDfc
{
    private IDfQuery idfQuery = new DfQuery();

    /**
     * Construtor parametrizado
     * 
     * @param session O Objeto de sessao com o repositorio
     */
    public UIdfQuery(UidfSession session)
    {
        super(session);
    }

    /**
     * Metodo que executa uma query no Content Server
     * 
     * @param queryString A query que se deseja executar
     * @return A collection retornada pela Query
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera retornada
     */
    public IDfCollection execQuery(String queryString) throws Exception
    {
        setDql(queryString);
        return (getIdfQuery().execute(getIdfSession(), DfQuery.DF_EXEC_QUERY));
    }

    public void setDql(String dql) throws Exception
    {
        getIdfQuery().setDQL(dql);
    }

    public IDfQuery getIdfQuery()
    {
        return idfQuery;
    }

    public void setIdfQuery(IDfQuery idfQuery)
    {
        this.idfQuery = idfQuery;
    }

}
