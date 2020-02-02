package jutildfc.utilsdfc;

import jutildfc.interfaces.AbstractUtilDfc;

import com.documentum.fc.client.IDfDocbaseMap;

public class UIdfDocBase extends AbstractUtilDfc
{
    private IDfDocbaseMap docBase = null;

    /**
     * Construtor parametrizado
     * 
     * @param session O objeto de sessao com o repositorio
     */
    public UIdfDocBase(UidfSession session)
    {
        super(session);

        try
        {
            setDocBase(getIdfSession().getClient().getDocbaseMap());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que mostra as informacoes atuais da docBaseMap
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void displayDocbaseInfo() throws Exception
    {
        for (int i = 0; i < docBase.getDocbaseCount(); i++)
        {
            System.out.println("Docbase  : " + docBase.getDocbaseName(i));
            System.out.println("Descrip  : " + docBase.getDocbaseDescription(i));
            System.out.println("Id       : " + docBase.getDocbaseId(i));
            System.out.println("Server   : " + docBase.getServerVersion(i));

            System.out.println("---------------------------------------------");
        }
    }

    /**
     * @return Metodo que retorna o Valor da variavel docBase
     */
    public IDfDocbaseMap getDocBase()
    {
        return docBase;
    }

    /**
     * @param docBase O Valor a ser setado na variavel docBase
     */
    public void setDocBase(IDfDocbaseMap docBase)
    {
        this.docBase = docBase;
    }

}
