package jutildfc.interfaces;

import java.io.PrintStream;

import com.documentum.fc.client.IDfSession;

import jutildfc.utilsdfc.UidfSession;

public abstract class AbstractUtilDfc
{

    private PrintStream	printErr    = System.err;
    private PrintStream	printMsg    = System.out;

    private UidfSession	UidfSession = null;
    private IDfSession	IdfSession  = null;

    /**
     * Construtor Parametrizado
     * 
     * @param session O Objeto de sessao com o repositorio
     */
    public AbstractUtilDfc(UidfSession session)
    {
	setUidfSession(session);
	setIdfSession(getUidfSession().getSession());
    }

    public UidfSession getUidfSession()
    {
	return UidfSession;
    }

    public void setUidfSession(UidfSession uidfSession)
    {
	UidfSession = uidfSession;
    }

    public IDfSession getIdfSession()
    {
	return IdfSession;
    }

    public void setIdfSession(IDfSession idfSession)
    {
	IdfSession = idfSession;
    }

    public void print(String msg) throws Exception
    {
	System.out.print(msg);
    }

    public void printErr(String mensagem) throws Exception
    {
	printErr.println(mensagem);
    }

    public void printMsg(String mensagem) throws Exception
    {
	printMsg.println(mensagem);
    }

    public void printLnDump(String campos[], String valores[], String tabAdicional) throws Exception
    {
	int l = 0;
	for (String s : campos)
	{
	    if (s.trim().length() > l)
	    {
		l = s.trim().length();
	    }
	}

	String s = "";
	for (int i = 0; i < campos.length; i++)
	{
	    campos[i] = campos[i].trim();
	    if (campos[i].length() < l)
	    {
		for (int k = 0; k < (l - campos[i].length()); k++)
		{
		    s = s.concat(" ");
		}
	    }
	    System.out.print(tabAdicional);
	    printMsg.println(campos[i].trim().concat(s.concat(" : ")).concat(valores[i]));
	    s = "";
	}
    }
}
